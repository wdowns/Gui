package gui;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import client.Client;
import client.ClientInterface;
import client.Message;

/**
 * This ChatRoomController class extends the abstract Controller class, which in itself implements the ActionListener interface. This class
 * has a reference to the Model, ChatRoomView and the ClientInterface. By extending the abstract Controller class which in turn implements the 
 * ActionListener interface, we can handle the user interaction with the buttons in our ChatRoomView.
 * 
 * @authors wed413 & clc371
 *
 */
public class ChatRoomController extends Controller {
	
	private Model model;
	private ChatRoomView view;
	private ClientInterface client;

	public ChatRoomController(Model model, ChatRoomView view, ClientInterface client) {
		super(model, view, client);
		this.model = model;
		this.view = view;
		this.client = client;
		
		view.getLogoutButton().addActionListener(this); //We need to assign this controller as the acctionListner for the logout button
		view.getSendButton().addActionListener(this); //We need to assign this controller as the acctionListner for the send button
		//view.getSaveButton().addActionListener(this);
		view.getRetrieveButton().addActionListener(this); //We need to assign this controller as the acctionListner for the retrieve button
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * If the logout button is pressed, a confirmation dialog pops up which asks the user whether they are sure they wish to logout. The user is
		 * greeted with a simple yes/no option. If the user presses no, they simply go back to the chatroom, and if they press yes the system exits.
		 */
		if (e.getSource() == view.logoutButton) {
			int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout\n"
              + "of the Munich chatroom?", "Is this goodbye?",  JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				System.out.println("You have logged out");
//				view.getMessageArea().setText("<" + model.getLoginModel().getUsername() + "> has logged out");
				try {
					client.sendMessages("User " + model.getLoginModel().getUsername() + " has logged out!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				view.getSendArea().setText("");
				System.exit(0);
			}
			else {
				System.out.println("User's staying!");
			}

		}

		//If the user presses the retrieve button, they are taken to the retrieve history panel
		else if (e.getSource() == view.getRetrieveButton()) {
			model.getChatModel().getRetrieveButton();
			try {
			client.retrieveHistory();
		} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.err.println("IOEXCEPTION in RETRIEVING HISTORY");
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == view.sendButton) {
			if (view.getSendArea().getText().length() < 1) { //if the user hasn't typed anything, do nothing
			} else if (view.getSendArea().getText().equals(".clear")) {
				/*
				 * "Cleared all messages comes up on the screen", and the text area is reset
            	 * back to being blank
            	 */
				view.getMessageArea().setText("Cleared all messages\n");
				view.getSendArea().setText(""); //the send area goes back to being empty after each message has been entered
			} else {
//				/*
//            	 * If the user has typed something, appear in the messageArea JTextArea, starting with
//            	 * the user's chosen username followed by their text
//            	 */
			}
			try {
				client.sendMessages(view.sendArea.getText()); 
				view.getSendArea().setText("");
			}catch (IOException e2) {
				// TODO Auto-generated catch block
				System.err.println("IOEXCEPTION in CREATING ACCOUNT");
				e2.printStackTrace();
			}
		}
	}
	
}
