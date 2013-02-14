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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.testing.jaxb.JAXBWithJSONTestCases;

public class MapElementWrapperExternalTestCases extends JAXBWithJSONTestCases{
	
	private final static String XML_RESOURCE = "org/eclipse/persistence/testing/jaxb/map/map.xml";
	private final static String XSD_RESOURCE = "org/eclipse/persistence/testing/jaxb/map/map.xsd";
	private final static String JSON_RESOURCE = "org/eclipse/persistence/testing/jaxb/map/map.json";
	
	public MapElementWrapperExternalTestCases(String name) throws Exception {
		super(name);
		setClasses(new Class[]{RootNoAnnotations.class});
    	setControlDocument(XML_RESOURCE);
    	setControlJSON(JSON_RESOURCE);	
    }
	
	public Map getProperties(){
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("org/eclipse/persistence/testing/jaxb/map/eclipselink-oxm.xml");

		HashMap<String, Source> metadataSourceMap = new HashMap<String, Source>();
	    metadataSourceMap.put("org.eclipse.persistence.testing.jaxb.map", new StreamSource(inputStream));
	    Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put(JAXBContextFactory.ECLIPSELINK_OXM_XML_KEY, metadataSourceMap);		
        
        return properties;
	}

	@Override
	protected Object getControlObject() {
		RootNoAnnotations root = new RootNoAnnotations();
		root.stringStringMap = new HashMap<String, String>();
		root.stringStringMap.put("key1", "value1");
		
		root.integerComplexValueMap = new HashMap<Integer, ComplexValue>();
		root.integerComplexValueMap.put(10, new ComplexValue("aaa","bbb"));
		
		return root;
	}
	
	public void testSchemaGen() throws Exception{
		List<InputStream> controlSchemas = new ArrayList<InputStream>();
		controlSchemas.add(getClass().getClassLoader().getResourceAsStream(XSD_RESOURCE));
		super.testSchemaGen(controlSchemas);
	}
	
}
