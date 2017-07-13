package org.eclipse.xtext.xtext.wizard;

public enum LanguageServer {
	NONE("None"),
	APP("Regular"),
	FATJAR("Fat Jar");
	
	private String humanReadableName;
	
	private LanguageServer(String humanReadableName) {
		this.humanReadableName = humanReadableName;
	}
	public String toString() {
		return humanReadableName;
	}
}
