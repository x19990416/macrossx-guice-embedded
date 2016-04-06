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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import lombok.extern.java.Log;
@Log
public class ServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		// log.info(String.format("%d", moduleContainer.get().size()));
		log.info("getInjector()");
		Injector newInjector = Guice.createInjector(Stage.PRODUCTION, new ServletModule() {

			@Override
			protected void configureServlets() {
				bind(HelloWorldServlet.class);
				serve("/my").with(HelloWorldServlet.class);
			}
		});
		return newInjector;
	}

	@Singleton
	@Log
	public static class HelloWorldServlet extends HttpServlet {

		private static final long serialVersionUID = -6169504697270124584L;

		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			log.info("HelloWorldServlet");

			String t1 = req.getParameter("req");
			PrintWriter writer = resp.getWriter();
			t1 = (t1==null||t1.isEmpty())?"":t1;
			writer.write("helloworld:\t"+t1);
			
			resp.getWriter().close();
			resp.getWriter().flush();
		}
	}
}