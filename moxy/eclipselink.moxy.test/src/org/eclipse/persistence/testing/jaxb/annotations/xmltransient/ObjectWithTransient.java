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
 *     Denise Smith - October 18, 2012
 ******************************************************************************/
package org.eclipse.persistence.testing.jaxb.annotations.xmltransient;

import java.util.List;

public class ObjectWithTransient {
    public String testString;
    public TransientClass transientThing;
    public List<TransientClass> transientThings;
}
