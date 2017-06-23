package gui;

import java.io.Serializable;
import java.util.Observable;

/**
 * This CreateNewAccountModel class contains all of the fields that need to be used in the CreateNewAccountView and CreateNewAccountController. This class also
 * implements the Serializable interface, ensuring that objects can be passed through the program.
 * 
 * The getters and setters here are used in the Model class, manipulating and retrieving data to be passed to the Server.
 * 
 * @authors wed413 & clc371
 *
 */
public class CreateNewAccountModel extends Observable implements Serializable {

	private String emailAddress;
	private String createUsername;
	private String createPassword;
	private String login;
	
	public CreateNewAccountModel(String emailAddress, String createUsername, String createPassword) {
		this.emailAddress = emailAddress;
		this.createUsername = createUsername;
		this.createPassword = createPassword;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public String getCreatePassword() {
		return createPassword;
	}

	public void setCreatePassword(String createPassword) {
		this.createPassword = createPassword;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
