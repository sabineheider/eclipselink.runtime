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
 *     Gordon Yorke - Initial Contribution
 ******************************************************************************/
package org.eclipse.persistence.testing.models.jpa.xml.cacheable;

public class CacheableForceProtectedEntity {
    private int id;
    protected CacheableFalseEntity cacheableFalse;

    public CacheableForceProtectedEntity() {}
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cacheableFalse
     */
    public CacheableFalseEntity getCacheableFalse() {
        return cacheableFalse;
    }

    /**
     * @param cacheableFalse the cacheableFalse to set
     */
    public void setCacheableFalse(CacheableFalseEntity cacheableFalse) {
        this.cacheableFalse = cacheableFalse;
    }

}
