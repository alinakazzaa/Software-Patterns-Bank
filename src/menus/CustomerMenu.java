package menus;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;
import customer.CustomerAccounts;
import dialog.ConfirmDialog;
import dialog.DialogFrame;
import dialog.InputDialog;

public class CustomerMenu implements ActionListener {

	private static CustomerMenu cusMenu;
	String userType;
	JPanel panel2;
	JButton add, returnButton, continueButton;
	Container content;
	JPanel buttonPanel, boxPanel, labelPanel;
	JLabel label;
	
	private StartMenu start;
	private BankingMain main;
	private Customer customer;
	private CustomerAccount acc;
	private DialogFrame dialog;
	JComboBox<String> box;
	private String title = "Oops!";

	public CustomerMenu() {
		main = BankingMain.getInstance();
		start = new StartMenu();
		
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
		boolean loop = true, loop2 = true;
		boolean cont = false;
		boolean found = false;
		Customer customer = null;
		String customerPassword, customerID;
			
			while (loop) {
				// reusable components
				dialog = new InputDialog(null, "Enter Customer ID:");
				customerID = (String) ((InputDialog) dialog).getInput();
				
				customer = main.getCustomerByID(customerID);
				
					if(customer == null) {
						// reusable components
						dialog = new ConfirmDialog(title, "User not found. Try again?");
						
						if (((ConfirmDialog) dialog).getReply() == 0) {
							loop = true;
						} else if (((ConfirmDialog) dialog).getReply() == 1) {
							loop = false;
							loop2 = false;
							start.menuStart();
						}
					} else {
						loop = false;
						
						while (loop2) {
							// reusable components
							dialog = new InputDialog(null, "Enter Customer Password;");
							customerPassword = (String) ((InputDialog) dialog).getInput();
							
							if (!customer.getPassword().equals(customerPassword))// check if custoemr password is correct
							{
								// reusable components
								new ConfirmDialog(title, "Incorrect password. Try again?");
								
								if (((ConfirmDialog) dialog).getReply() == 0) {

								} else if (((ConfirmDialog) dialog).getReply() == 1) {
									loop2 = false;
									start.menuStart();
								}
							} else {
								loop2 = false;
								cont = true;
							}
						}

						if (cont) {
							found = true;
							setCustomer(customer);
						}
					}

			}
		
		return found;
		
	}
	
	// state pattern
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

}
