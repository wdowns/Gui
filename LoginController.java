package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import client.Client;
import client.ClientInterface;

/**
 * This LoginController class extends the abstract Controller class, which in itself implements the ActionListener interface. This class
 * has a reference to the Model, LoginView and the ClientInterface. By extending the abstract Controller class which in turn implements the 
 * ActionListener interface, we can handle the user interaction with the buttons in our LoginView.
 * 
 * @authors wed413 & clc371
 *
 */
public class LoginController extends Controller {
    
	private Model model;
	private LoginView view;
	private ClientInterface client;
	
	public LoginController(Model model, LoginView view, ClientInterface client) {
		super(model, view, client);
		this.model = model;
		this.view = view;
		this.client = client;
		
		view.getHelpButton().addActionListener(this); //We need to assign this controller as the acctionListner for the help button
		view.getOkButton().addActionListener(this); //We need to assign this controller as the acctionListner for the ok button
		view.getReturnButton().addActionListener(this); //We need to assign this controller as the acctionListner for the return button
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.okButton) {
//			LoginModel login = new LoginModel();
			model.getLoginModel().setUsername(view.usrField.getText());
			model.getLoginModel().setPassword(view.password.getText());
//			client2.sending(login);
			try {
				client.login();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.err.println("IOEXCEPTION in LOGIN");
				e1.printStackTrace();
			}
		}
		
		//If the help button is pressed, a simple message dialog pops up giving information to the user on what to do next
        else if (e.getSource() == view.helpButton) { 
        	JOptionPane.showMessageDialog(null,
                    "Please type in the username and password that has\n"
                  + "been assigned to you.\n"
                  + "If you do not have an account yet, please return\n"
                  + "to the create account page by pressing the button below.");
        }
		
		//if the return button is pressed, the user is simply taken back to the create account panel 
        else if (e.getSource() == view.getReturnButton()) {
        	model.getLoginModel().getReturnButton();
        	try {
				client.previousPage();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				System.err.println("IOEXCEPTION in LOGIN");
				e2.printStackTrace();
			}
        }
    }
	
}

