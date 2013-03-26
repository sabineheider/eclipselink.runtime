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
package org.eclipse.persistence.testing.tests.performance.java;

import java.util.*;
import org.eclipse.persistence.testing.framework.*;

/**
 * This test compares the performance for Calendar getTime.
 */
public class CalendarTimeTest extends PerformanceComparisonTestCase {
    protected Calendar calendar = Calendar.getInstance();

    public CalendarTimeTest() {
        setName("Calendar.getTimeMillis vs Calendar.getTime.getTime PerformanceComparisonTest");
        setDescription("This test compares the performance for Calendar getTime.");
        addGetTimeTest();
    }

    /**
     * .getTimeMillis.
     */
    public void test() throws Exception {
        calendar.getTimeInMillis();
    }

    /**
     * getTime.
     */
    public void addGetTimeTest() {
        PerformanceComparisonTestCase test = new PerformanceComparisonTestCase() {
            public void test() {
                calendar.getTime().getTime();
            }
        };
        test.setName("GetTimeTest");
        test.setAllowableDecrease(-50);
        addTest(test);
    }
}
