package services;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;

/**
 * This service helps to handle authentication for the application
 * @author Josh Beck
 *
 */
@ManagedBean
@ViewScoped
public class AuthenticationService {
	
	
	//Get an instance of the database service used to handle the "database"
	DatabaseService db;
	
	/**
	 * Create an instance of the AuthenticationService with a database service instance
	 */
	public AuthenticationService() {
		db = new DatabaseService();
	}
	
	/**
	 * This method is used to validate a user exists
	 * @param username - the user name
	 * @param password - the password
	 * @return - a user if one exists matching the user name and password
	 */
	 public User validateUser(String username, String password){
		 // Check the DB for a user with this user name and password
		 User user = db.userExists(username, password);
		 
		 // Check if a user was returned
		 if (user != null) {
			 //Set the logged in value of the user to true
			 user.setLoggedIn(true);
			 //Save the user in the context to indicate a logged in user
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userInstance", user);
			 System.out.println("validate user " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance").toString());
			 return user;
		 } else {
			 return null;
		 }
		 
	 }
	 public boolean userIsLoggedIn(){
		 //Check the context if a user is logged in
		 System.out.println("logged in " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance").toString());
		 return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userInstance") != null;
	 }
	 public void invalidateUser(){
		 //Remove the user from the context
		 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userInstance");
	 }
	  
	 /**
	  * Add a user to the database
	  * @param user - User
	  */
	 public void addUser(User user) {
		 db.addUsers(user);
	 }
	  

}
