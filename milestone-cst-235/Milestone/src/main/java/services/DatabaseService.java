package services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;

/**
 * The service used to emulate the database as of Milestone 2
 * @author Josh Beck
 *
 */
@ManagedBean
@ApplicationScoped
public class DatabaseService {
	
	//The filename to store the users
	private String filename = "users.ser";
	
	/**
	 * Add a user to the database
	 * @param user - User
	 */
	public void addUsers(User user) {
		
		//Add the user to the database
		List<User> users = getAllUsers();
		users.add(user);
		setAllUsers(users);
		
	}
	
	/**
	 * Check if a user exists
	 * @param username - the username to check for
	 * @param password - the password to check for
	 * @return
	 */
	public User userExists(String username, String password) {
		
		//Get the list of all users
		List<User> users = getAllUsers();
		
		//Iterate through and determine if any users match the username and the password
		for (User u: users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				System.out.println("logging in user " + u.getUsername());
				return u;
			} else {
				System.out.println("user with username " + u.getUsername() + " " + username + " and password " + u.getPassword() + " " + password);
			}
		}
		return null;
	}
	
	/**
	 * Get all the users stored in a file
	 * @return - a list of users in the filesystem
	 */
	private List<User> getAllUsers(){
		List<User> usersRef = new ArrayList<User>();
		  
        try {   
        	
            //Get the file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
              
            //Deserialize the object
            usersRef = (List<User>)in.readObject();
              
            in.close();
            file.close();
              
        } catch(IOException ex) {
            System.out.println("IOException is caught" + ex.getMessage());
        }
          
        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        return usersRef;
	}
	/**
	 * Set all the users in the filesystem
	 * @param users - a list of users
	 */
	private void setAllUsers(List<User> users) {
        
        try {   
        	
        	//Accessing the file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
              
            //Writing the object to the filesystem
            out.writeObject(users);
              
            out.close();
            file.close();
              
        } catch(IOException ex) {
        	
            System.out.println("IOException is caught" +  ex.getMessage());
            
        }
 
	}
	public DatabaseService() {
		
	}
	
}
