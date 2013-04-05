/****************************************************************************
 * Copyright (c) 2011, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *      tware - 
 ******************************************************************************/
package org.eclipse.persistence.jpars.test.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.core.util.StringKeyIgnoreCaseMultivaluedMap;

public class TestURIInfo implements UriInfo {

    protected List<PathSegment> pathSegments = new ArrayList<PathSegment>();
    protected MultivaluedMap<String, String> queryParameters = new StringKeyIgnoreCaseMultivaluedMap<String>();
    
    public TestURIInfo(){
    }
    
    @Override
    public URI getAbsolutePath() {
        return null;
    }

    @Override
    public UriBuilder getAbsolutePathBuilder() {
        return null;
    }

    @Override
    public URI getBaseUri() {
        try {
            return RestUtils.getServerURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UriBuilder getBaseUriBuilder() {
        return null;
    }

    @Override
    public List<Object> getMatchedResources() {
        return null;
    }

    @Override
    public List<String> getMatchedURIs() {
        return null;
    }

    @Override
    public List<String> getMatchedURIs(boolean arg0) {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public String getPath(boolean arg0) {
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getPathParameters() {
        return null;
    }

    @Override
    public MultivaluedMap<String, String> getPathParameters(boolean arg0) {
        return null;
    }

    @Override
    public List<PathSegment> getPathSegments() {
        return pathSegments;
    }

    @Override
    public List<PathSegment> getPathSegments(boolean arg0) {
        return pathSegments;
    }

    @Override
    public MultivaluedMap<String, String> getQueryParameters() {
        return queryParameters;
    }

    @Override
    public MultivaluedMap<String, String> getQueryParameters(boolean arg0) {
        return queryParameters;
    }

    @Override
    public URI getRequestUri() {
        return null;
    }

    @Override
    public UriBuilder getRequestUriBuilder() {
        return null;
    }
    
    public void addMatrixParameter(String segment, String key, String value){
        PathSegment segmentToUse = null;
        for (PathSegment pathSegment: pathSegments){
            if (pathSegment.getPath().equals(segment)){
                segmentToUse = pathSegment;
            }
        }
        if (segmentToUse == null){
            segmentToUse = new TestPathSegment(segment);
            pathSegments.add(segmentToUse);
        }
        
        List<String> parameters = segmentToUse.getMatrixParameters().get(key);
        if (parameters == null){
            parameters = new ArrayList<String>();
        }
        parameters.add(value);
        segmentToUse.getMatrixParameters().put(key, parameters);
    }
    
    public class TestPathSegment implements PathSegment{
        private MultivaluedMap<String, String> matrixParameters = new MultivaluedMapImpl();
        private String path;
        
        public TestPathSegment(String path){
            this.path = path;
        }

            
        @Override
        public String getPath() {
            return path;
        }
                
        @Override
        public MultivaluedMap<String, String> getMatrixParameters() {
            // TODO Auto-generated method stub
            return matrixParameters;
        }
    }
}
