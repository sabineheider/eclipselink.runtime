/*
 * The contents of this file are subject to the terms 
 * of the Common Development and Distribution License 
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at 
 * https://glassfish.dev.java.net/public/CDDLv1.0.html or
 * glassfish/bootstrap/legal/CDDLv1.0.txt.
 * See the License for the specific language governing 
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL 
 * Header Notice in each file and include the License file 
 * at glassfish/bootstrap/legal/CDDLv1.0.txt.  
 * If applicable, add the following below the CDDL Header, 
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information: 
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 */

package javax.resource.spi.work;

import java.lang.Object;
import java.lang.Runnable;
import java.lang.Exception;
import java.lang.Throwable;

/**
 * This interface models a <code>WorkManager</code> which provides a facility
 * to submit <code>Work</code> instances for execution. This frees the user
 * from having to create Java threads directly to do work. Further, this
 * allows efficient pooling of thread resources and more control over thread
 * usage.
 *
 * The various stages in <code>Work</code> processing are:
 * <ul>
 * <li> work submit: A <code>Work</code> instance is being submitted for 
 * execution. The <code>Work</code> instance could either be accepted or 
 * rejected with a <code>WorkRejectedException</code> set to an appropriate
 * error code. </li>
 *
 * <li> work accepted: The submitted <code>Work</code> instance has been 
 * accepted. The accepted <code>Work</code> instance could either start 
 * execution or could be rejected again with a 
 * <code>WorkRejectedException</code> set to an appropriate error code.
 * There is no guarantee on when the  execution would start unless a start 
 * timeout duration is specified. When a start timeout is specified, the 
 * <code>Work</code> execution must be started within the specified  
 * duration (not a  real-time guarantee), failing which a 
 * <code>WorkRejectedException</code> set to an error code 
 * (<code>WorkRejected.TIMED_OUT</code>) is thrown. </li>
 *
 * <li> work rejected: The <code>Work</code> instance has  been rejected. 
 * The <code>Work</code> instance could be rejected during <code>Work</code>
 * submittal  or after the <code>Work</code> instance has been accepted 
 * (but before Work instance starts execution). The rejection could be due 
 * to internal factors or start timeout expiration. A 
 * <code>WorkRejectedException</code> with an appropriate error code 
 * (indicates the reason) is thrown in both cases. </li>
 *
 * <li> work started: The execution of the <code>Work</code> 
 * instance has started. This means that a thread
 * has been allocated for its execution. But this  
 * does not guarantee that the allocated thread has been scheduled to run 
 * on a CPU resource. Once execution is started, the allocated thread 
 * sets up an appropriate execution context (transaction , security, etc)
 * and calls Work.run(). Note, any exception thrown during execution context
 * setup or Work.run() leads to completion of processing. </li>
 *
 * <li> work completed: The execution of the <code>Work</code> has been 
 * completed. The execution could complete with or without an exception.
 * The <code>WorkManager</code> catches any exception thrown during 
 * <code>Work</code> processing (which includes execution context setup), 
 * and wraps it with a <code>WorkCompletedException</code>. </li>
 * </ul>
 *
 * @version 1.0
 * @author  Ram Jeyaraman
 */
public interface WorkManager {

    /**
     * A constant to indicate timeout duration. A zero timeout value indicates
     * an action be performed immediately.
     */
    long IMMEDIATE = 0L;

    /**
     * A constant to indicate timeout duration. A maximum timeout value 
     * indicates that an action be performed arbitrarily without any time 
     * constraint.
     */
    long INDEFINITE = Long.MAX_VALUE;

    /**
     * A constant to indicate an unknown start delay duration or other unknown
     * values.
     */
    long UNKNOWN = -1;

    /**
     * Accepts a <code>Work</code> instance for processing. This call
     * blocks until the <code>Work</code> instance completes execution.
     * There is no guarantee on when the accepted <code>Work</code> 
     * instance would start execution ie., there is no time constraint 
     * to start execution.
     *
     * @param work The unit of work to be done.  
     * Could be long or short-lived.
     *
     * @throws WorkRejectedException indicates that a 
     * <code>Work</code> instance has been rejected from further processing.
     * This can occur due to internal factors.
     *
     * @throws WorkCompletedException indicates that a
     * <code>Work</code> instance has completed execution with an exception.
     */
    void doWork(Work work) // startTimeout = INDEFINITE
	throws WorkException;

    /**
     * Accepts a <code>Work</code> instance for processing. This call
     * blocks until the <code>Work</code> instance completes execution.
     *
     * @param work The unit of work to be done.  
     * Could be long or short-lived.
     *
     * @param startTimeout a time duration (in milliseconds) 
     * within which the execution of the <code>Work</code> instance must
     * start. Otherwise, the <code>Work</code> instance is rejected with a
     * <code>WorkRejectedException</code> set to an appropriate error code 
     * (<code>WorkRejectedException.TIMED_OUT</code>). Note, this
     * does not offer real-time guarantees.
     *
     * @param execContext an object containing the execution
     * context with which the submitted <code>Work</code> instance must
     * be executed.
     *
     * @param workListener an object which would be notified
     * when the various <code>Work</code> processing events (work accepted, 
     * work rejected, work started, work completed) occur.
     *
     * @throws WorkRejectedException indicates that a 
     * <code>Work</code> instance has been rejected from further processing.
     * This can occur due to internal factors or start timeout expiration.
     *
     * @throws WorkCompletedException indicates that a
     * <code>Work</code> instance has completed execution with an exception.
     */
    void doWork(Work work, long startTimeout, 
            ExecutionContext execContext, WorkListener workListener) 
	throws WorkException;

    /**
     * Accepts a <code>Work</code> instance for processing. This call
     * blocks until the <code>Work</code> instance starts execution
     * but not until its completion. There is no guarantee on when
     * the accepted <code>Work</code> instance would start
     * execution ie., there is no time constraint to start execution.
     *
     * @param work The unit of work to be done.  
     * Could be long or short-lived.
     *
     * @return the time elapsed (in milliseconds) from <code>Work</code>
     * acceptance until start of execution. Note, this does not offer 
     * real-time guarantees. It is valid to return -1, if the actual start 
     * delay duration is unknown.
     *
     * @throws WorkRejectedException indicates that a 
     * <code>Work</code> instance has been rejected from further processing.
     * This can occur due to internal factors.
     */
    long startWork(Work work) // startTimeout = INDEFINITE
	throws WorkException;

    /**
     * Accepts a <code>Work</code> instance for processing. This call
     * blocks until the <code>Work</code> instance starts execution
     * but not until its completion. There is no guarantee on when
     * the accepted <code>Work</code> instance would start
     * execution ie., there is no time constraint to start execution.
     *
     * @param work The unit of work to be done.  
     * Could be long or short-lived.
     *
     * @param startTimeout a time duration (in milliseconds) 
     * within which the execution of the <code>Work</code> instance must
     * start. Otherwise, the <code>Work</code> instance is rejected with a
     * <code>WorkRejectedException</code> set to an appropriate error code 
     * (<code>WorkRejectedException.TIMED_OUT</code>). Note, this
     * does not offer real-time guarantees.
     *
     * @param execContext an object containing the execution
     * context with which the submitted <code>Work</code> instance must
     * be executed.
     *
     * @param workListener an object which would be notified
     * when the various <code>Work</code> processing events (work accepted, 
     * work rejected, work started, work completed) occur.
     *
     * @return the time elapsed (in milliseconds) from <code>Work</code>
     * acceptance until start of execution. Note, this does not offer 
     * real-time guarantees. It is valid to return -1, if the actual start 
     * delay duration is unknown.
     *
     * @throws WorkRejectedException indicates that a 
     * <code>Work</code> instance has been rejected from further processing.
     * This can occur due to internal factors or start timeout expiration.
     */
    long startWork(Work work, long startTimeout, 
            ExecutionContext execContext, WorkListener workListener) 
	throws WorkException;

    /**
     * Accepts a <code>Work</code> instance for processing. This call
     * does not block and returns immediately once a <code>Work</code>
     * instance has been accepted for processing. There is no guarantee
     * on when the submitted <code>Work</code> instance would start
     * execution ie., there is no time constraint to start execution.
     *
     * @param work The unit of work to be done.  
     * Could be long or short-lived.
     *
     * @throws WorkRejectedException indicates that a 
     * <code>Work</code> instance has been rejected from further processing.
     * This can occur due to internal factors.
     */
    void scheduleWork(Work work) // startTimeout = INDEFINITE
	throws WorkException;

    /**
     * Accepts a <code>Work</code> instance for processing. This call
     * does not block and returns immediately once a <code>Work</code>
     * instance has been accepted for processing.
     *
     * @param work The unit of work to be done. 
     * Could be long or short-lived.
     *
     * @param startTimeout a time duration (in milliseconds) 
     * within which the execution of the <code>Work</code> instance must
     * start. Otherwise, the <code>Work</code> instance is rejected with a
     * <code>WorkRejectedException</code> set to an appropriate error code 
     * (<code>WorkRejectedException.TIMED_OUT</code>). Note, this
     * does not offer real-time guarantees.
     *
     * @param execContext an object containing the execution
     * context with which the submitted <code>Work</code> instance must
     * be executed.
     *
     * @param workListener an object which would be notified
     * when the various <code>Work</code> processing events (work accepted, 
     * work rejected, work started, work completed) occur.
     *
     * @throws WorkRejectedException indicates that a 
     * <code>Work</code> instance has been rejected from further processing.
     * This can occur due to internal factors.
     */
    void scheduleWork(Work work, long startTimeout, 
            ExecutionContext execContext, WorkListener workListener) 
	throws WorkException;
}
