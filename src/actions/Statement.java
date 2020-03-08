package actions;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classes.Customer;
import classes.CustomerAccount;
import menus.CustomerMenu;

public class Statement extends JFrame implements ActionListener {

	private CustomerAccount acc;
	JFrame f;
	JPanel statementPanel, lodgementPanel, withdrawalPanel, returnPanel, textPanel;
	JButton statementButton, lodgementButton, withdrawButton, returnButton;
	JLabel label1;
	Container content;
	JTextArea textArea;
	JScrollPane scrollPane;
	
	public Statement(CustomerAccount acc) {
		this.acc = acc;
		statementCreated();
	}
	
	public void statementCreated() {
		
		f = new JFrame("Customer Menu");
		f.setSize(400, 600);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		f.setVisible(true);
		
		label1 = new JLabel("Summary of account transactions: ");
		
		returnPanel = new JPanel();
		returnButton = new JButton("Return");
		returnPanel.add(returnButton);
		returnPanel.add(label1);
		
		textPanel = new JPanel();
		
		textPanel.setLayout( new BorderLayout() );
		textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
//		textPanel.add(returnButton, BorderLayout.SOUTH);
		
		scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);
		
		for (int i = 0; i < acc.getTransactionList().size(); i ++)
		{
			textArea.append(acc.getTransactionList().get(i).toString());
			
		}
		
		textPanel.add(textArea);
		
		content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(textPanel);
		content.add(returnPanel);
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();		
				CustomerMenu.getInstance();
			}		
	     });			
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
