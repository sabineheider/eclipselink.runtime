/*******************************************************************************
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.fieldaccess.advanced;

import java.util.List;

public interface ParentInterface {

    public void addChild(Child cs);
    
    public List<Child> getChildren();
    
    public Integer getId();

    public String getSerialNumber();
    
    public int getVersion();
    
    public void setChildren(List<Child> children);

    public void setId(Integer id);

    public void setSerialNumber(String serialNumber);
}
