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
 *     Denise Smith - May 2013
 ******************************************************************************/ 
package org.eclipse.persistence.testing.jaxb.xmlvariablenode.method;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class ThingGetOnly {
		
	private String thingName;
	private String thingValue;		
	
	
	public ThingGetOnly(){		
	}
	public ThingGetOnly(String thingName){
		this.thingName = thingName;
	}
	
	public String getThingValue() {
		return thingValue;
	}

	@XmlElement
	public void setThingValue(String thingValue) {
		this.thingValue = thingValue;
	}
	
	@XmlElement
	public String getThingName() {
		return thingName;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof ThingGetOnly){
		    return((thingName == null && ((ThingGetOnly)obj).thingName ==null) ||(thingName.equals(((ThingGetOnly)obj).thingName))) && 	
		    thingValue.equals(((ThingGetOnly)obj).thingValue);
		  
		}
		return false;
	}
}
