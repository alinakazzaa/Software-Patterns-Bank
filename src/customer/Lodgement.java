package customer;

import java.util.Date;

import javax.swing.JOptionPane;

import banking.BankingMain;
import classes.AccountTransaction;
import classes.CustomerAccount;
import classes.CustomerCurrentAccount;
import menus.CustomerAccountMenu;

public class Lodgement {
	
	private CustomerAccount acc;
	
	public Lodgement(CustomerAccount acc) {
		this.acc = acc;
		accountLodgementCreated();
	}
	
	public void accountLodgementCreated() {
		boolean loop = true;
		boolean on = true;
		double balance = 0;

		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					// should be replaced by dialog component - no time
					JOptionPane.showMessageDialog(null, "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					new CustomerAccountMenu(acc);
					loop = false;
					on = false;
				}

				String Pin = JOptionPane.showInputDialog(null, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (on) {
					if (checkPin == i) {
						loop = false;
						// should be replaced by dialog component - no time
						JOptionPane.showMessageDialog(null, "Pin entry successful", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						count--;
						// should be replaced by dialog component - no time
						JOptionPane.showMessageDialog(null, "Incorrect pin. " + count + " attempts remaining.", "Pin",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}

		}
		if (on == true) {
			// should be replaced by dialog component - no time
			String balanceTest = JOptionPane.showInputDialog(null, "Enter amount you wish to lodge:");

			if (BankingMain.isNumeric(balanceTest)) {

				balance = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				// should be replaced by dialog component - no time
				JOptionPane.showMessageDialog(null, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			String euro = "\u20ac";
			acc.setBalance(acc.getBalance() + balance);
			Date date = new Date();
			String date2 = date.toString();
			String type = "Lodgement";
			double amount = balance;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			acc.addTransaction(transaction);
			// should be replaced by dialog component - no time
			JOptionPane.showMessageDialog(null, balance + euro + " added do you account!", "Lodgement",
					JOptionPane.INFORMATION_MESSAGE);
			// should be replaced by dialog component - no time
			JOptionPane.showMessageDialog(null, "New balance = " + acc.getBalance() + euro, "Lodgement",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}


}
