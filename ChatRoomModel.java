package gui;

import java.io.Serializable;
import java.util.Observable;

/**
 * This ChatRoomModel class contains all of the fields that need to be used in the ChatRoomView and ChatRoomController. This class also
 * implements the Serializable interface, ensuring that objects can be passed through the program.
 *
 * @authors wed413
 *
 */
public class ChatRoomModel extends Observable implements Serializable {

	private String retrieveButton;
	
	public ChatRoomModel() {
		
	}

	public String getRetrieveButton() {
		return retrieveButton;
	}

	public void setRetrieveButton(String retrieveButton) {
		this.retrieveButton = retrieveButton;
	}
}
