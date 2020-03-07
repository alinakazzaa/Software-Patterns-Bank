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

import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;
import menus.AdminMenu;

public class ApplyInterest implements ActionListener {

	private Customer customer;
	private CustomerAccount acc;
	private BankingMain main = new BankingMain();
	private CustomerAccount account;
	
	JFrame f;
	JComboBox<String> box;
	JPanel boxPanel;
	JLabel label;
	JButton continueButton, returnButton;
	Container content;

	public ApplyInterest(Customer customer) {
		this.customer = customer;
		
		if (customer.getAccounts().isEmpty()) {
			JOptionPane.showMessageDialog(f,
					"This customer has no accounts! \n The admin must add acounts to this customer.", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			new AdminMenu();
		} else {
			applyInterestCreated();
		}

	}

	public void applyInterestCreated() {

		f = new JFrame("Administrator Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		f.setVisible(true);

		box = new JComboBox<String>();
		
		for (int i = 0; i < customer.getAccounts().size(); i++) {
			box.addItem(customer.getAccounts().get(i).getNumber());
		}

		boxPanel = new JPanel();

		label = new JLabel("Select an account to apply interest to:");
		boxPanel.add(label);
		boxPanel.add(box);
		JPanel buttonPanel = new JPanel();
		continueButton = new JButton("Apply Interest");
		continueButton.addActionListener(this);
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		buttonPanel.add(continueButton);
		buttonPanel.add(returnButton);
		content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));

		content.add(boxPanel);
		content.add(buttonPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Apply Interest")) {
			applyInterest();
		 	
		} else {
			f.dispose();		
			AdminMenu.getInstance();
		}

	}
	
	public void applyInterest() { // separated function
		
		String euro = "\u20ac";
	 	double interest = 0;
	 	boolean loop = true;
	 	acc = customer.findAccount(box.getSelectedItem().toString());
	 	
	 	while(loop)
		{
			String interestString = JOptionPane.showInputDialog(f,
					"Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");
			
			// the isNumeric method tests to see if the string entered was numeric.
			if (BankingMain.isNumeric(interestString)) {

				interest = Double.parseDouble(interestString);
				loop = false;

				acc.setBalance(acc.getBalance() + (acc.getBalance() * (interest / 100)));

				JOptionPane.showMessageDialog(f,
						interest + "% interest applied. \n new balance = " + acc.getBalance() + euro, "Success!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			else {
				JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	 	
	}
}
