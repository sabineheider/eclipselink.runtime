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
*     Matt MacIvor - 2.5.1 - Initial Implementation
******************************************************************************/
package org.eclipse.persistence.testing.jaxb.interfaces.xmltransient;

import javax.xml.bind.annotation.XmlTransient;

public class RootClass {
    
    public String x;
    
    @XmlTransient
    public ChildInterface fieldInterface;

    
    @XmlTransient
    public ChildInterface getMethodInterface() {
        return null;
    }
    
    public void setMethodInterface(ChildInterface ci) {
        
    }
}
