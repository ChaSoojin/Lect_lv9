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
		System.out.println("1.가입  2.탈퇴  3.로그인  4.로그아웃  0.종료\n100.관리자");
		System.out.print("선택> ");
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
				System.out.println("SHOP 종료");
				return false;
			}
		} catch (Exception e) {}
		return true;
	}

	private void loginMenu() {
		boolean run = true;
		while(run) {
			System.out.printf("---- %s님 Shopping Menu ----\n", uc.getUser(User.log).getName()); //추가!
			System.out.println("1.쇼핑  2.장바구니목록  0.뒤로가기");
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
			System.out.println("1.내 장바구니  2.삭제  3.구입  0.뒤로가기");
			System.out.print("선택> ");
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
			System.out.print("선택> ");
			String select = uc.sc.next();
			
			try {
				int sel = Integer.parseInt(select) - 1;
				
				if(sel >= 0 && sel < ic.getCategorySize()) {
					ArrayList<Item> list = ic.showItemList(sel);
					ic.pickItem(list);
				}
				else if(sel == -1) return;
				
			} catch (Exception e) {
				System.out.println("[실패]잘못 선택하셨습니다.");
			}
		}
	}

	private void managerMenu() {
		if(User.log == -1) {
			boolean run = true;
			while (run) {
				System.out.println("============= 관리자 메뉴 =============");
				System.out.println("1.아이템관리  2.카테고리관리  3.장바구니관리\n4.유저관리  0.뒤로가기");
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
		else System.out.println("[엑세스불가]관리자만 이용 가능한 메뉴입니다.");
	}

	private void itemMenu() {
		boolean run = true;
		while (run) {
			System.out.println("--------------- 아이템 관리 메뉴 -------------");
			System.out.println("1.전체아이템  2.아이템추가  3.아이템삭제  0.뒤로가기");
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
			System.out.println("---------------- 카테고리 관리 메뉴 --------------");
			System.out.println("1.전체카테고리  2.카테고리추가  3.카테고리삭제  0.뒤로가기");
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
			System.out.println("------------ 유저 관리 메뉴 ------------");
			System.out.println("1.전체유저  2.유저추가  3.유제삭제  0.뒤로가기");
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
