/*******************************************************************************
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Blaise Doughan - 2.4.2 - initial implementation
 ******************************************************************************/
package org.eclipse.persistence.testing.jaxb.json.any;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AnyTestCases extends TestSuite {

    public static Test suite() {
        TestSuite suite = new TestSuite("AnyTestCases");
        suite.addTestSuite(EmptyAnyCollectionNoRootTestCases.class);
        suite.addTestSuite(EmptyAnyCollectionWithRootTestCases.class);
        suite.addTestSuite(NullAnyCollectionNoRootTestCases.class);
        suite.addTestSuite(NullAnyCollectionWithRootTestCases.class);
        return suite;
    }

}
