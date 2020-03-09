package admin;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import banking.BankingMain;
import classes.ATMCard;
import classes.AccountTransaction;
import classes.Customer;
import classes.CustomerCurrentAccount;
import classes.CustomerDepositAccount;

public class AddCustomerAccount {


	private BankingMain main = BankingMain.getInstance();
	private int index;
	private String account;
	String[] choices = { "Current Account", "Deposit Account" };
	private String number, pin;
	private ATMCard atm;
	private boolean valid;
	private int balance, randomPIN;
	private ArrayList<AccountTransaction> transactionList;
	private CustomerCurrentAccount current;

	public AddCustomerAccount(Customer customer) {
		this.index = main.getCustomers().indexOf(customer);

		// again should be component here
		account = (String) JOptionPane.showInputDialog(null, "Please choose account type", "Account Type",
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

		if (account.equals("Current Account")) {
			// create current account
			valid = true;
			balance = 0;
			number = String.valueOf("C" + (index + 1) * 10 + (customer.getAccounts().size() + 1));
			
			transactionList = new ArrayList<AccountTransaction>();
			
			randomPIN = (int) (Math.random() * 9000) + 1000;
			
			pin = String.valueOf(randomPIN);

			atm = new ATMCard(randomPIN, valid);

			current = new CustomerCurrentAccount(atm, number, balance, transactionList);
			
			customer.addAccount(current);
			
			JOptionPane.showMessageDialog(null, "Account number = " + number + "\n PIN = " + pin, "Account created.",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (account.equals("Deposit Account")) {

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
			JOptionPane.showMessageDialog(null, "Account number = " + number, "Account created.",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
