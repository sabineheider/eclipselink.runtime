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
 *     05/31/2010-2.1 Guy Pelletier 
 *       - 314941: multiple joinColumns without referenced column names defined, no error
 ******************************************************************************/ 
package org.eclipse.persistence.testing.models.jpa.advanced.compositepk;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import static javax.persistence.AccessType.FIELD;

@Embeddable
@Access(FIELD)
public class Competency {
    @Column(name="DESCRIP")
    public String description;
    
    @Column(name="RATING")
    public Integer rating;

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    
}
