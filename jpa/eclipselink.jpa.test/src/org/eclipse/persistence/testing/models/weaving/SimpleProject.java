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
package org.eclipse.persistence.testing.models.weaving;

import org.eclipse.persistence.sessions.*;
import org.eclipse.persistence.descriptors.*;
import org.eclipse.persistence.mappings.*;

/**
 * This class was generated by the TopLink project class generator.
 * It stores the meta-data (descriptors) that define the TopLink mappings.
 * ## Oracle TopLink - 10g release 3 (10.1.3.0) (Build 050307) ##
 * @see org.eclipse.persistence.sessions.factories.ProjectClassGenerator
 */

public class SimpleProject extends org.eclipse.persistence.sessions.Project {

public SimpleProject() {
	setName("SimpleProject");
	applyLogin();
	
	addDescriptor(buildSimpleObjectDescriptor());
	addDescriptor(buildSimpleAggregateDescriptor());
	
}

public void applyLogin() {
	DatabaseLogin login = new DatabaseLogin();	
	setDatasourceLogin(login);
}

public ClassDescriptor buildSimpleObjectDescriptor() {
	RelationalDescriptor descriptor = new RelationalDescriptor();
	descriptor.setJavaClass(org.eclipse.persistence.testing.models.weaving.SimpleObject.class);
	descriptor.addTableName("SIMPLE");
	
	// Descriptor Properties.
	descriptor.useSoftCacheWeakIdentityMap();
	descriptor.setIdentityMapSize(100);
	descriptor.onlyRefreshCacheIfNewerVersion();
	descriptor.setAlias("SimpleObject");
	
	
	// Query Manager.
	descriptor.getQueryManager().checkCacheForDoesExist();
	
	
	// Event Manager.

	// Mappings.
	
	DirectToFieldMapping versionMapping = new DirectToFieldMapping();
	versionMapping.setAttributeName("version");
	versionMapping.setFieldName("SIMPLE.VER");
	descriptor.addMapping(versionMapping);
	
	DirectToFieldMapping booleanAttributeMapping = new DirectToFieldMapping();
	booleanAttributeMapping.setAttributeName("booleanAttribute");
	booleanAttributeMapping.setFieldName("SIMPLE.BOOL_ATTRIB");
	descriptor.addMapping(booleanAttributeMapping);
	
	DirectToFieldMapping charAttributeMapping = new DirectToFieldMapping();
	charAttributeMapping.setAttributeName("charAttribute");
	charAttributeMapping.setFieldName("SIMPLE.CHAR_ATTRIB");
	descriptor.addMapping(charAttributeMapping);
	
	DirectToFieldMapping byteAttributeMapping = new DirectToFieldMapping();
	byteAttributeMapping.setAttributeName("byteAttribute");
	byteAttributeMapping.setFieldName("SIMPLE.BYTE_ATTRIB");
	descriptor.addMapping(byteAttributeMapping);
	
	DirectToFieldMapping shortAttributeMapping = new DirectToFieldMapping();
	shortAttributeMapping.setAttributeName("shortAttribute");
	shortAttributeMapping.setFieldName("SIMPLE.SHORT_ATTRIB");
	descriptor.addMapping(shortAttributeMapping);
	
	DirectToFieldMapping longAttributeMapping = new DirectToFieldMapping();
	longAttributeMapping.setAttributeName("longAttribute");
	longAttributeMapping.setFieldName("SIMPLE.LONG_ATTRIB");
	descriptor.addMapping(longAttributeMapping);
	
	DirectToFieldMapping floatAttributeMapping = new DirectToFieldMapping();
	floatAttributeMapping.setAttributeName("floatAttribute");
	floatAttributeMapping.setFieldName("SIMPLE.FLOAT_ATTRIB");
	descriptor.addMapping(floatAttributeMapping);
	
	DirectToFieldMapping doubleAttributeMapping = new DirectToFieldMapping();
	doubleAttributeMapping.setAttributeName("doubleAttribute");
	doubleAttributeMapping.setFieldName("SIMPLE.DOUBLE_ATTRIB");
	descriptor.addMapping(doubleAttributeMapping);
	
	DirectToFieldMapping idMapping = new DirectToFieldMapping();
	idMapping.setAttributeName("id");
	idMapping.setFieldName("SIMPLE.ID");
	descriptor.addMapping(idMapping);

	DirectToFieldMapping firstNameMapping = new DirectToFieldMapping();
	firstNameMapping.setAttributeName("name");
	firstNameMapping.setFieldName("SIMPLE.NAME");
	descriptor.addMapping(firstNameMapping);
	
	AggregateObjectMapping simpleAggregateMapping = new AggregateObjectMapping();
	simpleAggregateMapping.setAttributeName("simpleAggregate");
	simpleAggregateMapping.setReferenceClass(org.eclipse.persistence.testing.models.weaving.SimpleAggregate.class);
	simpleAggregateMapping.setIsNullAllowed(true);
	simpleAggregateMapping.addFieldNameTranslation("SIMPLE.FOO", "foo->DIRECT");
	descriptor.addMapping(simpleAggregateMapping);

	return descriptor;
}

public ClassDescriptor buildSimpleAggregateDescriptor() {
	RelationalDescriptor descriptor = new RelationalDescriptor();
	descriptor.descriptorIsAggregate();
	descriptor.setJavaClass(org.eclipse.persistence.testing.models.weaving.SimpleAggregate.class);
	
	// Descriptor Properties.
	descriptor.setAlias("SimpleAggregate");
	
	// Mappings.
	DirectToFieldMapping fooMapping = new DirectToFieldMapping();
	fooMapping.setAttributeName("foo");
	fooMapping.setFieldName("foo->DIRECT");
	descriptor.addMapping(fooMapping);
	
	return descriptor;
}
}
