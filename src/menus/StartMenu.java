package menus;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import actions.NewCustomer;
import banking.BankingMain;
import classes.Customer;




public class StartMenu extends JFrame{
	
	JFrame f, f1;
		JLabel customerIDLabel, passwordLabel;
		JTextField customerIDTextField, passwordTextField;
		Container content;
		Customer e;

	 JPanel panel2;
		JButton add;
		String 	PPS,firstName,surname,DOB,CustomerID;
		
	public void menuStart()
	{
		   /*The menuStart method asks the user if they are a new customer, an existing customer or an admin. It will then start the create customer process
		  if they are a new customer, or will ask them to log in if they are an existing customer or admin.*/

			f = new JFrame("User Type");
			f.setSize(400, 300);
			f.setLocation(200, 200);
			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});

			JPanel userTypePanel = new JPanel();
			final ButtonGroup userType = new ButtonGroup();
			JRadioButton radioButton;
			userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
			radioButton.setActionCommand("Customer");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("Administrator"));
			radioButton.setActionCommand("Administrator");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("New Customer"));
			radioButton.setActionCommand("New Customer");
			userType.add(radioButton);

			
			JButton continueButton = new JButton("Continue");
			JPanel continuePanel = new JPanel();
			
			
			continuePanel.add(continueButton);

			Container content = f.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(userTypePanel);
			content.add(continuePanel);
			f.setVisible(true);
			
			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String user = userType.getSelection().getActionCommand();
					
					if(user.equals("New Customer")) {
						f.dispose();
						new NewCustomer();
						
					} else if(user.equals("Customer")) {
						f.dispose();
						CustomerMenu.getInstance();
					}else {
						f.dispose();
						AdminMenu.getInstance();
							
					}
				}
			});	
			
			continuePanel.add(continueButton);
			content.add(continuePanel);
			f.setVisible(true);
	}
}