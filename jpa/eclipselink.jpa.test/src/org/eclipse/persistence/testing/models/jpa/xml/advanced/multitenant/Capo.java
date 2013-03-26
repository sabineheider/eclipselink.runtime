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
 *     03/23/2011-2.3 Guy Pelletier 
 *       - 337323: Multi-tenant with shared schema support (part 1)
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.xml.advanced.multitenant;

import java.util.Collection;
import java.util.Vector;

import static javax.persistence.CascadeType.ALL;

public class Capo extends Mafioso {
    private Underboss underboss;
    private Collection<Soldier> soldiers;

    public Capo() {
        this.soldiers = new Vector<Soldier>();
    }

    public void addSoldier(Soldier soldier) {
        soldiers.add(soldier);
        soldier.setCapo(this);
    }
    
    public Collection<Soldier> getSoldiers() { 
        return soldiers; 
    }
    
    public Underboss getUnderboss() {
        return underboss;
    }
    
    public void setSoldiers(Collection<Soldier> soldiers) {
        this.soldiers = soldiers;
    }
    
    public void setUnderboss(Underboss underboss) {
        this.underboss = underboss;
    }
}
