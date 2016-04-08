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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Path("hello")
public class JerseyHelloWorld {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String hello(){
		return "helloWorld: macrossx";
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/{path}")
	public Obj hello1(@PathParam("path") String path){
		Obj obj = new Obj();
		obj.setMsg("helloworld");
		obj.setReqPath(path);
		return obj;
	}	
	@Data
	@XmlRootElement  
	public static class Obj{
		private String msg;
		private String reqPath;
	}
}
