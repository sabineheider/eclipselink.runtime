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
package org.eclipse.persistence.testing.models.aggregate;

import org.eclipse.persistence.sessions.*;
import org.eclipse.persistence.mappings.*;
import org.eclipse.persistence.descriptors.RelationalDescriptor;

/**
 * This class was generated by the TopLink project class generator.
 * It stores the meta-data (descriptors) that define the TopLink mappings.
 * @see org.eclipse.persistence.sessions.factories.ProjectClassGenerator
 */
public class SwitchProject extends org.eclipse.persistence.sessions.Project {
    public SwitchProject() {
        setName("Switch");
        applyLogin();

        addDescriptor(buildSwitchDescriptor());
        addDescriptor(buildSwitchStateDescriptor());
        addDescriptor(buildSwitchStateOFFDescriptor());
        addDescriptor(buildSwitchStateONDescriptor());
    }

    public void applyLogin() {
        DatabaseLogin login = new DatabaseLogin();
        setLogin(login);
    }

    public RelationalDescriptor buildSwitchDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.aggregate.Switch.class);
        descriptor.addTableName("SWITCH");
        descriptor.addPrimaryKeyFieldName("SWITCH.ID");

        // RelationalDescriptor properties.
        descriptor.useFullIdentityMap();
        descriptor.setIdentityMapSize(100);
        descriptor.setSequenceNumberFieldName("SWITCH.ID");
        descriptor.setSequenceNumberName("SEQ_SWITCH");

        // Query manager.
        descriptor.getDescriptorQueryManager().checkCacheForDoesExist();

        // Event manager.
        // Mappings.
        DirectToFieldMapping idMapping = new DirectToFieldMapping();
        idMapping.setAttributeName("id");
        idMapping.setFieldName("SWITCH.ID");
        descriptor.addMapping(idMapping);

        AggregateObjectMapping stateMapping = new AggregateObjectMapping();
        stateMapping.setAttributeName("state");
        stateMapping.setReferenceClass(org.eclipse.persistence.testing.models.aggregate.SwitchState.class);
        stateMapping.setIsNullAllowed(false);
        stateMapping.addFieldNameTranslation("SWITCH.STATE", "STATE");
        descriptor.addMapping(stateMapping);

        return descriptor;
    }

    public RelationalDescriptor buildSwitchStateDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.descriptorIsAggregate();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.aggregate.SwitchState.class);

        // Inheritance properties.
        descriptor.getDescriptorInheritancePolicy().setClassIndicatorFieldName("STATE");
        descriptor.getDescriptorInheritancePolicy().addClassIndicator(org.eclipse.persistence.testing.models.aggregate.SwitchStateOFF.class, "OFF");
        descriptor.getDescriptorInheritancePolicy().addClassIndicator(org.eclipse.persistence.testing.models.aggregate.SwitchStateON.class, "ON");

        // RelationalDescriptor properties.
        // Query manager.
        // Event manager.
        return descriptor;
    }

    public RelationalDescriptor buildSwitchStateOFFDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.descriptorIsAggregate();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.aggregate.SwitchStateOFF.class);

        // Inheritance properties.
        descriptor.getDescriptorInheritancePolicy().setParentClass(org.eclipse.persistence.testing.models.aggregate.SwitchState.class);

        // RelationalDescriptor properties.
        // Query manager.
        // Event manager.
        return descriptor;
    }

    public RelationalDescriptor buildSwitchStateONDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.descriptorIsAggregate();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.aggregate.SwitchStateON.class);

        // Inheritance properties.
        descriptor.getDescriptorInheritancePolicy().setParentClass(org.eclipse.persistence.testing.models.aggregate.SwitchState.class);

        // RelationalDescriptor properties.
        // Query manager.
        // Event manager.
        return descriptor;
    }
}
