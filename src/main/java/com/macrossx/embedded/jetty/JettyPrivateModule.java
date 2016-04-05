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

import java.util.EventListener;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.PrivateModule;
import com.macrossx.embedded.BootServer;
import com.macrossx.embedded.ServletConfig;

public class JettyPrivateModule extends PrivateModule	{
	
	@Override
	protected void configure() {
		bind(org.eclipse.jetty.server.Server.class).toInstance(new Server(8080));
		bind(Handler.class).to(ServletContextHandler.class);
		bind(EventListener.class).to(ServletConfig.class);
	}
	

}
