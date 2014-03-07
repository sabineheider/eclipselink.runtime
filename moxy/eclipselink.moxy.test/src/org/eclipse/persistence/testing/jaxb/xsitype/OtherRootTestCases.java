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
 *     Denise Smith - August 2013
 ******************************************************************************/ 
package org.eclipse.persistence.testing.jaxb.xsitype;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.eclipse.persistence.testing.jaxb.JAXBWithJSONTestCases;

public class OtherRootTestCases extends JAXBWithJSONTestCases {
    private final static String XML_RESOURCE = "org/eclipse/persistence/testing/jaxb/xsitype/otherRoot.xml";
    private final static String JSON_RESOURCE = "org/eclipse/persistence/testing/jaxb/xsitype/otherRoot.json";	  
    public OtherRootTestCases(String name) throws Exception {
        super(name);
        setControlDocument(XML_RESOURCE);        
        setControlJSON(JSON_RESOURCE);
        Class[] classes = new Class[3];
        classes[0] = Root.class;
        classes[1] = Foo.class;
        classes[2] = ObjectFactory.class;
        setClasses(classes);
    }

    protected Object getControlObject() {
    	JAXBElement<Root> obj = new JAXBElement<Root>(new QName("otherNamespace", "otherRoot"), Root.class, new Root());
        return obj;        
    }  
    
    public void testRI()throws Exception{
    	 Class[] classes = new Class[3];
         classes[0] = Root.class;
         classes[1] = Foo.class;
         classes[2] = ObjectFactory.class;
    	JAXBContext ctx = JAXBContext.newInstance(classes);
    	Unmarshaller u = ctx.createUnmarshaller();
    	Object obj=u.unmarshal(getClass().getClassLoader().getResourceAsStream(XML_RESOURCE));
    	System.out.println(obj.getClass());
    }
}
