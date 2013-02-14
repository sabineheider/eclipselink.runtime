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
 *     Denise Smith  February, 2013
 ******************************************************************************/
package org.eclipse.persistence.testing.jaxb.map;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="root")
@XmlType(name="root")
public class RootNoAnnotations {

	public Map<String, String> stringStringMap;
	public Map<Integer,ComplexValue > integerComplexValueMap;
	
	 public boolean equals(Object obj){
	    	if(!(obj instanceof RootNoAnnotations)) {
	    		return false;
	    	}
	    	RootNoAnnotations compare = (RootNoAnnotations)obj;
	    	return stringStringMap.equals(compare.stringStringMap) && integerComplexValueMap.equals(compare.integerComplexValueMap);
	  }
}
