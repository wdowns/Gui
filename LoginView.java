package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This class contains the view for the login page. This class extends the abstract class View, which contains all common aspects and 
 * functionalities of all views. This class draws the GUI, and updates any components when the model is changed.
 * 
 * @authors wed413 & clc371
 *
 */
public class LoginView extends View {

	private LoginModel model;
	
	JTextField usrField; //area where the user types their username
	JPasswordField password; //area where the user types their password
	JLabel label; //simple label saying welcome 
	JLabel usrLabel; //label stating that the user should enter their username
	JLabel passLabel; //label stating that the user should enter their password
	JButton okButton; //the button that is pressed when the user has entered both their username and password
	JButton helpButton; //the button that is pressed if the user needs any help with the logging in process
	ImageIcon image; //login image
	ImageIcon image2; //smiley face
	JLabel loginPic; //label to put image on
	JLabel smile; //label to put image2 on
	JLabel labelClock; //label for time method
	JLabel labelDate; //label for date method
	JButton returnButton; //the button that is pressed if the user wishes to return to the create account page

	public LoginView(Model model) {
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
					labelDate.setText("Date: " + " " + day + "/" + 3 + "/" + year);
					
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
		 * Label that displays a welcome message to the user
		 */
		label = new JLabel ("Welcome to the Munich chatroom!");
		label.setSize(400, 20);
		label.setLocation(30, 20);
		add(label);
		Font font = new Font("Arial Bold", Font.BOLD,14);
		label.setFont(font);
		label.setForeground(Color.BLUE);
		
		/*
		 * This label is displayed next to the text field asking for users to enter their username
		 */
		usrLabel = new JLabel("Please enter username:");
		usrLabel.setSize(400,20);
		usrLabel.setLocation(30,70);
		add(usrLabel);
		Font usrfont = new Font("Arial Bold", Font.ITALIC,12);
		usrLabel.setFont(usrfont);
		usrLabel.setForeground(Color.RED);
		
		/*
		 * This is the text field where users type in their username. 
		 */
		usrField = new JTextField();
		usrField.setColumns(25);
		usrField.setSize(350, 30);
		usrField.setLocation(30,90);
		usrField.setToolTipText("Enter username");
		add(usrField);
		
		/*
		 * This label is displayed next to the text field asking for users to enter their password
		 */
		passLabel = new JLabel("Please enter password:");
		passLabel.setSize(400,20);
		passLabel.setLocation(30,150);
		add(passLabel);
		Font passfont = new Font("Arial Bold", Font.ITALIC,12);
		passLabel.setFont(passfont);
		passLabel.setForeground(Color.RED);
		
		/*
		 * This is the password field where the user enters their password. By using JPasswordField instead of JTextField, when the users types
		 * into the field the characters do not show in the box, improving security
		 */
		password = new JPasswordField();
		password.setColumns(25);
		password.setSize(350, 30);
		password.setLocation(30,170);
		password.setToolTipText("Enter password");
		add(password);
		
		/*
		 * This is the button that is pressed 
		 */
		okButton = new JButton("OK");
		okButton.setSize(68,25);
		okButton.setLocation(430, 110);
		add(okButton);
		
		/*
		 * This is the button that users press if they need any help with the login process. Once pressed, a pop up message appears (written
		 * in the LoginController) giving information on what to do.
		 */
		helpButton = new JButton("Help");
		helpButton.setSize(68,25);
		helpButton.setLocation(430, 150);
		add(helpButton);
		
		/*
		 * Method for adding an image onto a label, in this case a login image in the top right part of the screen
		 */
		loginPic = new JLabel("");
		image = new ImageIcon(this.getClass().getResource("login.png"));
		loginPic.setIcon(image);
		loginPic.setBounds(395, 4, 118, 90);
		add(loginPic);
		
		/*
		 * Method for adding an image onto a label, in this case a smiley face next to the welcome message.
		 */
		smile = new JLabel("");
		image2 = new ImageIcon(this.getClass().getResource("smile.png"));
		smile.setIcon(image2);
		smile.setBounds(315, 19, 24, 20);
		add(smile);
		
		/*
		 * JLabel for the clock method above (stating the time), setting its font and bounds
		 */
		labelClock = new JLabel("Time");
		labelClock.setFont(new Font("Gill Sans Nova", Font.BOLD, 14));
		labelClock.setBounds(30, 220, 156, 20);
		add(labelClock);
		
		/*
		 * JLabel for the clock method above (stating the date), setting its font and bounds
		 */
		labelDate = new JLabel("Date");
		labelDate.setFont(new Font("Gill Sans Nova", Font.BOLD, 14));
		labelDate.setBounds(180, 220, 156, 20);
		add(labelDate);
		
		/*
		 * This the the button that is pressed if the user has arrived at the login page by accident and does in fact need to create an
		 * account. This button returns the user back to the create account screen.
		 */
		returnButton = new JButton("< Return");
		returnButton.setSize(100, 25);
		returnButton.setLocation(415, 220);
		add(returnButton);
		
		clock();
	}
	
	/**
	 * The okButton needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JButton getOkButton() {
		return okButton;
	}

	/**
	 * The helpButton needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JButton getHelpButton() {
		return helpButton;
	}
	
	/**
	 * The returnButton needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JButton getReturnButton() {
		return returnButton;
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
