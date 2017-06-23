package gui;

import java.awt.event.ActionListener;

import client.ClientInterface;

/**
 * Abstract controller class contains all common aspects/functionalities of the controllers
 * 
 * @authors wed413 & clc371
 *
 */
public abstract class Controller implements ActionListener {
	private View view;
	private Model model;
	private ClientInterface client;
	
	public Controller(Model model, View view, ClientInterface client) {
		this.view = view;
		this.model = model;
		this.client = client;
	}
}
