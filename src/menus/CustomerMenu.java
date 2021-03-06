package menus;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import banking.UserLogin;
import classes.Customer;
import classes.CustomerAccount;
import customer.CustomerAccounts;
import dialog.ConfirmDialog;
import dialog.DialogFrame;

public class CustomerMenu implements ActionListener, UserMenu {

	private static CustomerMenu cusMenu;
	String userType;
	JPanel panel2;
	JButton add, returnButton, continueButton;
	Container content;
	JPanel buttonPanel, boxPanel, labelPanel;
	JLabel label;
	
	private StartMenu start;
	private Customer customer;
	private CustomerAccount acc;
	private DialogFrame dialog;
	JComboBox<String> box;
	private String title = "Oops!";
	private UserLogin login;

	public CustomerMenu() {
		start = new StartMenu();
		login = new UserLogin();
		
		if(customer == null) {
			if(customerLogIn()) {
				new CustomerAccounts();
			}
		} else {
			new CustomerAccounts();
		}
		
	}
	
	public static CustomerMenu getInstance() { // avoid log in each time window is called from another action - singleton
		
		if(cusMenu == null) {
			cusMenu = new CustomerMenu();
		}
		
		return cusMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Return")) {
			// sign out user from state - ie set to null
			setCustomer(null);
			start.menuStart();
		} else {
			new CustomerAccountMenu(acc);
		}
		
	}
	
	public boolean customerLogIn() {
		boolean tryAgain = true;
		boolean isValid = false;
		
		Customer customer = null;

		while (tryAgain) {
			
			if(login.validCustomer()) {
				if(login.validPassword("Customer")) {
					isValid = true;
					tryAgain = false;
					setCustomer(customer);
				}
				
			} else {
				dialog = new ConfirmDialog(title, "User not found. Try again?");
				if (((ConfirmDialog) dialog).getReply() == 1) {
					tryAgain = false;
					start.menuStart();
				}
			}
			
		}

		return isValid;
		
	}
	
	// state pattern
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public void createMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean logIn() {
		// TODO Auto-generated method stub
		return false;
	}

}
