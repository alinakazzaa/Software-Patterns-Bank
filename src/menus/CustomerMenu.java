package menus;

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

// split functions where validating into own methods

public class CustomerMenu extends JFrame {

	JFrame f;
	JPanel panel2;
	JButton add;
	Container content;

	private StartMenu start = new StartMenu();
	private BankingMain main = BankingMain.getInstance();
	private Customer customer;
	private CustomerAccount acc;
	
	

	public CustomerMenu() {
		validateCustomer();
	}

	public void validateCustomer() {

		boolean loop = true, loop2 = true;
		boolean cont = false;
		boolean found = false;
		System.out.println(main.getCustomers());
		Customer customer = null;

		while (loop) {
			Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

			if (main.getCustomers().size() > 0) {
				for (Customer aCustomer : main.getCustomers()) {

					if (aCustomer.getCustomerID().equals(customerID)) // search customer list for matching customer ID
					{
						found = true;
						customer = aCustomer;
					}
				}

				if (found == false) {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						loop = true;
					} else if (reply == JOptionPane.NO_OPTION) {
						loop = false;
						loop2 = false;
						start.menuStart();
					}
				} else {
					loop = false;
				}

			} else {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					loop = true;
				} else if (reply == JOptionPane.NO_OPTION) {
					loop = false;
					loop2 = false;
					start.menuStart();
				}
			}

		}

		while (loop2) {
			Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");

			if (!customer.getPassword().equals(customerPassword))// check if custoemr password is correct
			{
				int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

				} else if (reply == JOptionPane.NO_OPTION) {
					loop2 = false;
					start.menuStart();
				}
			} else {
				loop2 = false;
				cont = true;
			}
		}

		if (cont) {
			loop2 = false;
			loop = false;
			customer(customer);
		}
	}
	
	public void customer(Customer e1) {

		f = new JFrame("Customer Menu");
		customer = e1;
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		f.setVisible(true);

		
		if(customer.getAccounts().size() == 0)
		{
			JOptionPane.showMessageDialog(f, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
			f.dispose();				
			start.menuStart();
		}
		else
		{
			createCustomerMenu();
		}
	

//		
//		continueButton.addActionListener(new ActionListener(  ) {
//			public void actionPerformed(ActionEvent ae) {
//				
//		f.dispose();
//		
//		f = new JFrame("Customer Menu");
//		f.setSize(400, 300);
//		f.setLocation(200, 200);
//		f.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent we) { System.exit(0); }
//		});          
//		f.setVisible(true);
//		
//		JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JButton statementButton = new JButton("Display Bank Statement");
//		statementButton.setPreferredSize(new Dimension(250, 20));
//		
//		statementPanel.add(statementButton);
//		
//		JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JButton lodgementButton = new JButton("Lodge money into account");
//		lodgementPanel.add(lodgementButton);
//		lodgementButton.setPreferredSize(new Dimension(250, 20));
//		
//		JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		JButton withdrawButton = new JButton("Withdraw money from account");
//		withdrawalPanel.add(withdrawButton);
//		withdrawButton.setPreferredSize(new Dimension(250, 20));
//		
//		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		JButton returnButton = new JButton("Exit Customer Menu");
//		returnPanel.add(returnButton);
//
//		JLabel label1 = new JLabel("Please select an option");
//		
//		content = f.getContentPane();
//		content.setLayout(new GridLayout(5, 1));
//		content.add(label1);
//		content.add(statementPanel);
//		content.add(lodgementPanel);
//		content.add(withdrawalPanel);
//		content.add(returnPanel);
//		
//		statementButton.addActionListener(new ActionListener(  ) {
//			public void actionPerformed(ActionEvent ae) {
//				f.dispose();
//				f = new JFrame("Customer Menu");
//				f.setSize(400, 600);
//				f.setLocation(200, 200);
//				f.addWindowListener(new WindowAdapter() {
//					public void windowClosing(WindowEvent we) { System.exit(0); }
//				});          
//				f.setVisible(true);
//				
//				JLabel label1 = new JLabel("Summary of account transactions: ");
//				
//				JPanel returnPanel = new JPanel();
//				JButton returnButton = new JButton("Return");
//				returnPanel.add(returnButton);
//				
//				JPanel textPanel = new JPanel();
//				
//				textPanel.setLayout( new BorderLayout() );
//				JTextArea textArea = new JTextArea(40, 20);
//				textArea.setEditable(false);
//				textPanel.add(label1, BorderLayout.NORTH);
//				textPanel.add(textArea, BorderLayout.CENTER);
//				textPanel.add(returnButton, BorderLayout.SOUTH);
//				
//				JScrollPane scrollPane = new JScrollPane(textArea);
//				textPanel.add(scrollPane);
//				
//				for (int i = 0; i < acc.getTransactionList().size(); i ++)
//				{
//					textArea.append(acc.getTransactionList().get(i).toString());
//					
//				}
//				
//				textPanel.add(textArea);
//				content.removeAll();
//				
//				
//				Container content = f.getContentPane();
//				content.setLayout(new GridLayout(1, 1));
//			//	content.add(label1);
//				content.add(textPanel);
//				//content.add(returnPanel);
//				
//				returnButton.addActionListener(new ActionListener(  ) {
//					public void actionPerformed(ActionEvent ae) {
//						f.dispose();			
//					customer(e);				
//					}		
//			     });										
//			}	
//	     });
//		
//		lodgementButton.addActionListener(new ActionListener(  ) {
//			public void actionPerformed(ActionEvent ae) {
//			boolean loop = true;
//			boolean on = true;
//			double balance = 0;
//
//			if(acc instanceof CustomerCurrentAccount)
//			{
//				int count = 3;
//				int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
//				loop = true;
//				
//				while(loop)
//				{
//					if(count == 0)
//					{
//						JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
//						((CustomerCurrentAccount) acc).getAtm().setValid(false);
//						customer(e); 
//						loop = false;
//						on = false;
//					}
//					
//					String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
//					int i = Integer.parseInt(Pin);
//					
//				   if(on)
//				   {
//					if(checkPin == i)
//					{
//						loop = false;
//						JOptionPane.showMessageDialog(f, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);
//						
//					}
//					else
//					{
//						count --;
//						JOptionPane.showMessageDialog(f, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);					
//					}
//				
//				}
//				}
//		
//				
//			}		if(on == true)
//					{
//				String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");//the isNumeric method tests to see if the string entered was numeric. 
//				if(isNumeric(balanceTest))
//				{
//					
//					balance = Double.parseDouble(balanceTest);
//					loop = false;
//					
//					
//				}
//				else
//				{
//					JOptionPane.showMessageDialog(f, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
//				}
//				
//			
//			String euro = "\u20ac";
//			 acc.setBalance(acc.getBalance() + balance);
//			// String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//			 Date date = new Date();
//			 String date2 = date.toString();
//			 String type = "Lodgement";
//				double amount = balance;
//				
//				
//				
//				
//				AccountTransaction transaction = new AccountTransaction(date2, type, amount);
//				acc.getTransactionList().add(transaction);
//				
//			 JOptionPane.showMessageDialog(f, balance + euro + " added do you account!" ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
//			 JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro ,"Lodgement",  JOptionPane.INFORMATION_MESSAGE);
//			}
//
//			}	
//	     });
//		
//		withdrawButton.addActionListener(new ActionListener(  ) {
//			public void actionPerformed(ActionEvent ae) {
//				boolean loop = true;
//				boolean on = true;
//				double withdraw = 0;
//
//				if(acc instanceof CustomerCurrentAccount)
//				{
//					int count = 3;
//					int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
//					loop = true;
//					
//					while(loop)
//					{
//						if(count == 0)
//						{
//							JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);
//							((CustomerCurrentAccount) acc).getAtm().setValid(false);
//							customer(e); 
//							loop = false;
//							on = false;
//						}
//						
//						String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
//						int i = Integer.parseInt(Pin);
//						
//					   if(on)
//					   {
//						if(checkPin == i)
//						{
//							loop = false;
//							JOptionPane.showMessageDialog(f, "Pin entry successful" ,  "Pin", JOptionPane.INFORMATION_MESSAGE);
//							
//						}
//						else
//						{
//							count --;
//							JOptionPane.showMessageDialog(f, "Incorrect pin. " + count + " attempts remaining."  ,"Pin",  JOptionPane.INFORMATION_MESSAGE);		
//						
//						}
//					
//					}
//					}
//		
//				    	
//				    	
//				    
//					
//					
//				}		if(on == true)
//						{
//					String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to withdraw (max 500):");//the isNumeric method tests to see if the string entered was numeric. 
//					if(isNumeric(balanceTest))
//					{
//						
//						withdraw = Double.parseDouble(balanceTest);
//						loop = false;
//						
//						
//						
//					}
//					else
//					{
//						JOptionPane.showMessageDialog(f, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
//					}
//					if(withdraw > 500)
//					{
//						JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
//						withdraw = 0;
//					}
//					if(withdraw > acc.getBalance())
//					{
//						JOptionPane.showMessageDialog(f, "Insufficient funds." ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
//						withdraw = 0;					
//					}
//				
//				String euro = "\u20ac";
//				 acc.setBalance(acc.getBalance()-withdraw);
//				   //recording transaction:
//			//		String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//				 Date date = new Date();
//				 String date2 = date.toString();
//				 
//				 String type = "Withdraw";
//					double amount = withdraw;
//					
//		
//					AccountTransaction transaction = new AccountTransaction(date2, type, amount);
//					acc.getTransactionList().add(transaction);
//				 
//				 
//					
//				 JOptionPane.showMessageDialog(f, withdraw + euro + " withdrawn." ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
//				 JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro ,"Withdraw",  JOptionPane.INFORMATION_MESSAGE);
//				}
//				 
//					
//					
//			}	
//	     });
//		
//		returnButton.addActionListener(new ActionListener(  ) {
//			public void actionPerformed(ActionEvent ae) {
//				f.dispose();		
//				menuStart();				
//			}
//	     });		}		
//	     });
//	}
	}
	
	public void createCustomerMenu() {
		JPanel buttonPanel = new JPanel();
		JPanel boxPanel = new JPanel();
		JPanel labelPanel = new JPanel();
		
		JLabel label = new JLabel("Select Account:");
		labelPanel.add(label);
		
		JButton returnButton = new JButton("Return");
		buttonPanel.add(returnButton);
		JButton continueButton = new JButton("Continue");
		buttonPanel.add(continueButton);
		
		JComboBox<String> box = new JComboBox<String>();
	    for (int i =0; i < customer.getAccounts().size(); i++)
	    {
	     box.addItem(customer.getAccounts().get(i).getNumber());
	    }
		
	    for(int i = 0; i < customer.getAccounts().size(); i++)
	    {
	    	if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() )
	    	{
	    		acc = customer.getAccounts().get(i);
	    	}
	    }
	    
		boxPanel.add(box);
		content = f.getContentPane();
		content.setLayout(new GridLayout(3, 1));
		content.add(labelPanel);
		content.add(boxPanel);
		content.add(buttonPanel);
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
			f.dispose();				
			start.menuStart();				
			}		
	     });
	}


}
