package org.apache.maven.index.creator;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0    
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;

import org.apache.maven.index.ArtifactContext;
import org.apache.maven.index.ArtifactInfo;
import org.apache.maven.index.context.IndexCreator;
import org.codehaus.plexus.PlexusTestCase;

/**
 * @author Alin Dreghiciu
 */
public class JarFileContentsIndexCreatorTest
    extends PlexusTestCase
{
    protected IndexCreator indexCreator;

    @Override
    public void setUp()
        throws Exception
    {
        super.setUp();

        indexCreator = this.lookup( IndexCreator.class, "jarContent" );
    }

    public void test_nexus_2318_indexJarWithClasses()
        throws Exception
    {
        File artifact = new File( getBasedir(), "src/test/nexus-2318/aopalliance/aopalliance/1.0/aopalliance-1.0.jar" );

        File pom = new File( getBasedir(), "src/test/nexus-2318/aopalliance/aopalliance/1.0/aopalliance-1.0.pom" );

        ArtifactInfo artifactInfo = new ArtifactInfo( "test", "aopalliance", "aopalliance", "1.0", null );

        ArtifactContext artifactContext = new ArtifactContext( pom, artifact, null, artifactInfo, null );

        indexCreator.populateArtifactInfo( artifactContext );

        assertNotNull( "Classes should not be null", artifactContext.getArtifactInfo().classNames );
    }

    public void test_nexus_2318_indexJarWithSources()
        throws Exception
    {
        File artifact =
            new File( getBasedir(), "src/test/nexus-2318/aopalliance/aopalliance/1.0/aopalliance-1.0-sources.jar" );

        File pom = new File( getBasedir(), "src/test/nexus-2318/aopalliance/aopalliance/1.0/aopalliance-1.0.pom" );

        ArtifactInfo artifactInfo = new ArtifactInfo( "test", "aopalliance", "aopalliance", "1.0", null );

        ArtifactContext artifactContext = new ArtifactContext( pom, artifact, null, artifactInfo, null );

        indexCreator.populateArtifactInfo( artifactContext );

        assertNull( "Classes should be null", artifactContext.getArtifactInfo().classNames );
    }

}
