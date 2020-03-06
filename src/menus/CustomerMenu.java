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

public class CustomerMenu extends JFrame {

	String userType;
	JFrame f1;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
	JPanel panel2;
	JButton add;
	
	private StartMenu start = new StartMenu();
	private BankingMain main = BankingMain.getInstance();

	public CustomerMenu(String userType) {
		this.userType = userType;

		if (userType.equals("New Customer")) {
			newCustomer();
		} else {
			existingCustomer();
		}
	}

	public void newCustomer() {

		f1 = new JFrame("Create New Customer");
		f1.setSize(400, 300);
		f1.setLocation(200, 200);
		f1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		Container content = f1.getContentPane();
		content.setLayout(new BorderLayout());

		firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
		surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
		pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
		dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
		firstNameTextField = new JTextField(20);
		surnameTextField = new JTextField(20);
		pPSTextField = new JTextField(20);
		dOBTextField = new JTextField(20);
		JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.add(firstNameLabel);
		panel.add(firstNameTextField);
		panel.add(surnameLabel);
		panel.add(surnameTextField);
		panel.add(pPPSLabel);
		panel.add(pPSTextField);
		panel.add(dOBLabel);
		panel.add(dOBTextField);

		panel2 = new JPanel();
		add = new JButton("Add");
		add.addActionListener(new AddCustomer());

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.dispose();
				start.menuStart();
			}
		});

		panel2.add(add);
		panel2.add(cancel);

		content.add(panel, BorderLayout.CENTER);
		content.add(panel2, BorderLayout.SOUTH);

		f1.setVisible(true);

	}

	public void existingCustomer() {
		
		boolean loop = true, loop2 = true;
		boolean cont = false;
		boolean found = false;
		System.out.println(main.getCustomers());
		Customer customer = null;
		
		while (loop) {
			Object customerID = JOptionPane.showInputDialog(f1, "Enter Customer ID:");

			if(main.getCustomers().size() > 0) {
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

	private class AddCustomer implements ActionListener {
		String PPS, firstName, surname, DOB, CustomerID, password;

		public void actionPerformed(ActionEvent e) {

			PPS = pPSTextField.getText();
			firstName = firstNameTextField.getText();
			surname = surnameTextField.getText();
			DOB = dOBTextField.getText();
			password = "";
			CustomerID = "ID" + PPS;

			boolean loop = true;

			while (loop) {
				password = JOptionPane.showInputDialog(f1, "Enter 7 character Password;");

				if (password.length() != 7) // Making sure password is 7 characters
				{
					JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long",
							JOptionPane.OK_OPTION);
				} else {
					loop = false;
				}
			}
			
			ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
			Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);

			main.addCustomer(customer);

			JOptionPane.showMessageDialog(f1, "CustomerID = " + CustomerID + "\n Password = " + password,
					"Customer created.", JOptionPane.INFORMATION_MESSAGE);
			f1.dispose();
			start.menuStart();
		}

	}
	
}
