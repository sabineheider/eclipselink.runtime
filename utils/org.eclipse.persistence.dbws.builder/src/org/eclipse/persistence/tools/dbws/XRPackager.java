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
 *     Mike Norman - May 06 2008, created DBWS tools package
 ******************************************************************************/

package org.eclipse.persistence.tools.dbws;

//javase imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//EclipseLink imports
import org.eclipse.persistence.Version;
import org.eclipse.persistence.exceptions.DBWSException;
import org.eclipse.persistence.internal.sessions.factories.model.SessionConfigs;
import org.eclipse.persistence.internal.sessions.factories.model.log.DefaultSessionLogConfig;
import org.eclipse.persistence.internal.sessions.factories.model.login.DatabaseLoginConfig;
import org.eclipse.persistence.internal.sessions.factories.model.session.DatabaseSessionConfig;

import static org.eclipse.persistence.internal.xr.Util.DASH_STR;
import static org.eclipse.persistence.internal.xr.Util.DBWS_OR_SESSION_NAME_SUFFIX;
import static org.eclipse.persistence.internal.xr.Util.DBWS_OX_SESSION_NAME_SUFFIX;
import static org.eclipse.persistence.internal.xr.Util.DBWS_OR_XML;
import static org.eclipse.persistence.internal.xr.Util.DBWS_OX_XML;
import static org.eclipse.persistence.internal.xr.Util.DBWS_SCHEMA_XML;
import static org.eclipse.persistence.internal.xr.Util.DBWS_SERVICE_XML;
import static org.eclipse.persistence.internal.xr.Util.EMPTY_STR;
import static org.eclipse.persistence.tools.dbws.DBWSPackager.ArchiveUse.noArchive;
import static org.eclipse.persistence.tools.dbws.Util.SWAREF_FILENAME;

/**
 * <p>
 * <b>PUBLIC:</b> XRPackager implements the {@link DBWSPackager} interface. This packager is <br>
 * responsible for generating the core X-R metadata files and is the root class from which all <br>
 * all other packagers inherit.
 * <p>
 * By default, this packager does not use an archiver and writes out its files 'flat' to the stageDir:
 * <pre>
 * ${PACKAGER_ROOT}
 *    |   eclipselink-dbws.xml             -- fixed naming convention
 *    |   eclipselink-dbws-or.xml
 *    |   eclipselink-dbws-ox.xml
 *    |   eclipselink-dbws-schema.xsd
 *    |   eclipselink-dbws-sessions.xml    -- name can be overriden by <sessions-file> entry in eclipselink-dbws.xml
 *    |   <i>swaref.xsd</i>                       -- optional if attachements are enabled
 * </pre>
 *
 * @author Mike Norman - michael.norman@oracle.com
 * @since EclipseLink 1.x
 */
public class XRPackager implements DBWSPackager {

    // some packagers do not need to write out some files -
    // this do-nothing/go-nowhere stream handles that case
    protected static class NullOutputStream extends OutputStream {
            @Override
            public void close(){}
            @Override
            public void flush(){}
            @Override
            public void write(byte[]b){}
            @Override
            public void write(byte[]b,int i,int l){}
            @Override
            public void write(int b){}
    }
    public static NullOutputStream __nullStream = new NullOutputStream();

    protected DBWSBuilder builder;
    protected String[] additionalArgs;
    protected File stageDir;
    protected String sessionsFileName;
    protected boolean hasAttachments;
    protected Archiver archiver;
    protected String packagerLabel;
    protected ArchiveUse archiveUse;
    
    protected static String OFF = "off";
    protected static String SEVERE = "severe";
    protected static String WARNING = "warning";
    protected static String INFO = "info";
    protected static String CONFIG = "config";
    protected static String FINE = "fine";
    protected static String FINER = "finer";
    protected static String FINEST = "finest";
    protected static String ALL = "all";
    protected static String XR_STR = "xr";
    
    public XRPackager() {
        this(null, XR_STR, noArchive);
    }
    protected XRPackager(Archiver archiver, String packagerLabel, ArchiveUse useJavaArchive) {
        this.archiver = archiver;
        if (archiver != null) {
            archiver.setPackager(this);
        }
        this.packagerLabel = packagerLabel;
        this.archiveUse = useJavaArchive;
    }

    public void setDBWSBuilder(DBWSBuilder builder) {
        this.builder = builder;
    }

    public void setAdditionalArgs(String[] additionalArgs) {
        this.additionalArgs = additionalArgs;
        if (additionalArgs != null) {
            processAdditionalArgs();
        }
    }

    public void processAdditionalArgs() {
        if (additionalArgs.length > 0) {
            setArchiveFilename(additionalArgs[0]);
        }
        else {
            setArchiveFilename(builder.getProjectName());
        }
    }
    public File getStageDir() {
        return this.stageDir;
    }
    public void setStageDir(File stageDir) {
        this.stageDir = stageDir;
    }

    public String getSessionsFileName() {
        return sessionsFileName;
    }
    public void setSessionsFileName(String sessionsFileName) {
        this.sessionsFileName = sessionsFileName;
    }

    public boolean hasAttachments() {
        return hasAttachments;
    }
    public void setHasAttachments(boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    public Archiver getArchiver() {
        return archiver;
    }
    public void setArchiver(Archiver archiver) {
        this.archiver = archiver;
    }
    public void setArchiveUse(ArchiveUse packagerUse) {
        this.archiveUse = packagerUse;
        processArchiveUse();
    }
    public void processArchiveUse() {
        switch (archiveUse) {
            case archive:
                setArchiver(buildDefaultArchiver());
                break;
            case noArchive:
                setArchiver(null);
            case ignore:
                // do nothing - the default setting from the constructor is fine
                break;
        }
    }
    public Archiver buildDefaultArchiver() {
        return null;
    }
    public String getArchiveFilename() {
        if (archiver != null) {
            return archiver.getFilename();
        }
        return null;
    }
    public void setArchiveFilename(String archiveFilename) {
        if (archiver != null) {
            archiver.setFilename(archiveFilename);
        }
    }

    public String getPackagerLabel() {
        return packagerLabel;
    }
    public String getArchiverLabel() {
        return archiveUse.name();
    }
    public String getUsage() {
        StringBuilder sb = new StringBuilder("-packageAs:[default=");
        sb.append(getArchiverLabel());
        sb.append("] ");
        sb.append(getPackagerLabel());
        String additionalUsage = getAdditionalUsage();
        if (additionalUsage != null) {
            sb.append(additionalUsage);
        }
        return sb.toString();
    }
    public String getAdditionalUsage() {
        return " [jarFilename]";
    }

    public void start() {
        if (stageDir == null) {
            throw new DBWSException(this.getClass().getSimpleName() + " stageDir cannot be null");
        }
    }

    public OutputStream getSchemaStream() throws FileNotFoundException {
        return new FileOutputStream(new File(stageDir, DBWS_SCHEMA_XML));
    }
    public void closeSchemaStream(OutputStream schemaStream) {
        closeStream(schemaStream);
    }

    public OutputStream getSessionsStream(String sessionsFileName) throws FileNotFoundException {
        return new FileOutputStream(new File(stageDir, sessionsFileName));
    }
    public SessionConfigs buildSessionsXML(OutputStream dbwsSessionsStream, DBWSBuilder builder) {
        // build basic sessions.xml - no server platform settings, no Datasource settings
        SessionConfigs ts =    new SessionConfigs();
        ts.setVersion(Version.getVersion());
        DatabaseSessionConfig orSessionConfig = new DatabaseSessionConfig();
        String projectName = builder.getProjectName();
        orSessionConfig.setName(projectName + DASH_STR + DBWS_OR_SESSION_NAME_SUFFIX);
        String orSessionCustomizerClassName = builder.getOrSessionCustomizerClassName();
        if (orSessionCustomizerClassName != null && !EMPTY_STR.equals(orSessionCustomizerClassName)) {
            orSessionConfig.setSessionCustomizerClass(orSessionCustomizerClassName);
        }
        DatabaseLoginConfig dlc = new DatabaseLoginConfig();
        dlc.setBindAllParameters(true);
        dlc.setStreamsForBinding(true);
        dlc.setJdbcBatchWriting(true);
        dlc.setConnectionURL(builder.getUrl());
        dlc.setDriverClass(builder.getDriver());
        dlc.setUsername(builder.getUsername());
        dlc.setEncryptedPassword(builder.getPassword());
        dlc.setPlatformClass(builder.getPlatformClassname());
        orSessionConfig.setLoginConfig(dlc);
        DefaultSessionLogConfig orLogConfig = new DefaultSessionLogConfig();
        // validate log level - default to 'info' if invalid
        String logLevel = builder.getLogLevel();
    	if (!isValidLogLevel(logLevel)) {
    		builder.logMessage(java.util.logging.Level.WARNING, "Log level [" + logLevel + "] is invalid.  Valid values are [off, severe, warning, info, config, fine, finer, finest, all].  Using default log level [info].");
       		logLevel = INFO;
        }
        orLogConfig.setLogLevel(logLevel);
        orSessionConfig.setLogConfig(orLogConfig);
        ts.addSessionConfig(orSessionConfig);

        DatabaseSessionConfig oxSessionConfig = new DatabaseSessionConfig();
        oxSessionConfig.setName(projectName + DASH_STR + DBWS_OX_SESSION_NAME_SUFFIX);
        DefaultSessionLogConfig oxLogConfig = new DefaultSessionLogConfig();
        oxLogConfig.setLogLevel(OFF);
        oxSessionConfig.setLogConfig(oxLogConfig);
        String oxSessionCustomizerClassName = builder.getOxSessionCustomizerClassName();
        if (oxSessionCustomizerClassName != null && !EMPTY_STR.equals(oxSessionCustomizerClassName)) {
            oxSessionConfig.setSessionCustomizerClass(oxSessionCustomizerClassName);
        }
        ts.addSessionConfig(oxSessionConfig);
        
        return ts;
    }

    public void closeSessionsStream(OutputStream sessionsStream) {
        closeStream(sessionsStream);
    }

    public OutputStream getServiceStream() throws FileNotFoundException {
        return new FileOutputStream(new File(stageDir, DBWS_SERVICE_XML));
    }
    public void closeServiceStream(OutputStream serviceStream) {
        closeStream(serviceStream);
    }

    public OutputStream getOrStream() throws FileNotFoundException {
        return new FileOutputStream(new File(stageDir, DBWS_OR_XML));
    }
    public String getOrProjectPathPrefix() {
        if (archiver == null) {
            return null;
        }
        return archiver.getOrProjectPathPrefix();
    }
    public void closeOrStream(OutputStream orStream) {
        closeStream(orStream);
    }

    public OutputStream getOxStream() throws FileNotFoundException {
        return new FileOutputStream(new File(stageDir, DBWS_OX_XML));
    }
    public String getOxProjectPathPrefix() {
        if (archiver == null) {
            return null;
        }
        return archiver.getOxProjectPathPrefix();
    }
    public void closeOxStream(OutputStream oxStream) {
        closeStream(oxStream);
    }

    public OutputStream getWSDLStream() throws FileNotFoundException {
        return __nullStream;
    }
    public String getWSDLPathPrefix() {
        if (archiver == null) {
            return null;
        }
        return archiver.getWSDLPathPrefix();
    }
    public void closeWSDLStream(OutputStream wsdlStream) {
        closeStream(wsdlStream);
    }

    public OutputStream getSWARefStream() throws FileNotFoundException {
        if (!hasAttachments) {
            return __nullStream;
        }
        return new FileOutputStream(new File(stageDir, SWAREF_FILENAME));
    }
    public void closeSWARefStream(OutputStream swarefStream) {
        closeStream(swarefStream);
    }

    public OutputStream getWebXmlStream() throws FileNotFoundException {
        return __nullStream;
    }
    public void writeWebXml(OutputStream webXmlStream, DBWSBuilder dbwsBuilder) {
    }
    public void closeWebXmlStream(OutputStream webXmlStream) {
        closeStream(webXmlStream);
    }

    public OutputStream getProviderSourceStream() throws FileNotFoundException {
        return __nullStream;
    }
    public void closeProviderSourceStream(OutputStream sourceProviderStream) {
        closeStream(sourceProviderStream);
    }

    public OutputStream getProviderClassStream() throws FileNotFoundException {
        return __nullStream;
    }
    public void closeProviderClassStream(OutputStream classProviderStream) {
        closeStream(classProviderStream);
    }

    public OutputStream getProviderListenerSourceStream() throws FileNotFoundException {
        return __nullStream;
    }
    public void closeProviderListenerSourceStream(OutputStream sourceProviderListenerStream) {
        closeStream(sourceProviderListenerStream);
    }

    public OutputStream getProviderListenerClassStream() throws FileNotFoundException {
        return __nullStream;
    }
    public void closeProviderListenerClassStream(OutputStream classProviderListenerStream) {
        closeStream(classProviderListenerStream);
    }

    @Override
    public void writeProvider(OutputStream sourceProviderStream, OutputStream classProviderStream,
        OutputStream sourceProviderListenerStream, OutputStream classProviderListenerStream,
        DBWSBuilder builder) {
        // no-op
    }

    protected void closeStream(OutputStream outputStream) {
        if (outputStream != null && outputStream != __nullStream) {
            try {
                outputStream.flush();
                outputStream.close();
            }
            catch (IOException e) {/* ignore */}
        }
    }

    public void end() {
        if (archiver != null) {
            archiver.archive();
        }
    }
    
    /**
     * Write the deployment descriptor contents to the provided OutputStream.
     */
    @Override
    public void writeDeploymentDescriptor(OutputStream descriptorOutputStream) {
        // no-op
    }    
    /**
     * Return an OutputStream to the deployment descriptor.  Deployment descriptor
     * is optional, so return a null stream.
     */
    @Override
    public OutputStream getDeploymentDescriptorStream() throws FileNotFoundException {
        return __nullStream;
    }
    /**
     * Closes the given OutputStream.
     */
    @Override
    public void closeDeploymentDescriptorStream(OutputStream descriptorOutputStream) {
        closeStream(descriptorOutputStream);
    }
    /**
     * Return the name of the deployment descriptor file - this will depend on the
     * target application server.  Since the deployment descriptor is optional,
     * return null.
     */
    @Override
    public String getDeploymentDescriptorFileName() {
        return null;
    }
    
    /**
     * Validates user-set log level.  Valid values are: off, severe,
     * warning, info, config, fine, finer, finest, all.
     * 
     * If an invalid log level is set, a warning will be thrown
     * and the default level "fine" will be set.
     * 
     * @param logLevel
     */
    private boolean isValidLogLevel(String logLevel) {
    	return (logLevel.equalsIgnoreCase(OFF)
    		 || logLevel.equalsIgnoreCase(SEVERE) 
    		 || logLevel.equalsIgnoreCase(WARNING) 
    		 || logLevel.equalsIgnoreCase(INFO) 
    		 || logLevel.equalsIgnoreCase(CONFIG) 
    		 || logLevel.equalsIgnoreCase(FINE) 
    		 || logLevel.equalsIgnoreCase(FINER) 
    		 || logLevel.equalsIgnoreCase(FINEST) 
    		 || logLevel.equalsIgnoreCase(ALL));
    }
}