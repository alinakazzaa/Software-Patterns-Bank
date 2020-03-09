package factory;

import menus.AdminMenu;

public class AdminFactory extends UserFactory {
	
	public AdminFactory() {
		
	}

	
	@Override
	public void getMenu() {
		
		AdminMenu.getInstance();
		
	}
	
}
