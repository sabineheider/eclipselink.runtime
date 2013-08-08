/*******************************************************************************
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.distributedservers.rcm.jgroups;

import org.eclipse.persistence.sessions.DatabaseSession;
import org.eclipse.persistence.sessions.Session;

import org.eclipse.persistence.testing.tests.distributedservers.DistributedServer;
import org.eclipse.persistence.testing.tests.distributedservers.rcm.broadcast.BroadcastDistributedServersModel;
import org.eclipse.persistence.testing.tests.distributedservers.rcm.broadcast.BroadcastSetupHelper;

public class JGroupsDistributedServersModel extends BroadcastDistributedServersModel {

    public JGroupsDistributedServersModel() {
        setDescription("Tests cache synchronization with JGroups.");

        // maximum wait time to give a chance to remote command recipient to process it before verification.
        timeToWaitBeforeVerify = 10000;
    }

    public void addTests() {
        addSuperTests();

        // substitute each test with wrapped test - an instance of
        // BroadcastSetupHelper.TestWrapperWithEventLock, which contains
        // the original test as an internal test.
        // The wrapper methods call the respective methods on the internal test,
        // adding a wait after internalTest.test() method is called 
        // (in case there was merge with changes on the source side) 
        // until either the target side merges the sent changes or exception occurs.
        BroadcastSetupHelper.wrapAllTestCases(this, timeToWaitBeforeVerify);
    }    
    
    /**
     * Factory method for a DistributedServer.
     */
    public DistributedServer createDistributedServer(Session session) {
        return new JGroupsDistributedServer((DatabaseSession)session);
    }

    protected JGroupsSetupHelper getHelper() {
        return JGroupsSetupHelper.getHelper();
    }
    
    @Override
    public boolean requiresRegistry() {
        return false;
    }
}
