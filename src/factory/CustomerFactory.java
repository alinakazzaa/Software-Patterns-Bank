package factory;

import menus.CustomerMenu;
import menus.NewCustomer;
import menus.UserMenu;

public class CustomerFactory extends UserFactory {

	private boolean isNew;
	
	public CustomerFactory() {
		
	}
	
	@Override
	public void getMenu() {
		
		if(isNew) {
			NewCustomer.getInstance();
		} else {
			CustomerMenu.getInstance();
		}

	}
	
	public boolean getIsNew() {
		return this.isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

}
