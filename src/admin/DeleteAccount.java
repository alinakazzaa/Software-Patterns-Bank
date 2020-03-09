package admin;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;
import menus.AdminMenu;

public class DeleteAccount implements ActionListener {

	private Customer customer;
	private CustomerAccount account;
	private BankingMain main;
	JComboBox<String> box;
	JPanel boxPanel;
	JLabel label;
	JButton continueButton, returnButton;
	Container content;
	
	public DeleteAccount(Customer customer) {
		this.customer = customer;
		main = BankingMain.getInstance();
		deleteAccountCreated();
	}
	
	public void deleteAccountCreated() {

		box = new JComboBox<String>();
		
		for (int i = 0; i < customer.getAccounts().size(); i++) {
			box.addItem(customer.getAccounts().get(i).getNumber());
		}

		boxPanel = new JPanel();

		label = new JLabel("Select the account to delete:");
		boxPanel.add(label);
		boxPanel.add(box);
		JPanel buttonPanel = new JPanel();
		continueButton = new JButton("Delete");
		continueButton.addActionListener(this);
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		
		buttonPanel.add(continueButton);
		buttonPanel.add(returnButton);
		
		content = main.getFrame().getContentPane();
		content.setLayout(new GridLayout(2, 1));

		content.add(boxPanel);
		content.add(buttonPanel);
		
		main.getFrame().setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		account = customer.findAccount(box.getSelectedItem().toString());
		
		if(e.getActionCommand().equals("Delete")) {
			customer.getAccounts().remove(account);
			box.removeItem(account.getNumber());
			JOptionPane.showMessageDialog(null, "Successfully deleted account!", "Success!",JOptionPane.INFORMATION_MESSAGE);
		} else {
			AdminMenu.getInstance();
		}
		
	}

}
