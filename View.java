package gui;

import java.io.PrintStream;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * Abstract view class, contains all common aspects/functionalities of the views
 * 
 * @authors wed413 & clc371
 * 
 */
public abstract class View extends JPanel implements Observer {
	
	Model model;
	
	public View(Model model) {
		this.model = model;
		setLayoutToNull();
		setup();
	}

	/**
	 * Every view will have this setup method, which will draw all of the components
	 */
	public abstract void setup();
	
	/**
	 * Method that sets the layout to null for each view
	 */
	private void setLayoutToNull() {
		setLayout(null);
	}

}
