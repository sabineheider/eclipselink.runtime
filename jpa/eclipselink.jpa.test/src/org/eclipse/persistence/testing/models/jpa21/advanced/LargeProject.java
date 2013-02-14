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
 *     02/08/2012-2.4 Guy Pelletier 
 *       - 350487: JPA 2.1 Specification defined support for Stored Procedure Calls
 *     01/23/2013-2.5 Guy Pelletier 
 *       - 350487: JPA 2.1 Specification defined support for Stored Procedure Calls
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa21.advanced;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JPA21_LPROJECT")
@DiscriminatorValue("L")
public class LargeProject extends Project {
    private double m_budget;
    
    protected Employee executive;
    
    public LargeProject() {
        super();
    }
    
    public LargeProject(String name) {
        this();
        this.setName(name);
    }

    public double getBudget() { 
        return m_budget; 
    }
    
    public void setBudget(double budget) { 
        this.m_budget = budget; 
    }

    /**
     * @return the executive
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EXEC_ID")
    public Employee getExecutive() {
        return executive;
    }

    /**
     * @param executive the executive to set
     */
    public void setExecutive(Employee executive) {
        this.executive = executive;
    }

}
