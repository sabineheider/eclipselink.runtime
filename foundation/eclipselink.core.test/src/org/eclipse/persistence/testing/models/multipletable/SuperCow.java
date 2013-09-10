/*******************************************************************************
 * Copyright (c) 1998, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributor:
 *     Andrei Ilitchev - bug 248858: Problem with Multiple tables in EclipseLink.
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.multipletable;

public class SuperCow extends Cow {
    protected int speed;
    
    public SuperCow() {
        super();
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public static SuperCow getSuperCow1(){
        SuperCow cow = new SuperCow();
        cow.setAge(1);
        cow.setAgeId(111);
        cow.setCalfCount(2);
        cow.setCalfCountId(222);
        cow.setName("CudChewer");
        cow.setWeight(432);
        cow.setWeightId(444);
        cow.setSpeed(10);
        return cow;
    }
 }
