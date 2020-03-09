package customer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import banking.BankingMain;
import classes.CustomerAccount;
import menus.CustomerMenu;

public class AccountStatement implements ActionListener {

	private CustomerAccount acc;
	private BankingMain main;
	JPanel statementPanel, lodgementPanel, withdrawalPanel, textPanel;
	JButton statementButton, lodgementButton, withdrawButton, returnButton;
	JLabel label1;
	Container content;
	JTextArea textArea;
	JScrollPane scrollPane;
	
	public AccountStatement(CustomerAccount acc) {
		this.acc = acc;
		main = BankingMain.getInstance();
		statementCreated();
	}
	
	public void statementCreated() {
		
		label1 = new JLabel("Summary of account transactions: ");
		
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		
		textPanel = new JPanel();
		
		textPanel.setLayout( new BorderLayout() );
		textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(returnButton, BorderLayout.SOUTH);
		
		scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);
		
		for (int i = 0; i < acc.getTransactionList().size(); i ++)
		{
			textArea.append(acc.getTransactionList().get(i).toString());
			
		}
		
		textPanel.add(textArea);
		
		content = main.getFrame().getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(textPanel);	
		
		main.getFrame().setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CustomerMenu.getInstance();
	}
}

