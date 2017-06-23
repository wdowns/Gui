package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.DummyClient;

/**
 * This class contains the view for the chat history of the user. This class extends the abstract class View, which contains all common aspects and 
 * functionalities of all views. This class draws the GUI, and updates any components when the model is changed.
 * 
 * @authors clc371 & wed413
 *
 */
public class HistoryView extends View {

	JLabel label; //JLabel saying "Chat history for" and appending the user's chosen username
	JButton back; //This is the button that is pressed when the user wishes to return back to the chatroom
	JButton history; //This is the button that is pressed when the user wishes to retrieve their chat history
	JLabel labelClock; //label for time method
	JLabel labelDate; //label for the date method
	
	public HistoryView(Model model) {
		super(model);
	}
	
	/**
	 * This method adds a clock to the panel which tells the running time and also the date in DD/MM/YYYY format.
	 */
	public void clock() {
		
		Thread clock = new Thread() {
			
			public void run() {
				
				try {
					for(;;) { //infinite for loop to change the time from static to moving
					Calendar cal = new GregorianCalendar();

					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);
					
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR_OF_DAY);
					
					labelClock.setText("Time: " + hour + ":" + minute + ":" + second);
					labelDate.setText("Date: " + day + "/" + 3 + "/" + year);
					
					sleep(1000); //updates the clock every second
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start(); //thread begins executing
	}
	
	/**
	 * Setup method, which draws all of the components 
	 */
	@Override
	public void setup() {
		
		/*
		 * Label that states the chat history and then appends the user's username to the message
		 */
		label = new JLabel("Chat history for " + model.getUser().getUsername());
		label.setBounds(20, 20, 300, 25);
		add(label);
		Font usrfont = new Font("Arial Bold", Font.BOLD,14);
		label.setFont(usrfont);	
		
		/*
		 * This is the button that the user presses when they wish to retrun back to the chatroom
		 */
		back = new JButton("< BACK");
		back.setBounds(280, 20, 90, 30);
		add(back);
		
		/*
		 * The next 8 JButtons are the eight empty slots where the user's previous chats are diplayed. (Didn't in the end completely finish 
		 * this section due to time constraints)
		 */
		history = new JButton("EMPTY SLOT 1");
		history.setBounds(20, 80, 360, 40);
		add(history);

		history = new JButton("EMPTY SLOT 2");
		history.setBounds(20, 120, 360, 40);
		add(history);
		
		history = new JButton("EMPTY SLOT 3");
		history.setBounds(20, 160, 360, 40);
		add(history);
		
		history = new JButton("EMPTY SLOT 4");
		history.setBounds(20, 200, 360, 40);
		add(history);
		
		history = new JButton("EMPTY SLOT 5");
		history.setBounds(20, 240, 360, 40);
		add(history);
		
		history = new JButton("EMPTY SLOT 6");
		history.setBounds(20, 280, 360, 40);
		add(history);
		
		history = new JButton("EMPTY SLOT 7");
		history.setBounds(20, 320, 360, 40);
		add(history);
		
		history = new JButton("EMPTY SLOT 8");
		history.setBounds(20, 360, 360, 40);
		add(history);
		
		//JLabel for the clock method above (stating the time), setting its font and bounds
		labelClock = new JLabel("Time");
		labelClock.setFont(new Font("Gill Sans Nova", Font.BOLD, 14));
		labelClock.setBounds(60, 540, 156, 20);
		add(labelClock);
				
		//JLabel for the clock method above (stating the date), setting its font and bounds
		labelDate = new JLabel("Date");
		labelDate.setFont(new Font("Gill Sans Nova", Font.BOLD, 14));
		labelDate.setBounds(190, 540, 156, 20);
		add(labelDate);
				
		clock();
	}
	
	/**
	 * The back needs to be made accessible to the controller in the HistoryController class,
	 * so a getter is made
	 */
	public JButton getBack() {
		return back;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
