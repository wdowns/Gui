package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class contains the view for the create account page. This class extends the abstract class View, which contains all common aspects and 
 * functionalities of all views. This class draws the GUI, and updates any components when the model is changed.
 * 
 * @authors wed413 & clc371
 *
 */
public class CreateNewAccountView extends View {
	
	JTextField field; //area where the user types their username
	JLabel createLabel; //simple JLabel asking the user to create an account
	JPasswordField password; //area where the user types their password
	JLabel label; //simple label saying welcome 
	JLabel usrLabel; //label stating that the user should create a new username
	JLabel passLabel; //label stating that the user should create a new password
	JButton createButton; //The button that is pressed when the user wants to create an account
	JLabel labelClock; //label for time method
	JLabel labelDate; //label for the date method
	JLabel loginLabel;
	JButton loginButton; //The button that is pressed when the user wants to move straight to the create account panel
	JLabel registrationPic; //label to put image on
	ImageIcon image; //registration image
	JLabel smile; //label to put image2 on
	ImageIcon image2; //smiley face
	JLabel emailLabel; //label stating that the user should enter their email address
	JTextField emailField; //area where the user types their email address
	
	public CreateNewAccountView(Model model) {
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
					// TODO Auto-generated catch block
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
		 * Label that simply asks the user to create an account
		 */
		createLabel = new JLabel ("Please create a new account");
		createLabel.setSize(400, 20);
		createLabel.setLocation(30, 40);
		add(createLabel);
		Font font2 = new Font("Arial Bold", Font.BOLD,14);
		createLabel.setFont(font2);
		createLabel.setForeground(Color.BLUE);
		
		/*
		 * Label that simply asks the user to enter their email address
		 */
		emailLabel = new JLabel("Please enter your email address:");
		emailLabel.setSize(400, 20);
		emailLabel.setLocation(30, 80);
		add(emailLabel);
		Font emailFont = new Font("Arial Bold", Font.ITALIC,13);
		emailLabel.setFont(emailFont);
		emailLabel.setForeground(new Color(75, 183, 68));

		/*
		 * This is the text field where the user types in their email address
		 */
		emailField = new JTextField();
		emailField.setColumns(25);
		emailField.setSize(350, 30);
		emailField.setLocation(30,100);
		emailField.setToolTipText("Enter your email address");
		add(emailField);
		
		/*
		 * Label that simply tells the user to create a new username
		 */
		usrLabel = new JLabel("Please create new username:");
		usrLabel.setSize(400,20);
		usrLabel.setLocation(30,150);
		add(usrLabel);
		Font usrfont = new Font("Arial Bold", Font.ITALIC,13);
		usrLabel.setFont(usrfont);
		usrLabel.setForeground(new Color(75, 183, 68));
		
		/*
		 * This is the text field where the user types in their username
		 */
		field = new JTextField();
		field.setColumns(25);
		field.setSize(350, 30);
		field.setLocation(30,170);
		field.setToolTipText("Create new username");
		add(field);
		
		/*
		 * Label that simply tells the user to create a new password for their Munich account
		 */
		passLabel = new JLabel("Please create new password:");
		passLabel.setSize(400,20);
		passLabel.setLocation(30,220);
		add(passLabel);
		Font passfont = new Font("Arial Bold", Font.ITALIC,13);
		passLabel.setFont(passfont);
		passLabel.setForeground(new Color(75, 183, 68));
		
		/*
		 * This is the password field where the user enters their password. By using JPasswordField instead of JTextField, when the users types
		 * into the field the characters do not show in the box, improving security. A ToolTipText has been created which tells the user - once
		 * hovered over - the constraints when creating a password, e.g. no longer than 32 characters which was established in the database and 
		 * that special characters are not allowed. 
		 */
		password = new JPasswordField();
		password.setColumns(25);
		password.setSize(350, 30);
		password.setLocation(30,240);
		password.setToolTipText("Password can be up to 32 characters in length. No special characters allowed, e.g. £, $, !, ?");
		add(password);
		
		/*
		 * This is the JButton that the user presses when the user has finished typing in all of their details and wishes to create the account
		 */
		createButton = new JButton("Create >");
		createButton.setSize(100, 25);
		createButton.setLocation(430, 172);
		add(createButton);
		
		/*
		 * Simple label displayed in case the user already has an account
		 */
		loginLabel = new JLabel("Already have an account? Please ");
		loginLabel.setSize(400,20);
		loginLabel.setLocation(30,292);
		add(loginLabel);
		
		/*
		 * The button following on from the loginLabel, which the user presses if they want to be taken straight to the login page
		 */
		loginButton = new JButton("Log in");
		loginButton.setSize(80, 25);
		loginButton.setLocation(270, 290);
		add(loginButton);
		
		/*
		 * Method for adding an image onto a label, in this case a registration image in the top right part of the screen
		 */
		registrationPic = new JLabel("");
		image = new ImageIcon(this.getClass().getResource("registration.png"));
		registrationPic.setIcon(image);
		registrationPic.setBounds(410, 8, 130, 100);
		add(registrationPic);
		
		/*
		 * Method for adding an image onto a label, in this case a smiley face next to the welcome message.
		 */
		smile = new JLabel("");
		image2 = new ImageIcon(this.getClass().getResource("smile.png"));
		smile.setIcon(image2);
		smile.setBounds(270, 39, 24, 20);
		add(smile);
		
		/*
		 * JLabel for the clock method above (stating the time), setting its font and bounds
		 */
		labelClock = new JLabel("Time");
		labelClock.setFont(new Font("Gill Sans Nova", Font.BOLD, 14));
		labelClock.setBounds(410, 270, 156, 20);
		add(labelClock);
		
		/*
		 * JLabel for the clock method above (stating the date), setting its font and bounds
		 */
		labelDate = new JLabel("Date");
		labelDate.setFont(new Font("Gill Sans Nova", Font.BOLD, 14));
		labelDate.setBounds(405, 290, 156, 20);
		add(labelDate);
		
		clock();

	}
	
	/**
	 * The createButton needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JButton getCreateButton() {
		return createButton;
	}

	/**
	 * The loginButton needs to be made accessible to the controller in the ChatRoomController class,
	 * so a getter is made
	 */
	public JButton getLoginButton() {
		return loginButton;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

