import org.junit.Test;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.macrossx.embedded.BootServer;
import com.macrossx.embedded.jetty.JettyModule;
import com.macrossx.embedded.jetty.JettyServer;

import junit.framework.TestCase;

/**
 * Copyright (C) 2016 X-Forever.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writi	ng, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class TestStart extends TestCase{
	@Test
	public static void bootJetty(){
		BootServer server = Guice.createInjector(new Module(){
			@Override
			public void configure(Binder binder) {
				binder.bind(BootServer.class).to(JettyServer.class);
			}},new JettyModule()).getInstance(BootServer.class);
		server.run();
	}

}
