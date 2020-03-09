package admin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import banking.BankingMain;
import classes.Customer;
import classes.CustomerAccount;
import menus.AdminMenu;

public class AccountSummary implements ActionListener {
	
	private ArrayList<Customer>customerList;
	private BankingMain main;
	private CustomerAccount acc;
	
	JLabel label1;
	JPanel returnPanel;
	JButton returnButton;
	JPanel textPanel;
	JTextArea textArea;
	JScrollPane scrollPane;
	Container content;
	
	public AccountSummary() {
		main = BankingMain.getInstance();
		this.customerList = main.getCustomers();
		summaryCreated();
	}
	
	public void summaryCreated() {
		
		label1 = new JLabel("Summary of all transactions: ");
		returnPanel = new JPanel();
		returnButton = new JButton("Return");
		
		returnButton.addActionListener(this);
		
		returnPanel.add(returnButton);
		
		textPanel = new JPanel();
		
		textPanel.setLayout( new BorderLayout() );
		textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(returnButton, BorderLayout.SOUTH);
		
		scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);
		
		
		for (int a = 0; a < customerList.size(); a++)//For each customer, for each account, it displays each transaction.
			{
				for (int b = 0; b < customerList.get(a).getAccounts().size(); b ++ )
				{
					acc = customerList.get(a).getAccounts().get(b);
					for (int c = 0; c < customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++)
					{
						
						textArea.append(acc.getTransactionList().get(c).toString());
						//Int total = acc.getTransactionList().get(c).getAmount(); //I was going to use this to keep a running total but I couldnt get it  working.
						
					}				
				}				
			}
		
		textPanel.add(textArea);
		content = main.getFrame().getContentPane();
		content.setLayout(new GridLayout(1, 1));
		content.add(label1);
		content.add(textPanel);
		
		main.getFrame().setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AdminMenu.getInstance();
	}
}
