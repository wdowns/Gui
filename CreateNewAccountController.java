package Gui;

import java.awt.event.ActionEvent;
import java.io.IOException;

import client.ClientInterface;

/**
 * This CreateNewAccountController class extends the abstract Controller class, which in itself implements the ActionListener interface. This class
 * has a reference to the Model, CreateNewAccountView and the ClientInterface. By extending the abstract Controller class which in turn implements the 
 * ActionListener interface, we can handle the user interaction with the buttons in our CreateNewAccountView.
 * 
 * @authors wed413 & clc371
 *
 */
public class CreateNewAccountController extends Controller {
	
	private Model model;
	private CreateNewAccountView view;
	private ClientInterface client;

	public CreateNewAccountController(Model model, CreateNewAccountView view, ClientInterface client) {
		super(model, view, client);
		this.model = model;
		this.view = view;
		this.client = client;
		
		
		view.getCreateButton().addActionListener(this); //We need to assign this controller as the acctionListner for the create button
		view.getLoginButton().addActionListener(this); //We need to assign this controller as the acctionListner for the login button
		
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.createButton) {
			model.getCreateModel().setEmailAddress(view.emailField.getText());
			model.getCreateModel().setCreateUsername(view.field.getText());
			model.getCreateModel().setCreatePassword(view.password.getText());
			try {
				client.createAccount();
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				System.err.println("IOEXCEPTION in CREATING ACCOUNT");
				e1.printStackTrace();
			}
		}
		
		//If the login button is pressed, they simply takes the user to the login panel, it is pressed when the user already has an account
		else if (e.getSource() == view.loginButton) {
			model.getCreateModel().getLogin();
			try {
				client.loginPage();
			}catch (IOException e2) {
				// TODO Auto-generated catch block
				System.err.println("IOEXCEPTION in LOGGING IN");
				e2.printStackTrace();
			}
		}
		
	}
		

}
