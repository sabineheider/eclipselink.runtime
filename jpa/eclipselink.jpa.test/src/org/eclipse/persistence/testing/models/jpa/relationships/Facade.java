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
 *     12/02/2010-2.2 Guy Pelletier 
 *       - 324471: Do not default to VariableOneToOneMapping for interfaces unless a managed class implementing it is found
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.relationships;

public interface Facade {
    public String getCost();
}
