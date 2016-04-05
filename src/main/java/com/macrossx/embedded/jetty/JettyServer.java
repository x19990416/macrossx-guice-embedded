package com.macrossx.embedded.jetty;

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
	private ServletContextHandler handler;
	@Inject
	private EventListener eventListener;

	@Override
	public void run() throws EmbeddedServerException {
		try {
			new XmlConfiguration("./jetty/jetty.xml").configure(server);
			handler.setContextPath("/*");
			handler.addFilter(com.google.inject.servlet.GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST,
					DispatcherType.ASYNC, DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.INCLUDE));
			handler.addEventListener(eventListener);
			log.info("listen on port:" + ((ServerConnector) server.getConnectors()[0]).getPort());
			server.start();
			server.join();
		} catch (Exception e) {
			throw new EmbeddedServerException(e);
		}

	}

}
