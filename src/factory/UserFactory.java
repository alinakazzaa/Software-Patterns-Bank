package factory;

import banking.BankingMain;

public abstract class UserFactory {
	
	protected BankingMain main = BankingMain.getInstance();

	public abstract void getMenu();
	
}
