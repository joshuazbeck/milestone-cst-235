package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;
import services.AuthenticationService;

/**
 * This controller is in charge of handling user registration
 * @author Josh Beck
 *
 */
@ManagedBean
@ViewScoped
public class RegistrationController {
	
	/**
	 * The code that is used to register the user
	 * @return - The home page
	 */
	public String register() {

		// Get the users passed from the form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		//Get an instance of the authentication service and if the page user is not null, add new user to storage
		AuthenticationService authService = new AuthenticationService();
		
		if (user != null) {
			authService.addUser(user);
		}

		return "index.xhtml";
	}
	
}
