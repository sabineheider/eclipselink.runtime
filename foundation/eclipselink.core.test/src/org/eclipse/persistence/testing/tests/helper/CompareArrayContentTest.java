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
package org.eclipse.persistence.testing.tests.helper;

import org.eclipse.persistence.internal.helper.*;
import org.eclipse.persistence.testing.framework.*;

public class CompareArrayContentTest extends AutoVerifyTestCase {
    Exception e;
    Integer[] array1 = new Integer[3];
    Integer[] array2 = new Integer[3];
    Integer[] array3 = new Integer[3];
    boolean test1ResultIsTrue = false;
    boolean test2ResultIsTrue = false;

    public CompareArrayContentTest() {
        setDescription("Test of Helper.compareArrays(Object[] array1, Object[] array2) method's comparison of object array contents.");
    }

    public static void main(String[] args) {
        CompareArrayContentTest x = new CompareArrayContentTest();
        x.setup();
        x.test();
        x.verify();
        x.reset();
    }

    public void reset() {
        array1 = null;
        array2 = null;
        array3 = null;
    }

    public void setup() {
        for (int count = 0; count < 3; count++) {
            Integer counter = new Integer(count);
            Integer counter2 = new Integer(count + 9);
            array1[count] = counter;
            array2[count] = counter;
            array3[count] = counter2;
        }
    }

    public void test() {
        try {
            test1ResultIsTrue = Helper.compareArrays(array1, array2);
            test2ResultIsTrue = Helper.compareArrays(array1, array3);

        } catch (Exception e) {
            this.e = e;
            throw new TestErrorException("An exception should not have been thrown when comparing object arrays.");
        }
    }

    public void verify() {
        //
        if (!test1ResultIsTrue) {
            throw new TestErrorException("Helper.compareArrays(Object[] array1, Object[] array2) does not recognize that object arrays contain identical elements.");
        }

        if (test2ResultIsTrue) {
            throw new TestErrorException("Helper.compareArrays(Object[] array1, Object[] array2) does not recognize that object arrays contain different elements.");
        }
        if (e != null) {
            throw new TestErrorException("An exception should not have been thrown when comparing object arrays: " + e.toString());
        }
    }
}
