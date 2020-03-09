package factory;

import menus.AdminMenu;
import menus.UserMenu;

public class AdminFactory extends UserFactory {
	
	public AdminFactory() {
		
	}

	
	@Override
	public void getMenu() {
		
		AdminMenu.getInstance();
		
	}
	
}
