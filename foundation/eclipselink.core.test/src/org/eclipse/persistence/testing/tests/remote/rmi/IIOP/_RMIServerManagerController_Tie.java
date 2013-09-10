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
package org.eclipse.persistence.testing.tests.remote.rmi.IIOP;

// Tie class generated by rmic, do not edit.
// Contents subject to change without notice.

import org.eclipse.persistence.sessions.remote.rmi.iiop.RMIRemoteSessionController;
import java.rmi.Remote;
import javax.rmi.CORBA.Tie;
import javax.rmi.CORBA.Util;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;
import org.omg.CORBA.portable.UnknownException;
import org.omg.CORBA_2_3.portable.ObjectImpl;


public class _RMIServerManagerController_Tie extends ObjectImpl implements Tie {
	
	private RMIServerManagerController target = null;
	
	private static final String[] _type_ids = {
		"RMI:org.eclipse.persistence.testing.tests.remote.rmi.IIOP.RMIServerManager:0000000000000000"
	};
	
	public String[] _ids() { 
		return _type_ids;
	}
	public OutputStream  _invoke(String method, InputStream _in, ResponseHandler reply) throws SystemException {
		try {
			org.omg.CORBA_2_3.portable.InputStream in = 
				(org.omg.CORBA_2_3.portable.InputStream) _in;
			if (method.equals("createRemoteSessionController")) {
				RMIRemoteSessionController result = target.createRemoteSessionController();
				OutputStream out = reply.createReply();
				Util.writeRemoteObject(out,result);
				return out;
			}
			throw new BAD_OPERATION();
		} catch (SystemException ex) {
			throw ex;
		} catch (Throwable ex) {
			throw new UnknownException(ex);
		}
	}
	public void deactivate() {
		_orb().disconnect(this);
		_set_delegate(null);
		target = null;
	}
	public Remote getTarget() {
		return target;
	}
	public ORB orb() {
		return _orb();
	}
	public void orb(ORB orb) {
		orb.connect(this);
	}
	public void setTarget(Remote target) {
		this.target = (RMIServerManagerController) target;
	}
	public org.omg.CORBA.Object thisObject() {
		return this;
	}
}
