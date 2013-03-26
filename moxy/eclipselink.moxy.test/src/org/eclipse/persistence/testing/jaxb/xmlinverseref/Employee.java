/*******************************************************************************
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Denise Smith, February 2013
 ******************************************************************************/
package org.eclipse.persistence.testing.jaxb.xmlinverseref;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee implements AddressOwner{
	public String empId;
	public Address addr;
	
	public Address getAddr(){
	   return addr;
	}
	 
	public boolean equals(Object obj){
		if(obj instanceof Employee){
			Employee compareObject = (Employee)obj;
			if(!empId.equals(compareObject.empId)){
				return false;
			}
			if(!addr.equals(compareObject.addr)){
				return false;
			}
			return true;
		}
		return false;
	}
}
