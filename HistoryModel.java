package gui;

import java.io.Serializable;
import java.util.Observable;

/**
 * This HistoryModel class contains all of the fields that need to be used in the HistoryView and HistoryController. This class also
 * implements the Serializable interface, ensuring that objects can be passed through the program.
 * 
 * @authors clc371 & wed413
 *
 */
public class HistoryModel extends Observable implements Serializable {
	
	private String back;
	
	public HistoryModel() {
		
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}
	
}
