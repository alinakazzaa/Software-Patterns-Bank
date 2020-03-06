package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import banking.BankingMain;
import classes.ATMCard;
import classes.AccountTransaction;
import classes.Customer;
import classes.CustomerCurrentAccount;
import classes.CustomerDepositAccount;
import dialog.DialogFrame;

public class AddAccountToCustomer extends JFrame implements ActionListener {

	JFrame f;
	private BankingMain main = BankingMain.getInstance();
	private Customer customer;
	private int index;
	private String account;
	String[] choices = { "Current Account", "Deposit Account" };

	public AddAccountToCustomer(Customer customer, int index) {

		this.customer = customer;
		this.index = index;

		// a combo box in an dialog box that asks the admin what type of account they
		// wish to create (deposit/current)
		account = (String) JOptionPane.showInputDialog(null, "Please choose account type", "Account Type",
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

		if (account.equals("Current Account")) {
			// create current account
			boolean valid = true;
			double balance = 0;
			String number = String.valueOf("C" + (index + 1) * 10 + (customer.getAccounts().size() + 1));// this
																											// simple
																											// algorithm
																											// generates
																											// // //
																											// account
																											// // number
			ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
			int randomPIN = (int) (Math.random() * 9000) + 1000;
			String pin = String.valueOf(randomPIN);

			ATMCard atm = new ATMCard(randomPIN, valid);

			CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);
			
			customer.addAccount(current);
			
			JOptionPane.showMessageDialog(f, "Account number = " + number + "\n PIN = " + pin, "Account created.",
					JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println(customer.getAccounts().toString());

		}

		if (account.equals("Deposit Account")) {
			// create deposit account

			double balance = 0, interest = 0;
			String number = String.valueOf("D" + (index + 1) * 10 + (customer.getAccounts().size() + 1));// this
																											// simple
																											// algorithm
																											// generates
																											// the
																											// account
																											// number
			ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

			CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance, transactionList);
			
			customer.getAccounts().add(deposit);
			JOptionPane.showMessageDialog(f, "Account number = " + number, "Account created.",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println(ae.getActionCommand());
		System.out.println(account);
//				boolean loop = false; // remove unused variable

	}
	
}
