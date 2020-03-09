package customer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;
import menus.StartMenu;

public class AddNewCustomer implements ActionListener {
	
	private static AddNewCustomer newCus;
	private String PPS, firstName, surname, DOB, CustomerID, password;
	JPanel panel2;
	JButton add, cancel;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
	Container content;
	
	public AddNewCustomer() {
		createMenu();
	}
	
	public static AddNewCustomer getInstance() {
		if(newCus == null) {
			newCus = new AddNewCustomer();
		}
		return newCus;
	}
	
	public void createMenu() {
		
		BankingMain.getInstance().createFrame("New Customer");
		content = BankingMain.getInstance().getFrame().getContentPane();
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
		add.addActionListener(this);

		cancel = new JButton("Cancel");
		cancel.addActionListener(this);

		panel2.add(add);
		panel2.add(cancel);

		content.add(panel, BorderLayout.CENTER);
		content.add(panel2, BorderLayout.SOUTH);

		BankingMain.getInstance().getFrame().setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		boolean loop = true;
		
		if(e.getActionCommand().equals("Add")) {
			
			while (loop) {
				password = JOptionPane.showInputDialog(null, "Enter 7 character Password;");

				if (password.length() != 7) // Making sure password is 7 characters
				{
					JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long",
							JOptionPane.OK_OPTION);
				} else {
					loop = false;
					addCustomer(password);
					StartMenu.getInstance().menuStart();
				}
			}
			
		} else {
			BankingMain.getInstance().dispose();
			StartMenu.getInstance().menuStart();
		}	
	}
	
	public void addCustomer(String password) {
		ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
		
		PPS = pPSTextField.getText();
		firstName = firstNameTextField.getText();
		surname = surnameTextField.getText();
		DOB = dOBTextField.getText();
		CustomerID = "ID" + PPS;
		
		Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);

		BankingMain.getInstance().addCustomer(customer);
		BankingMain.getInstance().dispose();
		JOptionPane.showMessageDialog(null, "CustomerID = " + CustomerID + "\n Password = " + password,
				"Customer created.", JOptionPane.INFORMATION_MESSAGE);
	}

}
