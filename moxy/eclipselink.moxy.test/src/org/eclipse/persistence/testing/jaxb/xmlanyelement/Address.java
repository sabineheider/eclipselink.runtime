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
package org.eclipse.persistence.testing.jaxb.xmlanyelement;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="address")
public class Address {

	public String street;
	public String city;
	public String country;
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Address)) {
			return false;
		}
		Address addr = (Address)obj;
		return ((addr.street ==null && street==null) || ( addr.street.equals(street)))
		  &&((addr.city ==null && city==null) || ( addr.city.equals(city)))
		  &&((addr.country ==null && country==null) || ( addr.country.equals(country)));
	}

}
