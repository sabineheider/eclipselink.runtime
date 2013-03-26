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
 *     12/18/2009-2.1 Guy Pelletier 
 *       - 211323: Add class extractor support to the EclipseLink-ORM.XML Schema
 ******************************************************************************/
package org.eclipse.persistence.testing.models.jpa.xml.inheritance;

public abstract class Apple {
    protected Integer id;

    public Integer getId() {
        return id;
    }
   
    public void setId(Integer id) {
        this.id = id;
    }
}
