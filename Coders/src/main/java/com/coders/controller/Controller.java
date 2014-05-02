package com.coders.controller;

import javax.websocket.Session;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.coders.service.Services;



@Path("/file")
public class Controller {
	
	@POST
	@Path("/signin")
	public Response userprofile(@FormParam("email") String email,@FormParam("password") String password,@FormParam("firstname") String firstName,@FormParam("lastname") String lastName) 
	{
		Services s =new Services();
		
		s.signIn(email,password,firstName,lastName);
				
		System.out.println("name is: "+email);
		String output="hi"+ email;
		return Response.status(200).entity(output).build();
		
	}
	
	
	@POST
	@Path("/signin")
	public Response display(@FormParam("email") String email,@FormParam("password") String password,@FormParam("firstname") String firstName,@FormParam("lastname") String lastName) 
	{
		Services s =new Services();
		
		s.signIn(email,password,firstName,lastName);
				
		System.out.println("name is: "+email);
		String output="hi"+ email;
		return Response.status(200).entity(output).build();
	}
	
	
	
	
}













