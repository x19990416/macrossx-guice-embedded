/**
 * Copyright (C) 2016 X-Forever.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
	private EventListener eventListener;

	@Override
	public void run() throws EmbeddedServerException {
		try {
			this.init();
			new XmlConfiguration(this.getClass().getResourceAsStream("/jetty/jetty.xml")).configure(server);
			ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/",
					ServletContextHandler.SESSIONS);
			servletContextHandler.addFilter(com.google.inject.servlet.GuiceFilter.class, "/*",
					EnumSet.allOf(DispatcherType.class));
			servletContextHandler.addEventListener(eventListener);
			
			log.info("listen on port:" + ((ServerConnector) server.getConnectors()[0]).getPort());
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
