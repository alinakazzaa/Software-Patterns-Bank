package actions;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classes.Customer;
import menus.AdminMenu;

public class EditCustomer implements ActionListener {

	private Customer customer;
	JFrame f;
	JLabel label1, firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel;
	JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField, passwordTextField;
	Container content;
	JPanel textPanel, cancelPanel;
	JButton saveButton, cancelButton;
	
	
	public EditCustomer(Customer customer) {
		this.customer = customer;
		editCustomerCreated();
	}
	
	public void editCustomerCreated() {
		f = new JFrame("Administrator Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); } // outsource to window frames package
		});       
	
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
	
		textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		cancelPanel = new JPanel();
		
		textPanel.add(firstNameLabel);
		textPanel.add(firstNameTextField);
		textPanel.add(surnameLabel);
		textPanel.add(surnameTextField);
		textPanel.add(pPPSLabel);
		textPanel.add(pPSTextField);
		textPanel.add(dOBLabel);
		textPanel.add(dOBTextField);
		textPanel.add(customerIDLabel);
		textPanel.add(customerIDTextField);
		textPanel.add(passwordLabel);
		textPanel.add(passwordTextField);

		firstNameTextField.setText(customer.getFirstName());
		surnameTextField.setText(customer.getSurname());
		pPSTextField.setText(customer.getPPS());
		dOBTextField.setText(customer.getDOB());
		customerIDTextField.setText(customer.getCustomerID());
		passwordTextField.setText(customer.getPassword());	
		
		label1 = new JLabel("Edit customer details below. Then save");
	
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		cancelButton = new JButton("Exit");
		cancelButton.addActionListener(this);
		
		cancelPanel.add(cancelButton, BorderLayout.SOUTH);
		cancelPanel.add(saveButton, BorderLayout.SOUTH);
	//	content.removeAll();
		content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(textPanel, BorderLayout.NORTH);
		content.add(cancelPanel, BorderLayout.SOUTH);
		
		f.setContentPane(content);
		f.setSize(340, 350);
		f.setLocation(200, 200);
		f.setVisible(true);
		f.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save")) {
			customer.setFirstName(firstNameTextField.getText());
			customer.setSurname(surnameTextField.getText());
			customer.setPPS(pPSTextField.getText());
			customer.setDOB(dOBTextField.getText());
			customer.setCustomerID(customerIDTextField.getText());
			customer.setPassword(passwordTextField.getText());		
			
			f.dispose();
			
			JOptionPane.showMessageDialog(null, "Changes Saved.");
			AdminMenu.getInstance();

		} else {
			f.dispose();
			AdminMenu.getInstance();
		}

	}

}
