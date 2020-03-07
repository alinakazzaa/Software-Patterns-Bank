package actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;

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
