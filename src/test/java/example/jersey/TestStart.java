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
package example.jersey;

import java.util.EventListener;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.servlet.ServletModule;
import com.macrossx.embedded.BootServer;
import com.macrossx.embedded.ServletConfig;
import com.macrossx.embedded.jetty.JettyServer;
import com.sun.jersey.spi.container.servlet.ServletContainer;

import junit.framework.TestCase;

public class TestStart extends TestCase {
	public void testHelloworldJetty() {
		BootServer server = Guice.createInjector(new Module() {
			@Override
			public void configure(Binder binder) {
				binder.bind(BootServer.class).to(JettyServer.class);
				binder.bind(EventListener.class).toInstance(new ServletConfig() {

					@Override
					public List<AbstractModule> provider() {
						// TODO Auto-generated method stub
						return Lists.newArrayList(new HelloWorldServletModule());
					}
				});
			}
		}).getInstance(BootServer.class);
		server.run();
	}

	public static class HelloWorldServletModule extends ServletModule {
		@Override
		protected void configureServlets() {	
			bind(ServletContainer.class).asEagerSingleton();
			HashMap<String, String> param = Maps.newHashMap();
			param.put("com.sun.jersey.config.property.packages", "example.jersey");
			param.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
			serve("/service/*").with(com.sun.jersey.spi.container.servlet.ServletContainer.class,param);
		}
	}
}
