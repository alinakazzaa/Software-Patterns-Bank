package banking;

import java.util.ArrayList;

import classes.Customer;
import menus.StartMenu;

public class BankingMain {
	
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private String adminState, customerState;
	private static BankingMain main;
	
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
	
	// state pattern
	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}
	
	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}
	
	public String getCustomerState() {
		return this.customerState;
	}
	
	public String getAdminState() {
		return this.adminState;
	}

}
