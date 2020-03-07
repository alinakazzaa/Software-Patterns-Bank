package actions;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import banking.BankingMain;
import classes.Customer;
import menus.AdminMenu;

public class NavigateCustomerCollection extends JFrame implements ActionListener {

	private int position = 0;
	private ArrayList<Customer> customerList;
	private BankingMain main;
	JFrame f;
	JButton first, previous, next, last, cancel;
	JPanel gridPanel, buttonPanel, cancelPanel;
	JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField,
			passwordTextField;;
	Container content;

	public NavigateCustomerCollection() {
		main = BankingMain.getInstance();
		this.customerList = main.getCustomers();
		navigateCreated();
	}

	public void navigateCreated() {

		content = getContentPane();
		content.setLayout(new BorderLayout());

		buttonPanel = new JPanel();
		gridPanel = new JPanel(new GridLayout(8, 2));
		cancelPanel = new JPanel();

		firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
		surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
		pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
		dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
		customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
		passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
		firstNameTextField = new JTextField(20);
		surnameTextField = new JTextField(20);
		pPSTextField = new JTextField(20);
		dOBTextField = new JTextField(20);
		customerIDTextField = new JTextField(20);
		passwordTextField = new JTextField(20);

		first = new JButton("First");
		first.addActionListener(this);
		previous = new JButton("Previous");
		previous.addActionListener(this);
		next = new JButton("Next");
		next.addActionListener(this);
		last = new JButton("Last");
		last.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this); // added action listeners

		firstNameTextField.setEditable(false);
		surnameTextField.setEditable(false);
		pPSTextField.setEditable(false);
		dOBTextField.setEditable(false);
		customerIDTextField.setEditable(false);
		passwordTextField.setEditable(false);

		gridPanel.add(firstNameLabel);
		gridPanel.add(firstNameTextField);
		gridPanel.add(surnameLabel);
		gridPanel.add(surnameTextField);
		gridPanel.add(pPPSLabel);
		gridPanel.add(pPSTextField);
		gridPanel.add(dOBLabel);
		gridPanel.add(dOBTextField);
		gridPanel.add(customerIDLabel);
		gridPanel.add(customerIDTextField);
		gridPanel.add(passwordLabel);
		gridPanel.add(passwordTextField);

		buttonPanel.add(first);
		buttonPanel.add(previous);
		buttonPanel.add(next);
		buttonPanel.add(last);

		cancelPanel.add(cancel);

		content.add(gridPanel, BorderLayout.NORTH);
		content.add(buttonPanel, BorderLayout.CENTER);
		content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);

		setContentPane(content);
		setSize(400, 300);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
			AdminMenu.getInstance();

		} else {

			switch (e.getActionCommand()) {
			case "First":
				position = 0;
				break;
			case "Previous":
				if (position != 0) {
					position = position - 1;
				}
				break;
			case "Next":
				if (position != customerList.size() - 1) {
					position = position + 1;
				}
				break;
			case "Last":
				position = customerList.size() - 1;
				break;
			}

			setPosition(position);
		}
	}

	public void setPosition(int position) { // method instead of repeating
		firstNameTextField.setText(customerList.get(position).getFirstName());
		surnameTextField.setText(customerList.get(position).getSurname());
		pPSTextField.setText(customerList.get(position).getPPS());
		dOBTextField.setText(customerList.get(position).getDOB());
		customerIDTextField.setText(customerList.get(position).getCustomerID());
		passwordTextField.setText(customerList.get(position).getPassword());
	}

}
