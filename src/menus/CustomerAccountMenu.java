package menus;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import customer.AccountStatement;
import banking.BankingMain;
import classes.CustomerAccount;
import customer.CustomerAccounts;
import customer.Lodgement;
import customer.Withdrawal;

public class CustomerAccountMenu implements ActionListener {

	private CustomerAccount acc;
	private BankingMain main;
	JPanel statementPanel, lodgementPanel, withdrawalPanel, returnPanel;
	JButton statementButton, lodgementButton, withdrawButton, returnButton;
	JLabel label1;
	Container content;

	public CustomerAccountMenu(CustomerAccount acc) {
		this.acc = acc;
		main = BankingMain.getInstance();
		accountMenuCreated();

	}

	public void accountMenuCreated() {
		
		main.createFrame("Customer Menu");

		statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statementButton = new JButton("Display Bank Statement");
		statementButton.setPreferredSize(new Dimension(250, 20));
		statementButton.addActionListener(this);

		statementPanel.add(statementButton);

		lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lodgementButton = new JButton("Lodge money into account");
		lodgementPanel.add(lodgementButton);
		lodgementButton.setPreferredSize(new Dimension(250, 20));
		lodgementButton.addActionListener(this);

		withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		withdrawButton = new JButton("Withdraw money from account");
		withdrawalPanel.add(withdrawButton);
		withdrawButton.setPreferredSize(new Dimension(250, 20));
		withdrawButton.addActionListener(this);

		returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		returnButton = new JButton("Exit Customer Menu");
		returnButton.addActionListener(this);
		returnPanel.add(returnButton); // was missing, shuold have been added

		label1 = new JLabel("Please select an option");

		content = main.getFrame().getContentPane();
		content.setLayout(new GridLayout(5, 1));
		content.add(label1);
		content.add(statementPanel);
		content.add(lodgementPanel);
		content.add(withdrawalPanel);
		content.add(returnPanel);
		
		main.getFrame().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Display Bank Statement")) {
			new AccountStatement(acc);
		} else if (e.getActionCommand().equals("Lodge money into account")) {
			new Lodgement(acc);
		} else if (e.getActionCommand().equals("Withdraw money from account")) {
			new Withdrawal(acc);
		} else {
			new CustomerAccounts();
		}

	}

}
