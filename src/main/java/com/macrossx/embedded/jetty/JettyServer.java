package com.macrossx.embedded.jetty;

import java.io.File;
import java.io.FileInputStream;
import java.util.EnumSet;
import java.util.EventListener;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.xml.XmlConfiguration;

import com.google.inject.Inject;
import com.macrossx.embedded.BootServer;
import com.macrossx.embedded.EmbeddedServerException;

import lombok.extern.java.Log;

@Log
public class JettyServer implements BootServer {
	@Inject
	private Server server;
	@Inject
	private EventListener eventListener;

	@Override
	public void run() throws EmbeddedServerException {
		try {
			//	new XmlConfiguration("F:\\synctoy\\GitHub\\macrossx-guice-embedded\\bin\\jetty\\jetty.xml").configure(server);
			new XmlConfiguration(new FileInputStream(this.getClass().getResource("/").getPath() + "jetty/jetty.xml")).configure(server);
			ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
			servletContextHandler.addFilter(com.google.inject.servlet.GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
			servletContextHandler.addEventListener(eventListener);
			log.info("listen on port:" + ((ServerConnector) server.getConnectors()[0]).getPort());
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
