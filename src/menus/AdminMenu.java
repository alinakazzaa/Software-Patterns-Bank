package menus;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classes.Customer;


public class AdminMenu extends JFrame {
	JFrame f, f1;
	JPanel deleteCustomerPanel, deleteAccountPanel, bankChargesPanel, interestPanel,
			editCustomerPanel, navigatePanel, summaryPanel, accountPanel, returnPanel;
	JButton deleteCustomer, deleteAccount, bankChargesButton, interestButton, editCustomerButton, navigateButton, 
			summaryButton, accountButton, returnButton;
	JLabel label1;
	Container content;

	private StartMenu start = new StartMenu();
	
	public AdminMenu() {
	
	if(validateUser()) {
		adminMenuCreated();
	}
		
}

	public void adminMenuCreated() {
		f = new JFrame("Administrator Menu");
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		f.setVisible(true);
		
		deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		deleteCustomer = new JButton("Delete Customer");	
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomerPanel.add(deleteCustomer);
		
		
		deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));	
		deleteAccountPanel.add(deleteAccount);
		
		bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));	
		bankChargesPanel.add(bankChargesButton);
		
		interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		interestButton = new JButton("Apply Interest");
		interestPanel.add(interestButton);
		interestButton.setPreferredSize(new Dimension(250, 20));
		
		editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		editCustomerButton = new JButton("Edit existing Customer");
		editCustomerPanel.add(editCustomerButton);
		editCustomerButton.setPreferredSize(new Dimension(250, 20));
		
		navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navigateButton = new JButton("Navigate Customer Collection");
		navigatePanel.add(navigateButton);
		navigateButton.setPreferredSize(new Dimension(250, 20));
		
		summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		summaryButton = new JButton("Display Summary Of All Accounts");
		summaryPanel.add(summaryButton);
		summaryButton.setPreferredSize(new Dimension(250, 20));
		
		accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		accountButton = new JButton("Add an Account to a Customer");
		accountPanel.add(accountButton);
		accountButton.setPreferredSize(new Dimension(250, 20));
		
		returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		returnButton = new JButton("Exit Admin Menu");
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
	//	content.add(deleteAccountPanel);
		content.add(returnPanel);
		
	}
	
	public boolean validateUser() {
		boolean loop = true; boolean loop2 = false; boolean cont = false; boolean isValid = false;
		
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
				}
			}

			if (cont) {
				loop = false;
				isValid = true;
			}

		}
		
		return isValid;
	}

}
