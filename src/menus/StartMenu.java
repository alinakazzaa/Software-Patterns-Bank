package menus;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import banking.BankingMain;
import classes.Customer;
import factory.GetUserFactory;

public class StartMenu {

	private BankingMain main;
	private static StartMenu start;
	JPanel userTypePanel, continuePanel;
	JLabel customerIDLabel, passwordLabel;
	JTextField customerIDTextField, passwordTextField;
	Container content;
	Customer e;
	JRadioButton radioButton;
	JPanel panel2;
	JButton add, continueButton;
	String PPS, firstName, surname, DOB, CustomerID;
	
	
	public static StartMenu getInstance() {
		if(start == null) {
			start = new StartMenu();
		}
		
		return start;
	}

	public void menuStart() {
		/*
		 * The menuStart method asks the user if they are a new customer, an existing
		 * customer or an admin. It will then start the create customer process if they
		 * are a new customer, or will ask them to log in if they are an existing
		 * customer or admin.
		 */
		main = BankingMain.getInstance();

		main.createFrame("User Type");

		userTypePanel = new JPanel();
		final ButtonGroup userType = new ButtonGroup();

		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		userType.add(radioButton);

		continueButton = new JButton("Continue");
		continuePanel = new JPanel();

		continuePanel.add(continueButton);

		content = main.getFrame().getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = userType.getSelection().getActionCommand();
				// can use abstract factory method here
				
				GetUserFactory.getUserFactory(user).getMenu();
				
			}
		});

		continuePanel.add(continueButton);
		content.add(continuePanel);

		main.getFrame().setVisible(true);
	}
}