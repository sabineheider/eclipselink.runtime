/*******************************************************************************
 * Copyright (c) 2006, 2013 Oracle and/or its affiliates. All rights reserved.
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
package org.eclipse.persistence.jpa.tests.jpql;

import org.eclipse.persistence.jpa.jpql.tools.spi.IQuery;
import org.eclipse.persistence.jpa.tests.jpql.tools.AbstractJPQLQueryHelperTest;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * This unit-test tests {@link AbstractJPQLQueryHelper} when the queries are global and owned by an
 * ORM configuration.
 *
 * @version 2.4
 * @since 2.3
 * @author Pascal Filion
 */
@SuppressWarnings("nls")
@RunWith(JPQLTestRunner.class)
public final class ORMJPQLQueryHelperTest extends AbstractJPQLQueryHelperTest {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IQuery namedQuery(String entityName, String queryName) throws Exception {
		IORMConfiguration ormConfiguration = getORMConfiguration("orm2.xml");
		IQuery namedQuery = ormConfiguration.getNamedQuery(queryName);
		assertNotNull("The named query " + queryName + " could not be found in the ORM", namedQuery);
		return namedQuery;
	}
}