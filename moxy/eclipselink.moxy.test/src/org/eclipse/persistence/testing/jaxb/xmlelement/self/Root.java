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
 *     Denise Smith - September 2013
 ******************************************************************************/
package org.eclipse.persistence.testing.jaxb.xmlelement.self;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement
public class Root {
   @XmlPath(value=".")	
   public Object child;
   
   public boolean equals(Object obj){
	   if(obj instanceof Root){
		   return (child == null && ((Root)obj).child == null) || (child != null && child.equals(((Root)obj).child));
	   }
	   return false;
   }
}
