package actions;

import java.util.Date;

import javax.swing.JOptionPane;

import banking.BankingMain;
import classes.AccountTransaction;
import classes.CustomerAccount;
import classes.CustomerCurrentAccount;
import menus.CustomerMenu;

public class AccountWithdraw {

	private CustomerAccount acc;

	public AccountWithdraw(CustomerAccount acc) {
		this.acc = acc;
		accountWithdrawCreated();
	}

	public void accountWithdrawCreated() {
		boolean loop = true;
		boolean on = true;
		double withdraw = 0;

		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					CustomerMenu.getInstance();
					loop = false;
					on = false;
				}

				String Pin = JOptionPane.showInputDialog(null, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (on) {
					if (checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog(null, "Pin entry successful", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						count--;
						JOptionPane.showMessageDialog(null, "Incorrect pin. " + count + " attempts remaining.", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					}

				}
			}
		}

		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(null, "Enter amount you wish to withdraw (max 500):");// the
																													// isNumeric
																													// method
																													// tests
																													// to
																													// see
																													// if
																													// the
																													// string
																													// entered
																													// was
																													// numeric.
			if (BankingMain.isNumeric(balanceTest)) {

				withdraw = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				JOptionPane.showMessageDialog(null, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (withdraw > 500) {
				JOptionPane.showMessageDialog(null, "500 is the maximum you can withdraw at a time.", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}
			if (withdraw > acc.getBalance()) {
				JOptionPane.showMessageDialog(null, "Insufficient funds.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}

			String euro = "\u20ac";
			acc.setBalance(acc.getBalance() - withdraw);
			// recording transaction:
//		String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Date date = new Date();
			String date2 = date.toString();

			String type = "Withdraw";
			double amount = withdraw;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			acc.getTransactionList().add(transaction);

			JOptionPane.showMessageDialog(null, withdraw + euro + " withdrawn.", "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "New balance = " + acc.getBalance() + euro, "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
