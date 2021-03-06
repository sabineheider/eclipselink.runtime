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
package org.eclipse.persistence.testing.oxm.mappings.anycollection.withoutgroupingelement;

/**
 *  @version $Header: AnyCollectionNoDefaultRootComplexChildrenTestCases.java 02-nov-2006.10:57:12 gyorke Exp $
 *  @author  mfobrien
 *  @since   10.1.3.1.0
 */

import java.util.Vector;

import org.eclipse.persistence.exceptions.XMLMarshalException;
import org.eclipse.persistence.oxm.mappings.XMLAnyCollectionMapping;
import org.eclipse.persistence.sessions.Project;
import org.eclipse.persistence.testing.oxm.mappings.XMLMappingTestCases;

public class AnyCollectionNoDefaultRootComplexChildrenTestCases extends XMLMappingTestCases {
    public AnyCollectionNoDefaultRootComplexChildrenTestCases(String name) throws Exception {
        super(name);
        Project p = new AnyCollectionNoDefaultRootWithGroupingElementProject();
        ((XMLAnyCollectionMapping)p.getDescriptor(Root.class).getMappingForAttributeName("any")).useCollectionClass(java.util.ArrayList.class);
        setProject(p);
        setControlDocument("org/eclipse/persistence/testing/oxm/mappings/anycollection/withoutgroupingelement/no_default_root_element_children.xml");
        expectsMarshalException = true;
    }

    public boolean isUnmarshalTest() {
        return false;
    }

    public Object getControlObject() {
        Root root = new Root();
        Vector any = new Vector();
        Child child = new Child();
        child.setContent("Child1");
        any.addElement(child);
        child = new Child();
        child.setContent("Child2");
        any.addElement(child);
        // add object vector to root object
        root.setAny(any);
         return root;
    }

    @Override
    public void assertMarshalException(Exception exception) throws Exception {
        if(exception.getClass() == XMLMarshalException.class) {
            XMLMarshalException xmlMarshalException = (XMLMarshalException) exception;
            if(XMLMarshalException.DEFAULT_ROOT_ELEMENT_NOT_SPECIFIED == xmlMarshalException.getErrorCode()) {
                return;
            }
        }
        throw exception;
    }

}
