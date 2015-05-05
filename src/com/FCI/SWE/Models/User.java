package com.FCI.SWE.Models;

public class User {
private static UserEntity u;
private static User manager;


private User ()
{
	
}

public static User getinstance()
{
	if(manager==null)
	{
		manager = new User();
	}
	
	return manager;
	
}




/**
 * @return the user
 */
public UserEntity getUser() {
    return u;
}

/**
 * @param user the user to set
 */
public void setUser(UserEntity u) {
    this.u = u;
}




}

