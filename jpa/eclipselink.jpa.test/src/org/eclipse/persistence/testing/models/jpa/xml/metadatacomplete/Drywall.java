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
 *     12/01/2010-2.2 Guy Pelletier 
 *       - 331234: xml-mapping-metadata-complete overriden by metadata-complete specification 
 ******************************************************************************/
package org.eclipse.persistence.testing.models.jpa.xml.metadatacomplete;

import javax.persistence.Column;

/**
 * Included in the following mapping files:
 * - eclipselink-xml-only-model/metadata-complete-mappings.
 * - eclipselink-xml-only-model/xml-mapping-metadata-complete.
 * 
 * Which are used in two separate persistence units from:
 * - eclipselink-annotation-model/persistence.xml, namely:
 *   - METADATA_COMPLETE
 *   - XML_MAPPING_METADATA_COMPLETE
 *  
 * @author gpelleti
 */
public class Drywall extends Material {
    @Column(name="IGNORED_LENGTH")
    public int length;
    
    @Column(name="IGNORED_WIDTH")
    public int width;
    
    public Drywall() {}
}
