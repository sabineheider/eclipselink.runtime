/*******************************************************************************
 * Copyright (c) 1998, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.tests.performance.concurrent;

import org.eclipse.persistence.sessions.*;
import org.eclipse.persistence.testing.models.performance.toplink.*;

/**
 * This test compares the concurrency of read all.
 * This test must be run on a multi-CPU machine to be meaningful.
 */
public class ReadAllObjectsIsolatedConcurrentTest extends IsolatedConcurrentTest {

    public ReadAllObjectsIsolatedConcurrentTest() {
        setDescription("This tests the concurrency of read-all.");
    }

    /**
     * Find all employees
     */
    public void runTask() throws Exception {
        Session client = getServerSession().acquireClientSession();
        client.readAllObjects(Employee.class);
        client.release();
    }
}
