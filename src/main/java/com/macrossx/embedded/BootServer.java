package com.macrossx.embedded;
public interface BootServer {
	public void run() throws EmbeddedServerException;
	default public void init() throws EmbeddedServerException {
	}
}
