/*******************************************************************************
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Guy Pelletier - initial API and implementation
 ******************************************************************************/
package org.eclipse.persistence.testing.tests.jpa.config;

/**
 * JPA scripting API implementation helper class.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.5.1
 */
public class PhoneNumberPK  {
    public Integer id;
    public String type;

    public PhoneNumberPK() {}

    public Integer getId() { 
        return id; 
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() { 
        return type; 
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * equals: Answer true if the ids are equal
     */
    public boolean equals(Object anotherPhoneNumber) {
        if (anotherPhoneNumber.getClass() != PhoneNumberPK.class) {
            return false;
        }
        return (getId().equals(((PhoneNumberPK)anotherPhoneNumber).getId()));
    }
}
