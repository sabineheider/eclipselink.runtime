/*******************************************************************************
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *
 ******************************************************************************/
package org.eclipse.persistence.jpa.tests.jpql.parser;

import org.eclipse.persistence.jpa.tests.jpql.JPQLTestRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The root test suite containing the test suites that define how to test the parser with various
 * JPA versions and extensions.
 *
 * @version 2.5.1
 * @since 2.4
 * @author Pascal Filion
 */
@SuiteClasses({
	AllJPQLParserTests1_0.class,
	AllJPQLParserTests2_0.class,
	AllJPQLParserTests2_1.class,
	AllEclipseLinkJPQLParserTests.class,
	AllEclipseLinkJPQLParserTests2_1.class,
	AllEclipseLinkJPQLParserTests2_4.class,
	AllEclipseLinkJPQLParserTests2_5.class,
	AllJPQLParserConcurrentTests.class
})
@RunWith(JPQLTestRunner.class)
public final class AllJPQLParserTests {

	private AllJPQLParserTests() {
		super();
	}
}