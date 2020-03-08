package menus;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import actions.AccountScreen;
import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;
import dialog.ConfirmDialog;
import dialog.DialogFrame;
import dialog.InputDialog;
import dialog.MessageDialog;

public class CustomerMenu extends JFrame implements ActionListener {

	private static CustomerMenu cusMenu;
	String userType;
	JFrame f;
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
	private String message, title;

	public CustomerMenu() {
		main = BankingMain.getInstance();
		start = new StartMenu();
		
		if(customer == null) {
			if(customerLogIn()) {
				customerMenuCreated();
			}
		} else {
			customerMenuCreated();
		}
		
	}
	
	public static CustomerMenu getInstance() { // avoid log in each time window is called from another action - singleton
		
		if(cusMenu == null) {
			cusMenu = new CustomerMenu();
		}
		
		return cusMenu;
	}

	public void customerMenuCreated() {

		f = new JFrame("Customer Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);

		if (customer.getAccounts().size() == 0) {
			
			message = "This customer does not have any accounts yet.\n An admin must create an account for this customer\n for them to be able to use customer functionality.";
			title = "Oops!";
			dialog = new MessageDialog(title, message);
			
			f.dispose();
			start.menuStart();
		} else {
			buttonPanel = new JPanel();
			boxPanel = new JPanel();
			labelPanel = new JPanel();

			label = new JLabel("Select Account:");
			labelPanel.add(label);

			returnButton = new JButton("Return");
			returnButton.addActionListener(this);
			buttonPanel.add(returnButton);
			
			continueButton = new JButton("Continue");
			continueButton.addActionListener(this);
			buttonPanel.add(continueButton);

			box = new JComboBox<String>();
			
			for (int i = 0; i < customer.getAccounts().size(); i++) {
				box.addItem(customer.getAccounts().get(i).getNumber());
			}

			for (int i = 0; i < customer.getAccounts().size(); i++) {
				if (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
					acc = customer.getAccounts().get(i);
				}
			}

			boxPanel.add(box);
			content = f.getContentPane();
			content.setLayout(new GridLayout(3, 1));
			content.add(labelPanel);
			content.add(boxPanel);
			content.add(buttonPanel);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		f.dispose();
		if(e.getActionCommand().equals("Return")) {
			// sign out user from state - ie set to null
			setCustomer(null);
		} else {
			new AccountScreen(acc);
		}
		
	}
	
	public boolean customerLogIn() {
		boolean loop = true, loop2 = true;
		boolean cont = false;
		boolean found = false;
		Customer customer = null;
		String customerPassword, customerID;
		
		if(main.getCustomers().size() > 0) {
			
			while (loop) {
				dialog = new InputDialog(null, "Enter Customer ID:");
				customerID = (String) ((InputDialog) dialog).getInput();
				
				customer = main.getCustomerByID(customerID);
				
					if(customer == null) {
						
						title = "Oops!";
						message = "User not found. Try again?";
						dialog = new ConfirmDialog(title, message);
						
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
							dialog = new InputDialog(null, "Enter Customer Password;");
							customerPassword = (String) ((InputDialog) dialog).getInput();
							
							if (!customer.getPassword().equals(customerPassword))// check if custoemr password is correct
							{
								title = "Oops!";
								message = "Incorrect password. Try again?";
								dialog = new ConfirmDialog(title, message);
								dialog.showDialog();
								
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
