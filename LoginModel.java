package gui;

import java.io.Serializable;

import java.util.Observable;

/**
 * This LoginModel class contains all of the fields that need to be used in the LoginView and LoginController. This class also
 * implements the Serializable interface, ensuring that objects can be passed through the program.
 * 
 * The getters and setters here are used in the Model class, manipulating and retrieving data to be passed to the Server.
 * 
 * @authors wed413 & clc371
 *
 */
public class LoginModel extends Observable implements Serializable {

	private String username;
	private String password;
	private String returnButton;
	
	public LoginModel(){
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getReturnButton() {
		return returnButton;
	}

	public void setReturnButton(String returnButton) {
		this.returnButton = returnButton;
	}
	
}
