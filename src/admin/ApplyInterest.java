package admin;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	
	JComboBox<String> box;
	JPanel boxPanel;
	JLabel label;
	JButton continueButton, returnButton;
	Container content;

	public ApplyInterest(Customer customer) {
		this.customer = customer;
		main = BankingMain.getInstance();
		if (customer.getAccounts().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"This customer has no accounts! \n The admin must add acounts to this customer.", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			AdminMenu.getInstance();
		} else {
			applyInterestCreated();
		}

	}

	public void applyInterestCreated() {

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
		content = main.getFrame().getContentPane();
		content.setLayout(new GridLayout(2, 1));

		content.add(boxPanel);
		content.add(buttonPanel);
		
		main.getFrame().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Apply Interest")) {
			applyInterest();
		 	
		} else {
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
			String interestString = JOptionPane.showInputDialog(null,
					"Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");
			
			// the isNumeric method tests to see if the string entered was numeric.
			if (BankingMain.isNumeric(interestString)) {

				interest = Double.parseDouble(interestString);
				loop = false;

				acc.setBalance(acc.getBalance() + (acc.getBalance() * (interest / 100)));

				JOptionPane.showMessageDialog(null,
						interest + "% interest applied. \n new balance = " + acc.getBalance() + euro, "Success!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			else {
				JOptionPane.showMessageDialog(null, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}
	 	
	}
}
