package gui;

import java.awt.event.ActionEvent;
import java.io.IOException;

import client.ClientInterface;

/**
 * This HistoryController class extends the abstract Controller class, which in itself implements the ActionListener interface. This class
 * has a reference to the Model, HistoryView and the ClientInterface. By extending the abstract Controller class which in turn implements the 
 * ActionListener interface, we can handle the user interaction with the buttons in our HistoryView.
 * 
 * @authors wed413 & clc371
 *
 */
public class HistoryController extends Controller {
	
	private Model model;
	private HistoryView view;
	private ClientInterface client;

	public HistoryController(Model model, HistoryView view, ClientInterface client) {
		super(model, view, client);
		this.model = model;
		this.view = view;
		this.client = client;	
		
		view.getBack().addActionListener(this); //We need to assign this controller as the acctionListner for the back button
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//If the user presses the back button, they are taken back to the chatroom
		if (e.getSource() == view.getBack()) {
			model.getHistoryModel().getBack();
			try {
				client.backToChat();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.err.println("IOEXCEPTION in RETURNING TO CHAT");
				e1.printStackTrace();
			}
		}
	}

}
