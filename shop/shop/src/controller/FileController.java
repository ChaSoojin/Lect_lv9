package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import models.Cart;
import models.Item;
import models.User;

public class FileController {
	public static FileController instance = new FileController();
	private UserController uc = UserController.instance;
	private ItemController ic = ItemController.instance;
	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	private String fileName1 = "user.txt";
	private String fileName2 = "item.txt";
	private String fileName3 = "category.txt";
	private String fileName4 = "jang.txt";
	private File file = null;

	public void save() {
		try {
			fw = new FileWriter(fileName1);
			fw.write(makeUserData());
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			fw = new FileWriter(fileName2);
			fw.write(makeItemData());
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			fw = new FileWriter(fileName3);
			fw.write(makeCategoryData());
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			fw = new FileWriter(fileName4);
			fw.write(makeJangData());
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String makeUserData() {
		String data = "";
		for (User user : uc.getUserList()) {
			data += user.getCode() + "/";
			data += user.getId() + "/";
			data += user.getPw() + "/";
			data += user.getName() + "\n";
		}
		data = data.substring(0, data.length() - 1);

		return data;
	}

	private String makeItemData() {
		String data = "";
		for (Item item : ic.getItemList()) {
			data += item.getItemName() + "/";
			data += item.getPrice() + "/";
			data += item.getCategory() + "\n";
		}
		data = data.substring(0, data.length() - 1);

		return data;
	}

	private String makeCategoryData() {
		String data = "";
		for (String category : ic.getCategoryList()) {
			data += category + "\n";
		}
		data = data.substring(0, data.length() - 1);
		
		return data;
	}

	private String makeJangData() {
		String data = "";
		for (Cart cart : ic.getJangList()) {
			data += cart.getUserCode() + "/";
			data += cart.getItemName() + "\n";
		}
		data = data.substring(0, data.length() - 1);
		
		return data;
	}
	
	
	public void load() {
		uc.getUserList().clear();
		ic.clearCategory();
		ic.clearItem();
		ic.clearJang();
		
		String data = loadContent(fileName1);
		if(!data.equals("")) userDataSetting(data);
		
		data = loadContent(fileName2);
		if(!data.equals("")) itemDataSetting(data);
		
		data = loadContent(fileName3);
		if(!data.equals("")) categoryDataSetting(data);

		data = loadContent(fileName4);
		if(!data.equals("")) jangDataSetting(data);
	}

	private String loadContent(String fileName) {
		file = new File(fileName);
		String data = "";
		
		if(file.exists()) {
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				String line;
				
				while((line = br.readLine()) != null) {
					data += line + "\n";
				}
				data = data.substring(0, data.length()-1);
				
			} catch (Exception e) {
				// TODO: handle exception
			}					
		}
		return data;
	}

	private void userDataSetting(String data) {
		String[] info = data.split("\n");
		for(int i = 0; i < info.length; i++) {
			String[] info2 = info[i].split("/");
			
			uc.userSetting(new User(Integer.parseInt(info2[0]), info2[1], info2[2], info2[3]));
		}
	}
	
	private void itemDataSetting(String data) {
		String[] info = data.split("\n");
		for(int i = 0; i < info.length; i++) {
			String[] info2 = info[i].split("/");
		
			ic.itemSetting(new Item(info2[0], Integer.parseInt(info2[1]), info2[2]));
		}
	}

	private void categoryDataSetting(String data) {
		String[] info = data.split("\n");
		for(int i = 0; i < info.length; i++) {
			ic.categorySetting(info[i]);
		}
	}

	private void jangDataSetting(String data) {
		String[] info = data.split("\n");
		for(int i = 0; i < info.length; i++) {
			String[] info2 = info[i].split("/");
			
			ic.jangSetting(new Cart(Integer.parseInt(info2[0]), info2[1]));
		}
		
		for(int i = 0; i < uc.getUserSize(); i++) {
			for(int j = 0; j < ic.getJangList().size(); j++) {
				if(uc.getUser(i).getCode() == ic.getJangList().get(j).getUserCode()) {
					ic.userJangSetting(i, j);
				}
			}
		}
	}
}
