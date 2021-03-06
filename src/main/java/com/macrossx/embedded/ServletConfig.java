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
package com.macrossx.embedded;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;

import lombok.extern.java.Log;
@Log
public abstract class ServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		// log.info(String.format("%d", moduleContainer.get().size()));
		log.info("calling ServletConfig.....");
		Injector newInjector = Guice.createInjector(Stage.PRODUCTION, this.provider());
		return newInjector;
	}
	
	public abstract List<Module> provider();
}