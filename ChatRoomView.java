package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.DummyClient;
import db.JDBC;

/**
 * This class contains the view for the chat room. This class extends the abstract class View, which contains all common aspects and 
 * functionalities of all views. This class draws the GUI, and updates any components when the model is changed.
 * 
 * @authors wed413 & clc371
 * 
 */
public class ChatRoomView extends View {

	//private Model model;
	
	JLabel usrlabel; //JLabel saying welcome and appending the user's chosen username
	JTextArea messageArea; //display all the messages from the users
	JScrollPane messageScroll; //puts a vertical and horizontal scroll bar on the messageArea JTextArea 
	JTextField sendArea; //where the user types in the message they wish to send to the message area
	JButton sendButton; //the JButton that is pressed to send a message from the sendArea to the messageArea
	JButton logoutButton; //the JButton that is pressed to logout of the chat room
	JLabel onlineLabel; //simple JLabel saying "User's online:"
	JTextArea onlineArea; //displays all of the users currently online
	JScrollPane onlineScroll; //puts a vertical and horizontal scroll bar on the onlineArea JTextArea
	JButton saveButton; //the JButton that is pressed when the user wants to save their current chat
	JButton retrieveButton; //the JButton that is pressed when the user wants to retrieve previous chats that they have had
	JLabel labelClock; //label for time method
	JLabel labelDate; //label for the date method
	JDBC jdbc;
	

	public ChatRoomView(Model model) {
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
		 * Label that displays a welcome message that appends the user's username to the message
		 */
		usrlabel = new JLabel("Welcome " + model.getUser().getUsername() + "!");
		usrlabel.setBounds(20, 16, 300, 25);
		add(usrlabel);
		Font usrfont = new Font("Arial Bold", Font.BOLD,14);
		usrlabel.setFont(usrfont);			
		
		/*
		 * messageArea is the JTextArea where any message sent from the users (via the sendArea text field) is displayed
		 */
		messageArea = new JTextArea();
		messageArea.setEditable(false); //will only display text and won't allow for editing
		messageArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		messageArea.setLineWrap(true);
		
		/*
		 * This method adds a scroll bar along the vertical and horizontal sides of the messageArea. It has been set that both scroll bars
		 * will always appear on the message area, as opposed to them only appearing when needed.
		 */
		messageScroll = new JScrollPane(messageArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		messageScroll.setBounds(15, 57, 520, 370);
		add(messageScroll);
		
		/*
		 * This is the JTextArea where users type their messages
		 */
		sendArea = new JTextField();
		sendArea.setColumns(25);
		sendArea.setBounds(15, 460, 620, 55);
		add(sendArea);
		
		/*
		 * This is the button that is pressed when the users wants to sent their message from the sendArea to the messageArea.
		 */
		sendButton = new JButton("Send");
		sendButton.setBounds(666, 460, 84, 55);
		add(sendButton);
		
		/*
		 * This is the button that is pressed when the user wishes to logout of the chatroom. Once pressed, a pop up message appears (written
		 * in the ChatRoomController) asking whether the user is sure they want to logout. 
		 */
		logoutButton = new JButton("LOGOUT");
		logoutButton.setBounds(371, 14, 100, 29);
		add(logoutButton);
		
		/*
		 * This label is displayed above the onlineArea JTextArea which states which users are currently online.
		 */
		onlineLabel = new JLabel("Users online:");
		onlineLabel.setBounds(610, 16, 150, 35);
		add(onlineLabel);
		Font onlinefont = new Font("Arial Bold",Font.BOLD,14);
		onlineLabel.setFont(onlinefont);
		
		/*
		 * This is the JTextArea where the users' messages that they typed in the sendArea are displayed
		 */
		onlineArea = new JTextArea();
		onlineArea.setEditable(false); //will only display text and won't allow for editing
		onlineArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		appendUsersOnline(null);
		
		/*
		 * This method adds a scroll bar along the vertical and horizontal sides of the onlineArea. It has been set that both scroll bars
		 * will always appear on the message area, as opposed to them only appearing when needed.
		 */
		onlineScroll = new JScrollPane(onlineArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		onlineScroll.setBounds(570, 57, 190, 370);
		add(onlineScroll);
		
		/*
		 * This is the button that the user presses if they want to save their current chat. It is then stored in the database and 
		 * can be retrieved when the user presses the retrieve button.
		 */
		saveButton = new JButton("Save Chat");
		saveButton.setBounds(30, 530, 110, 30);
		add(saveButton);
		
		/*
		 * This is the button that the user presses if they want to retrieve previous chats that they have had in the chatroom. Previous 
		 * chats are stored in the database when the user pressed 'Save Chat'. Pressing retrieve brings up a new panel with all previous chats 
		 * in chronological order.
		 */
		retrieveButton = new JButton("Retrieve");
		retrieveButton.setBounds(150, 530, 110, 30);
		add(retrieveButton);
		
		//JLabel for the clock method above (stating the time), setting its font and bounds
		labelClock = new JLabel("Time");
		labelClock.setFont(new Font("Gill Sans Nova", Font.BOLD, 14));
		labelClock.setBounds(510, 540, 156, 20);
		add(labelClock);
		
		//JLabel for the clock method above (stating the date), setting its font and bounds
		labelDate = new JLabel("Date");
		labelDate.setFont(new Font("Gill Sans Nova", Font.BOLD, 14));
		labelDate.setBounds(640, 540, 156, 20);
		add(labelDate);
		
		clock();
		
	}
	
	public void append(String msg){
		messageArea.append(msg);
	}
	
	
	public void appendUsersOnline(String usersOnline){
		usersOnline = model.getLoginModel().getUsername();
		onlineArea.append(usersOnline);
	}
	
	
	
	/**
	 * The sendButton needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JButton getSendButton() {
		return sendButton;
	}

	/**
	 * The messageArea needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JTextArea getMessageArea() {
		return messageArea;
	}

	/**
	 * The sendArea needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JTextField getSendArea() {
		return sendArea;
	}

	/**
	 * The logoutButton needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JButton getLogoutButton() {
		return logoutButton;
	}
	
	/**
	 * The onlineArea needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JTextArea getOnlineArea() {
		return onlineArea;
	}

	/**
	 * The saveButton needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JButton getSaveButton() {
		return saveButton;
	}

	/**
	 * The retrieveButton needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JButton getRetrieveButton() {
		return retrieveButton;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
