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
 *     11/16/2009-2.0 Guy Pelletier 
 *       - 288392: With Identity sequencing entity with dependant ID can be sent to DB multiple times
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.advanced.derivedid;

public class CellNumberPK {
    public Integer id;
    public String number;

    public CellNumberPK() {}

    public Integer getId() { 
        return id; 
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() { 
        return number; 
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof CellNumberPK) {
           CellNumberPK otherCellNumber = (CellNumberPK) objectToCompare;
                
           if (! getId().equals(otherCellNumber.getId())) {
               return false;
           }
           
           return getNumber().equals(otherCellNumber.getNumber());
        }
        
        return false;
    }
}
