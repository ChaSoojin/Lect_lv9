import java.util.ArrayList;

import controller.FileController;
import controller.ItemController;
import controller.UserController;
import models.Item;
import models.User;

public class Shop {
	private UserController uc = UserController.instance;
	private ItemController ic = ItemController.instance;
	private FileController fc = FileController.instance;
	
	public void run() {
		fc.load();
		boolean run = true;
		while (run) {
			printAllData();
			run = mainMenu();
		}
	}

	public boolean mainMenu() {
		System.out.println("============ Soojin SHOP =============");
		System.out.println("1.����  2.Ż��  3.�α���  4.�α׾ƿ�  0.����\n100.������");
		System.out.print("����> ");
		String select = uc.sc.next();

		try {
			int sel = Integer.parseInt(select);

			if (sel == 1) uc.join();
			else if (sel == 2) {
				long code = uc.deleteUser();
				if(code != -1) ic.deleteJangProcess(code);
			}
			else if (sel == 3) {
				if (uc.login()) loginMenu();
			}
			else if (sel == 4) uc.logout();
			else if (sel == 100) managerMenu();
			else if (sel == 0) {
				fc.save();
				System.out.println("SHOP ����");
				return false;
			}
		} catch (Exception e) {}
		return true;
	}

	private void loginMenu() {
		boolean run = true;
		while(run) {
			System.out.printf("---- %s�� Shopping Menu ----\n", uc.getUser(User.log).getName()); //�߰�!
			System.out.println("1.����  2.��ٱ��ϸ��  0.�ڷΰ���");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				
				if(sel == 1) shoppingMenu();
				else if(sel == 2) cartMenu();
				else if(sel == 0) break;
			} catch (Exception e) {}
		}
	}

	private void cartMenu() {
		boolean run = true;
		while (run) {
			System.out.println("1.�� ��ٱ���  2.����  3.����  0.�ڷΰ���");
			System.out.print("����> ");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select);

				if (sel == 1) ic.myJang();
				else if (sel == 2) ic.removeJang();
				else if (sel == 3) ic.buyJang();
				else if (sel == 0) run = false;
				
			} catch (Exception e) {}
		}
	}

	private void shoppingMenu() {
		if(ic.showCategoryList()) {
			System.out.print("����> ");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select) - 1;
				
				if(sel >= 0 && sel < ic.getCategorySize()) {
					ArrayList<Item> list = ic.showItemList(sel);
					ic.pickItem(list);
				}
				else if(sel == -1) return;
				
			} catch (Exception e) {
				System.out.println("[����]�߸� �����ϼ̽��ϴ�.");
			}
		}
	}

	private void managerMenu() {
		if(User.log == -1) {
			boolean run = true;
			while (run) {
				System.out.println("============= ������ �޴� =============");
				System.out.println("1.�����۰���  2.ī�װ�����  3.��ٱ��ϰ���\n4.��������  0.�ڷΰ���");
				String select = uc.sc.next();
				
				try {
					int sel = Integer.parseInt(select);
					
					if (sel == 1) itemMenu();
					else if (sel == 2) categoryMenu();
					else if (sel == 3) jangMenu();
					else if (sel == 4) userMenu();
					else if (sel == 0) run = false;
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}			
		}
		else System.out.println("[�������Ұ�]�����ڸ� �̿� ������ �޴��Դϴ�.");
	}

	private void itemMenu() {
		boolean run = true;
		while (run) {
			System.out.println("--------------- ������ ���� �޴� -------------");
			System.out.println("1.��ü������  2.�������߰�  3.�����ۻ���  0.�ڷΰ���");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				
				if (sel == 1) ic.showItemList();
				else if (sel == 2) ic.addItem(); 
				else if (sel == 3) ic.removeItem();
				else if (sel == 0) run = false;
				
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
	}

	private void categoryMenu() {
		boolean run = true;
		while (run) {
			System.out.println("---------------- ī�װ� ���� �޴� --------------");
			System.out.println("1.��üī�װ�  2.ī�װ��߰�  3.ī�װ�����  0.�ڷΰ���");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				
				if (sel == 1) ic.showCategoryList();
				else if (sel == 2) ic.addCategory();
				else if (sel == 3) ic.removeCategory();
				else if (sel == 0) run = false;
				
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
	}

	private void jangMenu() {
		ic.showJangList();
	}

	private void userMenu() {
		boolean run = true;
		while (run) {
			System.out.println("------------ ���� ���� �޴� ------------");
			System.out.println("1.��ü����  2.�����߰�  3.��������  0.�ڷΰ���");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select);
				
				if (sel == 1) ic.showAllUserData();
				else if (sel == 2) uc.addUser();
				else if (sel == 3) {
					long code = uc.removeUser();
					if(code != -1) ic.deleteJangProcess(code);
				}
				else if (sel == 0) run = false;
				
			}catch(Exception e) {}
		}
	}

	private void printAllData() {
		for(int i = 0; i < uc.getUserSize(); i++) {
			String data = uc.getUser(i).toString();
			System.out.println(data);
		}
	}
}
