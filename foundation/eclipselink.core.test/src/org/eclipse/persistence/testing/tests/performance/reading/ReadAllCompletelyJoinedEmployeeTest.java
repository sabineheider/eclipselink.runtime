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
package org.eclipse.persistence.testing.tests.performance.reading;

import java.util.*;

import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.testing.models.performance.toplink.*;
import org.eclipse.persistence.testing.tests.performance.PerformanceTest;

/**
 * This tests the performance of read-all queries.
 * Its purpose is to compare the test result with previous release/label results.
 * It also provides a useful test for profiling performance.
 */
public class ReadAllCompletelyJoinedEmployeeTest extends PerformanceTest {
    public ReadAllCompletelyJoinedEmployeeTest() {
        setDescription("This tests the performance of read-all queries with joining.");
    }

    /**
     * Read employee and clear the cache, test database read.
     */
    public void test() throws Exception {
        super.test();
        ReadAllQuery query = new ReadAllQuery(Employee.class);
        query.addJoinedAttribute("address");
        query.addJoinedAttribute(query.getExpressionBuilder().getAllowingNull("manager"));
        query.addJoinedAttribute(query.getExpressionBuilder().anyOf("phoneNumbers"));
        query.addJoinedAttribute(query.getExpressionBuilder().anyOfAllowingNone("managedEmployees"));
        query.addJoinedAttribute(query.getExpressionBuilder().anyOfAllowingNone("projects"));
        allObjects = (List)getSession().executeQuery(query);
        for (Iterator iterator = allObjects.iterator(); iterator.hasNext();) {
            Employee employee = (Employee)iterator.next();
            employee.getAddress();
            employee.getManager();
            employee.getManagedEmployees().size();
            employee.getProjects().size();
            employee.getPhoneNumbers().size();
        }
    }
}
