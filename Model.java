package gui;

import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.DummyClient;
import server.User;

/**
 * Model class that contains all models for the GUI's
 * 
 * @authors wed413 & clc371
 *
 */
public class Model extends Observable {

	private LoginModel loginModel;
	private CreateNewAccountModel createModel;
	private ChatRoomModel chatModel;
	private HistoryModel historyModel;
	private User user;

	public Model() {
		loginModel = new LoginModel();
		createModel = new CreateNewAccountModel(null, null, null);
		chatModel = new ChatRoomModel();
		historyModel = new HistoryModel();
	}

	public LoginModel getLoginModel() {
		return loginModel;
	}

	public void setLoginModel(LoginModel loginModel) {
		this.loginModel = loginModel;
		setChanged();
		notifyObservers();
	}

	public CreateNewAccountModel getCreateModel() {
		return createModel;
	}

	public void setCreateModel(CreateNewAccountModel createModel) {
		this.createModel = createModel;
		setChanged();
		notifyObservers();
	}

	public ChatRoomModel getChatModel() {
		return chatModel;
	}

	public void setChatModel(ChatRoomModel chatModel) {
		this.chatModel = chatModel;
	}
	
	public HistoryModel getHistoryModel() {
		return historyModel;
	}

	public void setHistoryModel(HistoryModel historyModel) {
		this.historyModel = historyModel;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
		setChanged();
		notifyObservers();
	}
	
}
