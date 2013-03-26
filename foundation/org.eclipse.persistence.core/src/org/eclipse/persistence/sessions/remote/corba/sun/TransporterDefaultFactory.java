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
package org.eclipse.persistence.sessions.remote.corba.sun;

import org.eclipse.persistence.internal.sessions.remote.Transporter;

/**
 * INTERNAL:
* org/eclipse/persistence/internal/remote/TransporterDefaultFactory.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from d:/StarTeam/DIDev/Pine/TopLink/DISource/IDLs/CorbaRemoteSessionControllerSun.idl
* Thursday, May 3, 2001 1:36:59 PM EDT
*/
public class TransporterDefaultFactory implements org.omg.CORBA.portable.ValueFactory {
    public java.io.Serializable read_value(org.omg.CORBA_2_3.portable.InputStream is) {
        return is.read_value(new Transporter());
    }
}
