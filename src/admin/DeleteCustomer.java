package admin;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import banking.BankingMain;
import classes.Customer;

public class DeleteCustomer {

	private BankingMain main;
	private ArrayList<Customer> customerList;

	public DeleteCustomer(Customer customer) {
		main = BankingMain.getInstance();
		customerList = main.getCustomers();

			if (customer.getAccounts().size() > 0) {
				JOptionPane.showMessageDialog(null,
						"This customer has accounts. \n You must delete a customer's accounts before deleting a customer ",
						"Oops!", JOptionPane.INFORMATION_MESSAGE);
			} else {
				customerList.remove(customer);
				JOptionPane.showMessageDialog(null, "Customer Deleted ", "Success.", JOptionPane.INFORMATION_MESSAGE);
			}

	}

}
