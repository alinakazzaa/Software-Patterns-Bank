package actions;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.CustomerAccount;
import menus.CustomerMenu;
import menus.StartMenu;

public class AccountScreen extends JFrame implements ActionListener {
	
	private CustomerAccount acc;
	JFrame f;
	JPanel statementPanel, lodgementPanel, withdrawalPanel, returnPanel;
	JButton statementButton, lodgementButton, withdrawButton, returnButton;
	JLabel label1;
	Container content;
	
	
	public AccountScreen(CustomerAccount acc) {
		this.acc = acc;
		accountScreenCreated();
		
	}
	
	public void accountScreenCreated() {
		f = new JFrame("Customer Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		f.setVisible(true);
		
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

		label1 = new JLabel("Please select an option");
		
		content = f.getContentPane();
		content.setLayout(new GridLayout(5, 1));
		content.add(label1);
		content.add(statementPanel);
		content.add(lodgementPanel);
		content.add(withdrawalPanel);
		content.add(returnPanel);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Display Bank Statement")) {
			new Statement(acc);
		} else if(e.getActionCommand().equals("Lodge money into account")) {
			new AccountLodgement(acc);
		} else if (e.getActionCommand().equals("Withdraw money from account")) {
			new AccountWithdraw(acc);
		} else {
			f.dispose();
			CustomerMenu.getInstance().setCustomer(null);
			new StartMenu();
		}
		
	}
	
	public CustomerAccount getAcc() {
		return acc;
	}

	public void setAcc(CustomerAccount acc) {
		this.acc = acc;
	}

}
