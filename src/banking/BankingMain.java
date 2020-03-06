package banking;

import java.util.ArrayList;

import classes.Customer;
import menus.StartMenu;

public class BankingMain {
	
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
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

}
