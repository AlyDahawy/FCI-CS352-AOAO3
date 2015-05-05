package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;










//import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.Models.UserEntity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")
public class Service {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/


		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity (uname, email, pass );
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}
	  //@POST
	  //@Path("/addFriend")
	  //public String addFriend(@FormParam("name") String name )
	  {
		  
		  
		  
	  }
	

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		User.getinstance().setUser(user);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}

		return object.toString();

	}
	@POST
	@Path("/SearchService")
	public String searchService(@FormParam("n") String n)
	{ 
		JSONObject object = new JSONObject();
	   if(User.getinstance().getUser().search(n)==true)
	   {
		object.put("Status", "OK");
		System.out.println("////////////");
	   }
	   else 
	   {
		   object.put("Status", "faileed");
	   }
	   
		return object.toString();
		
	}
	@POST
	@Path("/SearchSendService")
	public String searchSendService(@FormParam("ss") String ss)
	{ 
		JSONObject object = new JSONObject();
	   if(User.getinstance().getUser().search(ss)==true)
	   {
		object.put("Status", "OK");
		System.out.println("////////////");
	   }
	   else 
	   {
		   object.put("Status", "faileed");
	   }
	   
		return object.toString();
		
	}
	@POST
	@Path("/addfriendService")
	public String addService(@FormParam("name") String name)
	{
		JSONObject object = new JSONObject();
	   if	(User.getinstance().getUser().addfriend(name)==true)
	   {
		object.put("Status", "OK");
	   }
	   else
	   {
		   object.put("Status", "failed");
	   }
		return object.toString();
		
	}
	
	@GET
	@Path("/showrequests")
	public String ShowService()
	{
		List<String> y=new ArrayList<String>();
		y=User.getinstance().getUser().showrequests();
		JSONObject object = new JSONObject();
	for(int i=0;i<y.size();i++)
	{
		object.put("request", y.get(i));
		
	}
		
	
		return object.toString();
		
	}
	@POST
	@Path("/acceptService")
	public String acceptService(@FormParam("nn") String nn) {
		User.getinstance().getUser().accept(nn);
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	@POST
	@Path("/showmsg")
	//@Produces("text/html")
	public String Showmsg(  )
	{
		Vector<String> msg=User.getinstance().getUser().Show();
		 JSONArray arr1 = new JSONArray();
		 for ( int i=0;i<msg.size();i++)
		 {
			 JSONObject object = new JSONObject();
		 
			object.put(" message ", msg.get(i) );
			
			arr1.put(object);
		 }
			
		return arr1.toString() ;
}
		
		
	

	@POST
	@Path("/messageService")
	public String message(@FormParam("n1") String n1,@FormParam("msg") String msg)
	{
		
	   	User.getinstance().getUser().message(n1, msg);
	   JSONObject object = new JSONObject();
		object.put("Status", "OK");
		
	   
	 
		  
		return object.toString();
		
	}
	@POST
	@Path("/groupService")
	public String group(@FormParam("id") String id,@FormParam("mem") String mem)
	{
		
	   	User.getinstance().getUser().group(id, mem);
	   JSONObject object = new JSONObject();
		object.put("Status", "OK");
		
	   
	 
		  
		return object.toString();
		
	}
	@POST
	@Path("/msggroupService")
	public String msggroup(@FormParam("id1") String id1,@FormParam("msg1") String msg1)
	{
		
	   	User.getinstance().getUser().msggroup(id1, msg1);
	   JSONObject object = new JSONObject();
		object.put("Status", "OK");
		
	   
	 
		  
		return object.toString();
		
	}
	
		
	}
	
