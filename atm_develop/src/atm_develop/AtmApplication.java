package atm_develop;

import controller.BankManager;

public class AtmApplication {
	public static void main(String[] args) {
		BankManager.instance.run();
	}
}
