package com.FCI.SWE.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	private String name;
	private String email;
	private String password;
	

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	public UserEntity(String name, String email, String password  ) {
		this.name = name;
		this.email = email;
		this.password = password;
      
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static UserEntity getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			return new UserEntity(object.get("name").toString(), object.get(
					"email").toString(), object.get("password").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				return returnedUser;
			}
		}

		return null;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		
		datastore.put(employee);

		return true;

	}
	public static boolean search(String n)
	{
		
	boolean t=false;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()){
			
			if (entity.getProperty("name").toString().equals(n))
			{
				System.out.println("------");
				
				t= true;
			}				
		}
	return t;
	}
	
	public boolean addfriend(String name ){
		
		boolean y=false;
		
		if(User.getinstance().getUser().search(name)==true)
		{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee = new Entity("requests", list.size() + 1);
		
		employee.setProperty("sender",this.name);
		employee.setProperty("reciever",name );
		employee.setProperty("accepted/unaccepted", "");
		datastore.put(employee);
		
		Query gaeQuery1 = new Query("notifications");
		PreparedQuery pq1 = datastore.prepare(gaeQuery);
		List<Entity> list1 = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee1 = new Entity("notifications", list.size() + 1);
		employee1.setProperty("sender",this.name);
		employee1.setProperty("reciever",name );
		employee1.setProperty("type", "friendrequest");
		datastore.put(employee1);
		
		
		y=true;
		
		
		}
		
		return y;
		
	}
public static void message ( String name , String msg ){
		
		//boolean y=false;
		
		//if(User.getinstance().getUser().search(name)==true)
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("indivdual msg");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee = new Entity("indivdual msg", list.size() + 1);
	
		employee.setProperty("sender",User.getinstance().getUser().name);
		employee.setProperty("reciever",name );
		employee.setProperty("message", msg);
		datastore.put(employee);
		
		
		
		Query gaeQuery1 = new Query("notifications");
		PreparedQuery pq1 = datastore.prepare(gaeQuery);
		List<Entity> list1 = pq.asList(FetchOptions.Builder.withDefaults());
		Entity employee1 = new Entity("notifications", list.size() + 1);
		employee1.setProperty("sender",User.getinstance().getUser().name);
		employee1.setProperty("reciever",name );
		employee1.setProperty("type", "message");
		datastore.put(employee1);
		
		//return y;
		
	}
public static void group ( String id , String mem ){
	
	//boolean y=false;
	
	//if(User.getinstance().getUser().search(name)==true)
	
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query gaeQuery = new Query("group conversations");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
	Entity employee = new Entity("group conversations", list.size() + 1);
	
	employee.setProperty("sender",User.getinstance().getUser().name);
	employee.setProperty("reciever",mem );
	employee.setProperty("group_id", id);
	datastore.put(employee);

	//return y;
}
public static void msggroup ( String id , String msg ){
	
	//boolean y=false;
	
	//if(User.getinstance().getUser().search(name)==true)
	
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query gaeQuery = new Query("group messages");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
	Entity employee = new Entity("group messages", list.size() + 1);
	
	employee.setProperty("sender",User.getinstance().getUser().name);
	employee.setProperty("message",msg );
	employee.setProperty("group_id", id);
	datastore.put(employee);
   //////////////////////////////////////////// 
	//Query gaeQuery1 = new Query("notifications");
	//PreparedQuery pq1 = datastore.prepare(gaeQuery1);

	
	/////////////////////////////////////////////////////
	
	Query gaeQuery2 = new Query("group conversations");
	PreparedQuery pq2 = datastore.prepare(gaeQuery2);
	
	for (Entity entity : pq2.asIterable())
	{
		
		if(entity.getProperty("group_id").toString().equals(id))
		{
			DatastoreService datastore1 = DatastoreServiceFactory
				.getDatastoreService();
			Query gaeQuery1 = new Query("notifications");
			PreparedQuery pq1 = datastore1.prepare(gaeQuery1);
			List<Entity> list1 = pq1.asList(FetchOptions.Builder.withDefaults());
			Entity employee1 = new Entity("notifications", list1.size() + 1);
			System.out.println("***********************");
			System.out.println(entity.getProperty("reciever").toString());
	employee1.setProperty("sender",User.getinstance().getUser().name);
		employee1.setProperty("reciever",entity.getProperty("reciever").toString());
	employee1.setProperty("type", "message");
	
	datastore1.put(employee1);
	}
		
	}
	
	//return y;
}

	 public List<String> showrequests ( )
	 {
		 List<String> l=new ArrayList<String>();
		 DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();

			Query gaeQuery = new Query("requests");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				if (entity.getProperty("reciever").toString().equals(this.name))
						{
					l.add(entity.getProperty("sender").toString());
						}
			}
			return l;
	 }
	 
		public boolean accept(String name ){
			
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			Query gaeQuery = new Query("requests");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
			Entity employee = new Entity("requests", list.size() + 1);
			
			for (Entity entity : pq.asIterable()) {
				if (entity.getProperty("reciever").toString().equals(this.name) &&entity.getProperty("sender").toString().equals(name))
						{
			entity.setProperty("accepted/unaccepted", "accept");
			datastore.put(entity);
			
			}
			
			
		}
			return true;
	}
		
		
	public  Vector<String> Show()
	{
		String m = null;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("indivdual msg");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		Vector<String> msgs= new Vector<String>();
	//	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		//Entity employee = new Entity("indivdual msg", list.size() + 1);
		
		for (Entity entity : pq.asIterable()) {
			if (    entity.getProperty("reciever").toString().equals(this.name) )
					{
	msgs.add( entity.getProperty("message").toString());

	}
}
		
	return msgs;
	}
}