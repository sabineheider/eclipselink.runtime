/*******************************************************************************
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     24 April 2013-2.5.1 ailitche
 *       SerializedObjectPolicy initial API and implementation
 ******************************************************************************/  
package org.eclipse.persistence.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.persistence.Column;

/**
 * SerializedObject annotation is used to set an 
 * org.eclipse.persistence.descriptors.SerializedObjectPolicy on an Entity or MappedSuperClass.
 * 
 * If SerializedObjectPolicy is specified Eclipselink writes out the whole entity object with its
 * privately owned (and nested privately owned) entities and element collections into an additional 
 * (likely BLOB) field in the database. That field could be specified in the annotation, it defaults to "SOP" in the main table. 
 * 
 * <p>
 * Examples:
 * <code><pre>
 * @Entity
 * @SerializedObject(MySerializedObjectPolicy.class); 
 * public class Employee {...
 * </pre></code>
 * <code><pre>
 * @Entity
 * @SerializedObject(value = MySerializedObjectPolicy.class, column = @Column(name="SERIALIZED")); 
 * public class Address {...
 * </pre></code>
 * 
 * If SerializedObjectPolicy is set on an entity then SerializedObjectPolicies with the same field are set
 * on all inheriting entities.
 * 
 * The query that uses SerializedObjectPolicy extracts the whole object from that field.
 * To read object(s) using SerializedObjectPolicy the query should specify
 * @see QueryHints#SERIALIZED_OBJECT
 *  
 * <p>
 * Examples:
 * <code><pre>
 * Query query = em.createQuery("SELECT e FROM Employee e").setHint(QueryHints.SERIALIZED_OBJECT, "true");
 * </pre></code>
 * <code><pre>
 * Map hints = new HashMap();
 * hints.put("eclipselink.serialized-object", "true");
 * Address address = em.find(Address.class, id, hints);
 * </pre></code>
 *
 * The goal is to make reads from the database faster.
 * The draw back is slower writes into the database.
 * So SerializedObjectPolicy may make sense for read-only / read-mostly application 
 * for Entity, which always loads all its dependent entities and / or ElementCollections.
 * 
 * In case the serialized object column contains null or obsolete version of the object
 * the query using SerializedObjectPolicy would either throw exception or - if all other fields have been read, too -
 * would build the object using these fields (exactly as in case SerializedObjectPolicy is not used). 
 * 
 * Note that currently no default implementation of SerializedObjectPolicy is available
 * and this class should be provided by the user.
 * 
 * @see org.eclipse.persistence.descriptors.SerializedObject
 * 
 * @author ailitche
 * @since EclipseLink 2.5.1
 * 
 * @see SerializedObjectPolicy
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface SerializedObject {
    /**
     * The Class that implements org.eclipse.persistence.descriptors.SerializedObjectPolicy interface.
     * This class must be specified. 
     */ 
    Class value();
    
    /**
     * (Optional) The column that holds the serialized object. By default it's a BLOB column named "SOP" in entity's main table. 
     */
    Column column() default @Column(name="SOP");
}
