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
package org.eclipse.persistence.testing.tests.nls.japanese;

import org.eclipse.persistence.testing.framework.AutoVerifyTestCase;
import org.eclipse.persistence.queries.*;
import org.eclipse.persistence.expressions.*;
import java.util.Vector;

public class NLSUseDistinctTest extends AutoVerifyTestCase {
    private Vector employees;
    private ReadAllQuery query;

    public NLSUseDistinctTest() {
        setDescription("[NLS_Japanese] Test use distinct option");
    }

    public void reset() {
        getSession().getIdentityMapAccessor().initializeAllIdentityMaps();
    }

    public void setup() {
    }

    public void test() {
        query = (ReadAllQuery)getSession().getDescriptor(org.eclipse.persistence.testing.tests.nls.japanese.NLSEmployee.class).getQueryManager().getQuery("useDistinctQuery");
        ReadAllQuery queryCopy = (ReadAllQuery)query.clone();
        queryCopy.setSelectionCriteria(new ExpressionBuilder().anyOf("phoneNumbers").get("areaCode").equal("613"));
        employees = (Vector)getSession().executeQuery(queryCopy);
    }

    public void verify() {
        if (employees.size() != 10) {
            throw new org.eclipse.persistence.testing.framework.TestErrorException("ReadAllQuery with useDistinct test failed. Mismatched objects returned");
        }
    }
}
