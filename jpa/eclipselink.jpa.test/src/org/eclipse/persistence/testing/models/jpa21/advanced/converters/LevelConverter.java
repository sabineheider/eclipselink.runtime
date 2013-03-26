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
 *     10/25/2012-2.5 Guy Pelletier 
 *       - 374688: JPA 2.1 Converter support
 ******************************************************************************/ 
package org.eclipse.persistence.testing.models.jpa21.advanced.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.eclipse.persistence.testing.models.jpa21.advanced.enums.Level;

@Converter(autoApply=false)
public class LevelConverter implements AttributeConverter<Level, String> {
    
    @Override
    public String convertToDatabaseColumn(Level level) {
        if (level == null) {
            return null;
        } else if (level.equals(Level.A)) {
            return Level.AMATEUR.name();
        } else if (level.equals(Level.E)) {
            return Level.ELITE.name();
        } else {
            return level.name();
        } 
    }

    @Override
    public Level convertToEntityAttribute(String dbData) {
        return (dbData == null) ? null : Level.valueOf(dbData);
    }
}

