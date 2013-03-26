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
package org.eclipse.persistence.testing.tests.jpa.ddlgeneration;

import junit.framework.TestSuite;
import junit.framework.Test;

import org.eclipse.persistence.testing.framework.junit.JUnitTestCase;

/**
 * <p><b>Purpose</b>: To collect the tests that will run against Application Server only.
 * DDLGenerationJUnitTestSuite contains several persistence units, we have to split this suite to several test suites based on persistence unit in server env
 * DDLGenerationServerTestSuite is test suite on server as for DDLGenerationJUnitTestSuite in J2SE
 */
public class DDLGenerationServerTestSuite extends TestSuite {
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.setName("DDLGeneration ServerTestSuite");
        suite.addTest(DDLGenerationTestSuite.suite());
        suite.addTest(DDLTablePerClassTestSuite.suite());
        suite.addTest(DDLTableSuffixTestSuite.suite());
        if (! JUnitTestCase.isJPA10()) {
            suite.addTest(DDLGenerationExtendTablesJUnitTestSuite.suite());
        }
        return suite;
    }
}
