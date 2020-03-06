package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import banking.BankingMain;
import classes.Customer;

public class DeleteCustomer implements ActionListener {

	private BankingMain main = BankingMain.getInstance();
	private Customer customer;

	public DeleteCustomer(Customer customer) {
		ArrayList<Customer> customerList = main.getCustomers();

			if (customer.getAccounts().size() > 0) {
				JOptionPane.showMessageDialog(null,
						"This customer has accounts. \n You must delete a customer's accounts before deleting a customer ",
						"Oops!", JOptionPane.INFORMATION_MESSAGE);
			} else {
				customerList.remove(customer);
				JOptionPane.showMessageDialog(null, "Customer Deleted ", "Success.", JOptionPane.INFORMATION_MESSAGE);
			}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
