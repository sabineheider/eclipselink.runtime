/*******************************************************************************
 * Copyright (c) 1998, 2012 Oracle and/or its affiliates. All rights reserved.
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
package org.eclipse.persistence.testing.jaxb.xmlenum;

import java.math.RoundingMode;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "employee")
public class EmployeeSingleDepartment {

    public String name;

    @XmlElement(name = "department-number")
    public Department department;

    public RoundingMode roundingMode;

    public boolean equals(Object o) {
        if (!(o instanceof EmployeeSingleDepartment) || o == null) {
            return false;
        } else {
            EmployeeSingleDepartment e = ((EmployeeSingleDepartment) o);
            return (e.department == this.department) && (e.roundingMode == this.roundingMode);
        }
    }

    public String toString() {
        return "EMPLOYEE(" + department + ", " + roundingMode + ")";
    }

}