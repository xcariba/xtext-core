/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtext.wizard

import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

import static org.eclipse.xtext.xtext.wizard.ExternalDependency.*

@FinalFieldsConstructor
class IdeProjectDescriptor extends ProjectDescriptor {
	
	override getNameQualifier() {
		".ide"
	}

	override getUpstreamProjects() {
		#{config.runtimeProject}
	}
	
	override isEclipsePluginProject() {
		config.preferredBuildSystem == BuildSystem.NONE || config.uiProject.enabled
	}
	
	override isPartOfGradleBuild() {
		true
	}
	
	override isPartOfMavenBuild() {
		true
	}
	
	override getExternalDependencies() {
		val deps = newLinkedHashSet
		deps += super.externalDependencies
		deps += createXtextDependency("org.eclipse.xtext.ide")
		deps += createXtextDependency("org.eclipse.xtext.xbase.ide")
		deps
	}
	
	override pom() {
		super.pom => [
			buildSection = '''
				«IF config.isCreateLanguageServerJar && isEclipsePluginProject»
					<dependencies>
						<dependency>
							<groupId>log4j</groupId>
							<artifactId>log4j</artifactId>
							<version>1.2.16</version>
						</dependency>
						<dependency>
							<groupId>org.eclipse.lsp4j</groupId>
							<artifactId>org.eclipse.lsp4j</artifactId>
							<version>0.2.1</version>
						</dependency>
						<dependency>
							<groupId>org.ow2.asm</groupId>
							<artifactId>asm</artifactId>
							<version>5.0.1</version>
						</dependency>
						<dependency>
							<groupId>org.ow2.asm</groupId>
							<artifactId>asm-commons</artifactId>
							<version>5.0.1</version>
						</dependency>
						<dependency>
							<groupId>org.ow2.asm</groupId>
							<artifactId>asm-tree</artifactId>
							<version>5.0.1</version>
						</dependency>
					</dependencies>
					
				«ENDIF»
				<build>
					«IF !isEclipsePluginProject && config.sourceLayout == SourceLayout.PLAIN»
						<sourceDirectory>«Outlet.MAIN_JAVA.sourceFolder»</sourceDirectory>
						<resources>
							<resource>
								<directory>«Outlet.MAIN_RESOURCES.sourceFolder»</directory>
								<excludes>
									<exclude>**/*.java</exclude>
									<exclude>**/*.xtend</exclude>
								</excludes>
							</resource>
						</resources>
					«ENDIF»
					<plugins>
						<plugin>
							<groupId>org.eclipse.xtend</groupId>
							<artifactId>xtend-maven-plugin</artifactId>
						</plugin>
						«IF !isEclipsePluginProject»
							<plugin>
								<groupId>org.codehaus.mojo</groupId>
								<artifactId>build-helper-maven-plugin</artifactId>
								<version>1.9.1</version>
								<executions>
									<execution>
										<id>add-source</id>
										<phase>initialize</phase>
										<goals>
											<goal>add-source</goal>
											<goal>add-resource</goal>
										</goals>
										<configuration>
											<sources>
												<source>«Outlet.MAIN_SRC_GEN.sourceFolder»</source>
											</sources>
											<resources>
												<resource>
													<directory>«Outlet.MAIN_SRC_GEN.sourceFolder»</directory>
													<excludes>
														<exclude>**/*.java</exclude>
														<exclude>**/*.g</exclude>
													</excludes>
												</resource>
											</resources>
										</configuration>
									</execution>
								</executions>
							</plugin>
						«ENDIF»
						«IF config.createLanguageServerJar»
							«IF isEclipsePluginProject»
								<plugin>
									<groupId>org.eclipse.tycho</groupId>
									<artifactId>target-platform-configuration</artifactId>
									<configuration>
										<pomDependencies>consider</pomDependencies>
									</configuration>
								</plugin>
								<plugin>
									<groupId>org.apache.maven.plugins</groupId>
									<artifactId>maven-dependency-plugin</artifactId>
									<version>3.0.1</version>
									<executions>
										<execution>
											<id>copy-dependencies</id>
											<phase>package</phase>
											<goals>
												<goal>copy-dependencies</goal>
											</goals>
											<configuration>
												<outputDirectory>${project.build.directory}/libs</outputDirectory>
												<overWriteReleases>false</overWriteReleases>
												<overWriteSnapshots>false</overWriteSnapshots>
												<overWriteIfNewer>true</overWriteIfNewer>
												<excludeTransitive>true</excludeTransitive>
												<includeArtifactIds>
													 «config.runtimeProject.name»
													,org.eclipse.xtext
													,org.eclipse.xtext.util
													,org.eclipse.xtext.ide
													,org.eclipse.xtext.xbase
													,org.eclipse.xtext.xbase.ide
													,org.eclipse.xtext.xbase.lib
													,org.eclipse.xtext.common.types
													,org.eclipse.xtend.lib
													,org.eclipse.xtend.lib.macro
													,org.eclipse.osgi
													,org.eclipse.equinox.common
													,org.antlr.runtime
													,com.google.inject
													,com.google.guava
													,javax.inject
													,org.eclipse.emf.common
													,org.eclipse.emf.ecore
													,org.eclipse.emf.ecore.xmi
													,org.eclipse.lsp4j
													,org.eclipse.generator
													,org.eclipse.jsonrpc
													,gson
													,log4j
													,asm
													,asm-tree
													,asm-commons
													,aopalliance
												</includeArtifactIds>
											</configuration>
										</execution>
									</executions>
								</plugin>
								<plugin>
									<groupId>com.googlecode.addjars-maven-plugin</groupId>
									<artifactId>addjars-maven-plugin</artifactId>
									<version>1.0.5</version>
									<executions>
										<execution>
											<phase>package</phase>
											<goals>
												<goal>add-jars</goal>
											</goals>
											<configuration>
												<resources>
													<resource>
														<directory>${project.build.directory}/libs</directory>
													</resource>
												</resources>
											</configuration>
										</execution>
									</executions>
								</plugin>
							«ENDIF»
							<plugin>
								<groupId>org.apache.maven.plugins</groupId>
								<artifactId>maven-shade-plugin</artifactId>
								<version>3.0.0</version>
								<configuration>
									<transformers>
										<transformer
											implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
											<mainClass>org.eclipse.xtext.ide.server.ServerLauncher</mainClass>
										</transformer>
										<transformer
											implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
											<resource>plugin.properties</resource>
										</transformer>
									</transformers>
									«IF isEclipsePluginProject»
										<artifactSet>
											<excludes><!-- avoid duplicate inclusion due to addjars plugin -->
												<exclude>*:«config.ideProject.name»-org.eclipse.lsp4j*</exclude>
												<exclude>*:«config.ideProject.name»-org.eclipse.xtext.xbase.lib*</exclude>
												<exclude>*:«config.ideProject.name»-org.eclipse.xtend.lib*</exclude>
												<exclude>*:«config.ideProject.name»-com.google.guava*</exclude>
												<exclude>*:«config.ideProject.name»-asm*</exclude>
												<exclude>*:«config.ideProject.name»-log4j*</exclude>
											</excludes>
										</artifactSet>
									«ENDIF»
									<filters>
										<filter>
											<artifact>*:*</artifact>
											<excludes>
												<exclude>META-INF/INDEX.LIST</exclude>
												<exclude>META-INF/*.SF</exclude>
												<exclude>META-INF/*.DSA</exclude>
												<exclude>META-INF/*.RSA</exclude>
												<exclude>.options</exclude>
												<exclude>.api_description</exclude>
												<exclude>*.profile</exclude>
												<exclude>*.html</exclude>
												<exclude>about.*</exclude>
												<exclude>about_files/*</exclude>
												<exclude>plugin.xml</exclude>
												<exclude>modeling32.png</exclude>
												<exclude>systembundle.properties</exclude>
												<exclude>profile.list</exclude>
												<exclude>**/*._trace</exclude>
												<exclude>**/*.g</exclude>
												<exclude>**/*.tokens</exclude>
												<exclude>**/*.mwe2</exclude>
												<exclude>**/*.xtext</exclude>
												<exclude>**/*.xtextbin</exclude>
											</excludes>
										</filter>
									</filters>
									<shadedArtifactAttached>true</shadedArtifactAttached>
									<shadedClassifierName>ls</shadedClassifierName>
									<minimizeJar>false</minimizeJar>
								</configuration>
								<executions>
									<execution>
										<phase>package</phase>
										<goals>
											<goal>shade</goal>
										</goals>
									</execution>
								</executions>
							</plugin>
						«ENDIF»
					</plugins>
				</build>
			'''
			packaging = if (isEclipsePluginProject) "eclipse-plugin" else "jar"
		]
	}

	override buildGradle() {
		super.buildGradle => [
			pluginsSection = '''
				«IF config.createLanguageServerJar»
					plugins {
						id 'com.github.johnrengelman.shadow' version '2.0.0'
					}
					
				«ENDIF»
			'''
			additionalContent = '''
				«IF config.createLanguageServerJar»
					
					apply plugin: 'application'
					apply plugin: 'com.github.johnrengelman.shadow'
					mainClassName = "org.eclipse.xtext.ide.server.ServerLauncher"
					
					shadowJar {
						from(project.convention.getPlugin(JavaPluginConvention).sourceSets.main.output)
						configurations = [project.configurations.runtime]
						exclude('META-INF/INDEX.LIST', 'META-INF/*.SF', 'META-INF/*.DSA', 'META-INF/*.RSA','schema/*',
							'.options', '.api_description', '*.profile', '*.html', 'about.*', 'about_files/*',
							'plugin.xml', 'modeling32.png', 'systembundle.properties', 'profile.list')
						classifier = 'ls'
						append('plugin.properties')
					}
				«ENDIF»
			'''
		]
	}

}