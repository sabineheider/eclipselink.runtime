/*******************************************************************************
 * Copyright (c) 1998, 2014 Oracle and/or its affiliates. All rights reserved.
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
package org.eclipse.persistence.testing.tests.isolatedsession;

import org.eclipse.persistence.sessions.*;
import org.eclipse.persistence.sessions.Project;
import org.eclipse.persistence.tools.schemaframework.*;
import org.eclipse.persistence.testing.framework.*;

public class IsolatedSessionSystem extends TestSystem {
    public void addDescriptors(DatabaseSession session) {
        Project project = new IsolatedEmployeeProject();
        session.addDescriptors(project);
    }

    public void createTables(DatabaseSession session) {
        SchemaManager schemaManager = new SchemaManager(session);
        schemaManager.replaceObject(IsolatedEmployee.buildIsolatedTableDefinition());
        schemaManager.replaceObject(IsolatedPhoneNumber.buildIsolatedTableDefinition());
        schemaManager.replaceObject(IsolatedAddress.buildIsolatedTableDefinition());
        schemaManager.replaceObject(IsolatedEmployee.buildISOLATEDRESPONSTable());
        schemaManager.replaceObject(IsolatedEmployee.buildISOLATEDSALARYTable());
        schemaManager.replaceObject(IsolatedParent.buildISOLATEDPARENTTable());
        schemaManager.replaceObject(IsolatedChild.buildISOLATEDCHILDTable());
        schemaManager.replaceObject(IsolatedDog.buildISOLATEDDOGTable());
        schemaManager.replaceObject(IsolatedBone.buildISOLATEDBONETable());
    }

    public void populate(DatabaseSession session) {
        UnitOfWork uow = session.acquireUnitOfWork();
        uow.registerObject(IsolatedEmployee.buildEmployeeExample1());
        uow.registerObject(IsolatedEmployee.buildEmployeeExample2());
        uow.registerObject(IsolatedParent.buildIsolatedParentExample1());
        uow.registerObject(IsolatedParent.buildIsolatedParentExample2());
        uow.registerObject(IsolatedDog.buildIsolatedDogExample1());
        uow.commit();
    }
}
