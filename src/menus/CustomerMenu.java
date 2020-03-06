package menus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;

// split functions where validating into own methods

public class CustomerMenu extends JFrame {

	JFrame f1;
	JPanel panel2;
	JButton add;

	private StartMenu start = new StartMenu();
	private BankingMain main = BankingMain.getInstance();

	public CustomerMenu() {
		validateCustomer();
	}

	public void validateCustomer() {

		boolean loop = true, loop2 = true;
		boolean cont = false;
		boolean found = false;
		System.out.println(main.getCustomers());
		Customer customer = null;

		while (loop) {
			Object customerID = JOptionPane.showInputDialog(f1, "Enter Customer ID:");

			if (main.getCustomers().size() > 0) {
				for (Customer aCustomer : main.getCustomers()) {

					if (aCustomer.getCustomerID().equals(customerID)) // search customer list for matching customer ID
					{
						found = true;
						customer = aCustomer;
					}
				}

				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						loop = false;
						loop2 = false;
						start.menuStart();
					}
				} else {
					loop = false;
				}

			} else {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					loop = true;
				} else if (reply == JOptionPane.NO_OPTION) {
					loop = false;
					loop2 = false;
					start.menuStart();
				}
			}

		}

		while (loop2) {
			Object customerPassword = JOptionPane.showInputDialog(f1, "Enter Customer Password;");

			if (!customer.getPassword().equals(customerPassword))// check if custoemr password is correct
			{
				int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?",
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
			loop2 = false;
			loop = false;
			System.out.println("Show customer menu");
		}
	}

}
