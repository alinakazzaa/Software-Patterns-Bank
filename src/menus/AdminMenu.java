package menus;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import actions.AddAccountToCustomer;
import actions.ApplyBankCharges;
import actions.ApplyInterest;
import actions.DeleteAccount;
import actions.DeleteCustomer;
import actions.EditCustomer;
import actions.NavigateCustomerCollection;
import actions.Summary;
import banking.BankingMain;
import classes.Customer;

public class AdminMenu extends JFrame implements ActionListener {
	
	JFrame f;
	JPanel deleteCustomerPanel, deleteAccountPanel, bankChargesPanel, interestPanel, editCustomerPanel, navigatePanel,
			summaryPanel, accountPanel, returnPanel;
	JButton deleteCustomer, deleteAccount, bankChargesButton, interestButton, editCustomerButton, navigateButton,
			summaryButton, accountButton, returnButton;
	JLabel label1;
	Container content;

	private StartMenu start = new StartMenu();
	private static AdminMenu admin;
	private BankingMain main;
	private ArrayList<Customer> customerList;
	private String adminState;

	public AdminMenu() {

		main = BankingMain.getInstance();
		customerList = main.getCustomers();
		
		if(adminState == null) {
			if (validateUser()) {
				adminMenuCreated();
			}
		} else {
			adminMenuCreated();
		}
	}
	
	public static AdminMenu getInstance() { // avoid log in each time window is called from another action - singleton
		
		if(admin == null) {
			admin = new AdminMenu();
		}
		
		return admin;
	}

	public void adminMenuCreated() {
		
		f = new JFrame("Administrator Menu");
		f.setSize(500, 400);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
		f.setVisible(true);

		// keep buttons together for validation of customer list size later
		deleteCustomer = new JButton("Delete Customer");
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomer.addActionListener(this);

		deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));
		deleteAccount.addActionListener(this);

		bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));
		bankChargesButton.addActionListener(this);

		interestButton = new JButton("Apply Interest");
		interestButton.setPreferredSize(new Dimension(250, 20));
		interestButton.addActionListener(this);

		editCustomerButton = new JButton("Edit existing Customer");
		editCustomerButton.setPreferredSize(new Dimension(250, 20));
		editCustomerButton.addActionListener(this);

		navigateButton = new JButton("Navigate Customer Collection");
		navigateButton.setPreferredSize(new Dimension(250, 20));
		navigateButton.addActionListener(this);

		summaryButton = new JButton("Display Summary Of All Accounts");
		summaryButton.setPreferredSize(new Dimension(250, 20));
		summaryButton.addActionListener(this);

		accountButton = new JButton("Add an Account to a Customer");
		accountButton.setPreferredSize(new Dimension(250, 20));
		accountButton.addActionListener(this);

		returnButton = new JButton("Exit Admin Menu");
		returnButton.addActionListener(this);

		deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		deleteCustomerPanel.add(deleteCustomer);

		deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		deleteAccountPanel.add(deleteAccount);

		bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bankChargesPanel.add(bankChargesButton);

		interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		interestPanel.add(interestButton);

		editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustomerPanel.add(editCustomerButton);

		navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navigatePanel.add(navigateButton);

		summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		summaryPanel.add(summaryButton);

		accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		accountPanel.add(accountButton);

		returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		returnPanel.add(returnButton);

		label1 = new JLabel("Please select an option");

		content = f.getContentPane();
		content.setLayout(new GridLayout(9, 1));
		content.add(label1);
		content.add(accountPanel);
		content.add(bankChargesPanel);
		content.add(interestPanel);
		content.add(editCustomerPanel);
		content.add(navigatePanel);
		content.add(summaryPanel);
		content.add(deleteCustomerPanel);
		content.add(deleteAccountPanel);
		content.add(returnPanel);

	}

	public boolean validateUser() {
		boolean loop = true;
		boolean loop2 = false;
		boolean cont = false;
		boolean isValid = false;
		String adminState;
		
		
		while (loop) {

			Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

			if (!adminUsername.equals("admin")) {

				int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					loop = true;
				}

				else if (reply == JOptionPane.NO_OPTION) {
					loop = false;
					start.menuStart();
				}
			} else {
				loop2 = true;
			}

			while (loop2) {
				Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");

				if (!adminPassword.equals("admin11"))// search admin list for admin with matching admin password
				{
					int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {

					} else if (reply == JOptionPane.NO_OPTION) {
						loop2 = false;
						start.menuStart();
					}
				} else {
					loop2 = false;
					cont = true;
					adminState = "Username: " + adminUsername.toString() + "/n" + "Password: " + adminPassword.toString();
					setAdminState(adminState);
				}
			}

			if (cont) {
				loop = false;
				isValid = true;
				
			}

		}

		return isValid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Customer customer = null;
		boolean loop = true;
		boolean found = false;

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			
		} else if (e.getActionCommand().equals("Exit Admin Menu")) {
			f.dispose();
			start.menuStart();
		} else if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			
		} else if(e.getActionCommand().equals("Navigate Customer Collection")) {
			f.dispose();
			new NavigateCustomerCollection();
			
		} else if(e.getActionCommand().equals("Display Summary Of All Accounts")) {
			f.dispose();
			new Summary();
			
		} else {

			while (loop) {

				String customerID = JOptionPane.showInputDialog(f,
						"Customer ID:");

				if (customerID.isEmpty() || customerID == null) {
					System.out.println("empty");
				} else {
					
					customer = main.getCustomerByID(customerID);

					if (customer != null) {
						loop = false;
						found = true;

					} else {

						int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
								JOptionPane.YES_NO_OPTION);
						if (reply != JOptionPane.YES_OPTION) {
							loop = false;
						}
					}

				}

			}
			
			if (found) {
				switch (e.getActionCommand()) {
				case "Add an Account to a Customer":
					new AddAccountToCustomer(customer);
					break;
				case "Apply Bank Charges":
					
					if(customer.getAccounts().size() > 0) {
						f.dispose();
						new ApplyBankCharges(customer);
					} else {
						JOptionPane.showMessageDialog(f, "User has no accounts!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
					
				case "Apply Interest":

					if(customer.getAccounts().size() > 0) {
						f.dispose();
						new ApplyInterest(customer);
					} else {
						JOptionPane.showMessageDialog(f, "User has no accounts!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
					
				case "Edit existing Customer":
					
					f.dispose();
					new EditCustomer(customer);
					break;
					
				case "Delete Customer":
					
					new DeleteCustomer(customer);
					break;
					
				case "Delete Account":
					
					if(customer.getAccounts().size() > 0) {
						f.dispose();
						new DeleteAccount(customer);
					} else {
						JOptionPane.showMessageDialog(f, "User has no accounts!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
					
				}
				
			}

		}
	}
	
	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}
	
	public String getAdminState() {
		return this.adminState;
	}

}
