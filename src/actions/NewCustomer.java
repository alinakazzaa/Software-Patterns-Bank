package actions;

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
import menus.StartMenu;

public class NewCustomer extends JFrame implements ActionListener {
	
	private BankingMain main;
	private StartMenu start;
	private String PPS, firstName, surname, DOB, CustomerID, password;
	JFrame f;
	JPanel panel2;
	JButton add, cancel;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
	Container content;
	
	public NewCustomer() {
		
		newCustomerCreated();
	}
	
	public void newCustomerCreated() {
		main = BankingMain.getInstance();
		start = new StartMenu();
		
		f = new JFrame("Create New Customer");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
		content = f.getContentPane();
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

		f.setVisible(true);
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
					start.menuStart();
				}
			}
			
		} else {
			f.dispose();
			start.menuStart();
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

		main.addCustomer(customer);
		f.dispose();
		JOptionPane.showMessageDialog(f, "CustomerID = " + CustomerID + "\n Password = " + password,
				"Customer created.", JOptionPane.INFORMATION_MESSAGE);
	}

}
