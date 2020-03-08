package actions;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classes.Customer;
import classes.CustomerAccount;
import classes.CustomerCurrentAccount;
import classes.CustomerDepositAccount;
import menus.AdminMenu;

public class ApplyBankCharges implements ActionListener {

	private Customer customer;
	JFrame f;
	private CustomerAccount acc;
	JComboBox<String> box;
	JPanel boxPanel, buttonPanel;
	JButton continueButton, returnButton;
	Container content;

	public ApplyBankCharges(Customer customer) {
		this.customer = customer;
		applyChargesCreated();
	}

	public void applyChargesCreated() {

		f = new JFrame("Administrator Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);

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
		content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));

		content.add(boxPanel);
		content.add(buttonPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Return")) {
			f.dispose();
			AdminMenu.getInstance();
		} else {

			for (int i = 0; i < customer.getAccounts().size(); i++) {
				if (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
					acc = customer.getAccounts().get(i);
				}
			}

			String euro = "\u20ac";

			if (acc instanceof CustomerDepositAccount) {

				JOptionPane.showMessageDialog(f, "25" + euro + " deposit account fee aplied.", "",
						JOptionPane.INFORMATION_MESSAGE);
				acc.setBalance(acc.getBalance() - 25);
				JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance(), "Success!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			if (acc instanceof CustomerCurrentAccount) {

				JOptionPane.showMessageDialog(f, "15" + euro + " current account fee aplied.", "",
						JOptionPane.INFORMATION_MESSAGE);
				acc.setBalance(acc.getBalance() - 25);
				JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance(), "Success!",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

}
