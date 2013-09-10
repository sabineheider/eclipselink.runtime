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


package org.eclipse.persistence.testing.models.jpa.xml.inheritance;

import java.util.*;
import java.io.*;

public class Company implements Serializable {
    private Number id;
    private String name;
    private Collection<Vehicle> vehicles;

    public Company() {
        vehicles = new Vector<Vehicle>();
    }

    public Number getId() {
        return id;
    }

	public void setId(Number id) { 
        this.id = id; 
    }

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }
}
