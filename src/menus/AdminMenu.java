package menus;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import admin.ApplyBankCharges;
import admin.ApplyInterest;
import admin.DeleteAccount;
import admin.DeleteCustomer;
import admin.EditCustomer;
import admin.NavigateCustomers;
import admin.AccountSummary;
import admin.AddCustomerAccount;
import banking.BankingMain;
import banking.UserLogin;
import classes.Customer;
import dialog.ConfirmDialog;
import dialog.DialogFrame;
import dialog.InputDialog;
import dialog.MessageDialog;

public class AdminMenu implements ActionListener, UserMenu {

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
	private String title = "Oops!";
	private DialogFrame dialog;
	private UserLogin login;
	private Customer customer = null;

	public AdminMenu() {

		main = BankingMain.getInstance();
		customerList = main.getCustomers();
		login = new UserLogin();

		if (adminState == null) {
			if (logIn()) {
				adminMenuCreated();
			}
		} else {
			adminMenuCreated();
		}
	}

	public static AdminMenu getInstance() { // avoid log in each time window is called from another action - singleton

		if (admin == null) {
			admin = new AdminMenu();
		}

		return admin;
	}

	public void adminMenuCreated() {
		main.createFrame("Administrator Menu");

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

		content = main.getFrame().getContentPane();
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

		main.getFrame().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Exit Admin Menu")) {

			main.getFrame().dispose();
			start.menuStart();

		} else if (customerList.isEmpty()) {

			new MessageDialog(title, "There are no customers yet!");

		} else if (customerList.isEmpty()) {

			new MessageDialog(title, "There are no customers yet!");

		} else if (e.getActionCommand().equals("Navigate Customer Collection")) {
			new NavigateCustomers();

		} else if (e.getActionCommand().equals("Display Summary Of All Accounts")) {
			new AccountSummary();

		} else {

			if (foundCustomer()) {
				switch (e.getActionCommand()) {
				case "Add an Account to a Customer":
					new AddCustomerAccount(customer);
					break;
				case "Apply Bank Charges":

					if (customer.getAccounts().size() > 0) {
						new ApplyBankCharges(customer);
					} else {

						new MessageDialog(title, "User has no accounts!");
					}
					break;

				case "Apply Interest":

					if (customer.getAccounts().size() > 0) {
						new ApplyInterest(customer);
					} else {

						new MessageDialog(title, "User has no accounts!");
					}
					break;

				case "Edit existing Customer":

					new EditCustomer(customer);
					break;

				case "Delete Customer":

					new DeleteCustomer(customer);
					break;

				case "Delete Account":

					if (customer.getAccounts().size() > 0) {

						new DeleteAccount(customer);
					} else {

						new MessageDialog(title, "User has no accounts!");
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

	@Override
	public void createMenu() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean logIn() {
		boolean tryAgain = true;
		boolean isValid = false;
		
		String adminState;

		while (tryAgain) {
			
			if(login.validAdmin()) {
				if(login.validPassword("Admin")) {
					isValid = true;
					tryAgain = false;
					adminState = "Username: admin/n" + "Password: admin11";
					setAdminState(adminState);
				}
				
			} else {
				dialog = new ConfirmDialog(title, "Incorrect Username. Try again?");
				if (((ConfirmDialog) dialog).getReply() == 1) {
					tryAgain = false;
					start.menuStart();
				}
			}
			
		}

		return isValid;
	}
	
	public boolean foundCustomer() {
		boolean found = false; boolean search = true;
		String customerID;
		
		while (search) {

			dialog = new InputDialog(null, "Customer ID:");

			customerID = (String) ((InputDialog) dialog).getInput();

			if (!customerID.isEmpty() && customerID != null) {
				customer = main.getCustomerByID(customerID);

				if (customer != null) {
					found = true;
					search = false;

				} else {
					dialog = new ConfirmDialog(null, "User not found. Try again?");

					if (((ConfirmDialog) dialog).getReply() != 0) {
						search = false;
					}
				}
			}

		}
		
		
		return found;
	}

}
