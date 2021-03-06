/*******************************************************************************
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ide.server

import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.workspace.FileProjectConfig
import org.eclipse.xtext.workspace.FileSourceFolder
import org.eclipse.xtext.workspace.IWorkspaceConfig
import org.eclipse.xtext.workspace.InMemoryProjectConfig

/**
 * @author Jan Koehnlein - Initial contribution and API
 * @since 2.11
 */
interface IWorkspaceConfigFactory {
    
    def IWorkspaceConfig getWorkspaceConfig(URI workspaceBaseURI)
    
}

/**
 * The workspace itself is a single project
 * 
 * @author Jan Koehnlein - Initial contribution and API
 * @since 2.11
 */
class ProjectWorkspaceConfigFactory implements IWorkspaceConfigFactory {
    
    override getWorkspaceConfig(URI workspaceBaseURI) {
    	if (workspaceBaseURI === null) {
			val projectConfig = new InMemoryProjectConfig
			return projectConfig.workspaceConfig
    	} else {
	        val projectConfig = new FileProjectConfig(workspaceBaseURI)
	        projectConfig.sourceFolders += new FileSourceFolder(projectConfig, '.')
	        return projectConfig.workspaceConfig
        }
    }
    
}
