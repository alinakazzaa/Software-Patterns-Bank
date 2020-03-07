package actions;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classes.Customer;
import classes.CustomerAccount;
import menus.AdminMenu;

public class DeleteAccount extends JFrame implements ActionListener {

	private Customer customer;
	private CustomerAccount account;
	
	JFrame f;
	JComboBox<String> box;
	JPanel boxPanel;
	JLabel label;
	JButton continueButton, returnButton;
	Container content;
	
	public DeleteAccount(Customer customer) {
		this.customer = customer;
		deleteAccountCreated();
	}
	
	public void deleteAccountCreated() {
		
		f = new JFrame("Administrator Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		box = new JComboBox<String>();
		
		for (int i = 0; i < customer.getAccounts().size(); i++) {
			box.addItem(customer.getAccounts().get(i).getNumber());
		}

		boxPanel = new JPanel();

		label = new JLabel("Select the account to delete:");
		boxPanel.add(label);
		boxPanel.add(box);
		JPanel buttonPanel = new JPanel();
		continueButton = new JButton("Delete");
		continueButton.addActionListener(this);
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		
		buttonPanel.add(continueButton);
		buttonPanel.add(returnButton);
		
		content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));

		content.add(boxPanel);
		content.add(buttonPanel);
		
		f.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		account = customer.findAccount(box.getSelectedItem().toString());
		
		if(e.getActionCommand().equals("Delete")) {
			customer.getAccounts().remove(account);
			box.removeItem(account.getNumber());
			JOptionPane.showMessageDialog(f, "Successfully deleted account!", "Success!",JOptionPane.INFORMATION_MESSAGE);
		} else {
			f.dispose();
			AdminMenu.getInstance();
		}
		
	}

}
