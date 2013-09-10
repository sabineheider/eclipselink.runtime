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
package org.eclipse.persistence.testing.tests.workbenchintegration;

/**
 *  This test system uses the Employee test system to test the integration 
 *  between the Mapping Workbench and the Foundation Library. To do this, it 
 *  writes our test project to a project class file and then compile and 
 *  instantiate the project class and runs the employee tests on it.
 *  @author Edwin Tang
 */
public class EmployeeWorkbenchIntegrationSubSystem extends EmployeeWorkbenchIntegrationSystem {
    protected void buildProject() {
        project = WorkbenchIntegrationSystemHelper.buildProjectClass(getInitialProject(), PROJECT_FILE);
    }
}
