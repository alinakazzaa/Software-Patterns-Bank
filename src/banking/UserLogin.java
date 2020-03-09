package banking;

import classes.Customer;
import dialog.DialogFrame;
import dialog.InputDialog;

public class UserLogin {
	
	private boolean isValid = false;
	private DialogFrame dialog;
	private Customer customer = null;
	
	public UserLogin() {
		
	}

	public boolean validCustomer() {
		
		dialog = new InputDialog(null, "Enter Customer ID:");
		String customerID;
		
		if(((InputDialog) dialog).getInput() != null && !((InputDialog) dialog).getInput().equals("")) {
			
			customerID = (String) ((InputDialog) dialog).getInput();
			customer = BankingMain.getInstance().getCustomerByID(customerID);
			
			if(customer != null) {
				isValid = true;
			}
		}
		
		return isValid;
		
	}
	
	public boolean validAdmin() {
		String username;
		
		dialog = new InputDialog(null, "Enter Administrator Username:");
		
		if(((InputDialog) dialog).getInput() != null && !((InputDialog) dialog).getInput().equals("")) {
			
			username = (String) ((InputDialog) dialog).getInput();
			
			if (username.equals("admin")) 
				isValid = true;
		}
		
		return isValid;
	}
	
	public boolean validPassword(String type) {
		String password;
		
		dialog = new InputDialog(null, "Enter Password;");
		
		
		if(((InputDialog) dialog).getInput() != null && !((InputDialog) dialog).getInput().equals("")) {
			
			password = (String) ((InputDialog) dialog).getInput();
			
			if(type.equals("Customer")) {
				
				if(customer.getPassword().equals(password))
					isValid = true;
			} else {
				if(password.equals("admin11")) 
					isValid = true;
			}
			
		}
		
		return isValid;
		
	}
}
