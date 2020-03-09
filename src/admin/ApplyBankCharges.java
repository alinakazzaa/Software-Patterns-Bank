package admin;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;
import classes.CustomerCurrentAccount;
import classes.CustomerDepositAccount;
import menus.AdminMenu;

public class ApplyBankCharges implements ActionListener {

	private Customer customer;
	private CustomerAccount acc;
	private BankingMain main;
	JComboBox<String> box;
	JPanel boxPanel, buttonPanel;
	JButton continueButton, returnButton;
	Container content;

	public ApplyBankCharges(Customer customer) {
		this.customer = customer;
		main = BankingMain.getInstance();
		applyChargesCreated();
	}

	public void applyChargesCreated() {

		box = new JComboBox<String>();
		
		for (int i = 0; i < customer.getAccounts().size(); i++) {

			box.addItem(customer.getAccounts().get(i).getNumber());
		}

		box.getSelectedItem();

		boxPanel = new JPanel();
		boxPanel.add(box);

		buttonPanel = new JPanel();
		continueButton = new JButton("Apply Charge");
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

		if (e.getActionCommand().equals("Return")) {
			main.getFrame().dispose();
			AdminMenu.getInstance();
		} else {

			for (int i = 0; i < customer.getAccounts().size(); i++) {
				
				if (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
					acc = customer.getAccounts().get(i);
				}
			}

			String euro = "\u20ac";

			if (acc instanceof CustomerDepositAccount) {
				// should be components dialog
				JOptionPane.showMessageDialog(null, "25" + euro + " deposit account fee aplied.", "",
						JOptionPane.INFORMATION_MESSAGE);
				acc.setBalance(acc.getBalance() - 25);
				JOptionPane.showMessageDialog(null, "New balance = " + acc.getBalance(), "Success!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			if (acc instanceof CustomerCurrentAccount) {

				JOptionPane.showMessageDialog(null, "15" + euro + " current account fee aplied.", "",
						JOptionPane.INFORMATION_MESSAGE);
				acc.setBalance(acc.getBalance() - 25);
				JOptionPane.showMessageDialog(null, "New balance = " + acc.getBalance(), "Success!",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

}
