/*******************************************************************************
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Blaise Doughan - 2.4.2 - initial implementation
 ******************************************************************************/
package org.eclipse.persistence.testing.jaxb.json.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement
@XmlType(propOrder={"wrapperItems"})
public class ElementRefsCollectionRoot {

    private List<ElementRefsCollectionRoot> wrapperItems;

    @XmlElementWrapper
    @XmlElementRef
    public List<ElementRefsCollectionRoot> getWrapperItems() {
        return wrapperItems;
    }

    public void setWrapperItems(List<ElementRefsCollectionRoot> wrapperItems) {
        this.wrapperItems = wrapperItems;
    }


    @Override
    public boolean equals(Object obj) {
        if(null == obj || obj.getClass() != this.getClass()) {
            return false;
        }
        ElementRefsCollectionRoot test = (ElementRefsCollectionRoot) obj;
        if(!equals(wrapperItems, test.wrapperItems)) {
            return false;
        }
        return true;
    }

    private boolean equals(List<?> control, List<?> test) {
        if(control == test) {
            return true;
        }
        if(null == control || null == test) {
            return false;
        }
        if(control.size() != test.size()) {
            return false;
        }
        for(int x=0; x<control.size(); x++) {
            if(!control.get(x).equals(test.get(x))) {
                return false;
            }
        }
        return true;
    }
}
