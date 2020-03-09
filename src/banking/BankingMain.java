package banking;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import classes.Customer;
import menus.StartMenu;

public class BankingMain extends JFrame {
	
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private static BankingMain main;
	JFrame f;
	
	public static BankingMain getInstance() {
		
		if(main == null) {
			main = new BankingMain();
		} 
		return main;
	}
	
	public static void main(String[] args)
	{
		StartMenu driver = new StartMenu();
		driver.menuStart();
	}
	
	public ArrayList<Customer> getCustomers() {
		return this.customerList;
	}
	
	public void addCustomer(Customer customer) {
		this.customerList.add(customer);
	}
	
	public Customer getCustomerByID(String customerID) {
		
		Customer customer = null;
		
		for (Customer aCustomer : this.customerList) {
			if (aCustomer.getCustomerID().equals(customerID)) {
				customer = aCustomer;
			}
		}
		
		return customer;
	}
	
	public static boolean isNumeric(String str)  // a method that tests if a string is numeric
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public void createFrame(String title) {
		
		f = new JFrame(title);
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});
	}
	
	public JFrame getFrame() {
		return this.f;
	}
	
}
