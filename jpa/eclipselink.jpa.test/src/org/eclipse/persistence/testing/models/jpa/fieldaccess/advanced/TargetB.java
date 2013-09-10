/*******************************************************************************
 * Copyright (c) 2012, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     May 28, 2012 - ailitchev
 *        - Bug 341709 - Delete fails with DB constraint violation due to an internal update
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.fieldaccess.advanced;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CMP3_FA_TARGET_B")
public class TargetB implements Serializable {
    @Id
    private String id;
    private String description;

    @OneToOne
    @JoinColumn(name="SOURCE_ID")
    private Source source;
    
    /**
     * 
     */
    public TargetB() {
    }

    public TargetB(String id) {
        this.id = id;
    }

    public TargetB(String id, String description) {
        this(id);
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Source getSource() {
        return this.source;
    }
    public void setSource(Source source) {
        this.source = source;
    }
}
