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
 *     10/18/2010-2.2 Guy Pelletier 
 *       - 326973: TABLE_PER_CLASS with EmbeddedId results in DescriptorException EclipseLink-74 
 *                 "The primary key fields are not set for this descriptor"
 ******************************************************************************/
package org.eclipse.persistence.testing.models.jpa.inheritance;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Model class added to test metadata processing error for bug 326973.
 * Model not added to the InheritanceTableCreator.
 */
@Entity
@Table(name="TPC_SHERATON")
public class Sheraton extends Hotel {

}
