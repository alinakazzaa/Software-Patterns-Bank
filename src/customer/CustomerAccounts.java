package customer;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;
import dialog.MessageDialog;
import menus.CustomerAccountMenu;
import menus.CustomerMenu;
import menus.StartMenu;

public class CustomerAccounts implements ActionListener {
	
	private BankingMain main;
	private StartMenu start;
	private Customer customer;
	private CustomerAccount acc;
	private String title = "Oops!";
	
	JComboBox<String> box;
	String userType;
	JPanel panel2;
	JButton add, returnButton, continueButton;
	Container content;
	JPanel buttonPanel, boxPanel, labelPanel;
	JLabel label;
	
	public CustomerAccounts() {
		customerAccountsCreated();
	}
	
	public void customerAccountsCreated() {
		
		main = BankingMain.getInstance();
		main.createFrame("Customer Menu");
		
		if (CustomerMenu.getInstance().getCustomer().getAccounts().size() == 0) {
			new MessageDialog(title, "This customer does not have any accounts yet.\n An admin must create an account for this customer\n for them to be able to use customer functionality.");
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
			content = main.getFrame().getContentPane();
			content.setLayout(new GridLayout(3, 1));
			content.add(labelPanel);
			content.add(boxPanel);
			content.add(buttonPanel);
			
			main.getFrame().setVisible(true);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Return")) {
			// sign out user from state - ie set to null
			CustomerMenu.getInstance().setCustomer(null);
			start.menuStart();
		} else {
			new CustomerAccountMenu(acc);
		}
		
	}

}
