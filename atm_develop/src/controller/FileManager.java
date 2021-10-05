package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import models.Account;
import models.User;

public class FileManager {
	public static FileManager instance = new FileManager();
	private UserManager um = UserManager.instance;
	private AccountManager am = AccountManager.instance;
	private File file = null;
	private FileWriter fw = null;
	private FileReader fr = null;
	private BufferedReader br = null;
	private String fileName1 = "user.txt";
	private String fileName2 = "acc.txt";
	
	public void save() {
		String data1 = makeUserData();
		String data2 = makeAccData();
		
		try {
			fw = new FileWriter(fileName1);
			fw.write(data1);
			fw.close();
		} catch (Exception e) {}

		try {
			fw = new FileWriter(fileName2);
			fw.write(data2);
			fw.close();
			System.out.printf("[저장완료]%s와 %s에 저장되었습니다.\n", fileName1, fileName2);
		} catch (Exception e) {
			System.out.println("[저장실패]");
		}
	}
	
	public void load() {
		um.clearUser();
		am.clearAcc();
	
		String data1 = loadData(fileName1);
		String data2 = loadData(fileName2);
		
//		System.out.println(data1);
//		System.out.println(data2);

		userDataSetting(data1);
		AccDataSetting(data2);
	}
	
	private void AccDataSetting(String data) {
		String[] strInfo = data.split("\n");
		
		for(int i = 0; i < strInfo.length; i++) {
			String[] accInfo = strInfo[i].split("/");

			boolean rep = Boolean.parseBoolean(accInfo[0]);
			int userCode = Integer.parseInt(accInfo[1]);
			int accNum = Integer.parseInt(accInfo[2]);
			int pw = Integer.parseInt(accInfo[3]);
			int money = Integer.parseInt(accInfo[4]);
			
			Account newAcc = new Account(rep, userCode, accNum, pw, money);
			am.addAcc(newAcc);
			
			int idx = -1;
			for(int j = 0; j < um.getUserSize(); j++) {
				if(um.getUser(j).getCode() == userCode) idx = j;
			}
			
			um.addAcc(idx, newAcc);
		}
	}

	private void userDataSetting(String data) {
		String[] strInfo = data.split("\n");
		
		for(int i = 0; i < strInfo.length; i++) {
			String[] userInfo = strInfo[i].split("/");

			int code = Integer.parseInt(userInfo[0]);
			String id = userInfo[1];
			String pw = userInfo[2];
			String name = userInfo[3];
		
			um.addUser(new User(code, id, pw, name));
		}
	}

	private String loadData(String fileName) {
		file = new File(fileName);
		String data = "";

		if(file.exists()) {
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				String line;
				
				while((line = br.readLine()) != null){
					data += line + "\n";
				}
				data = data.substring(0, data.length()-1);
				
				fr.close();
				br.close();			
			} catch (Exception e) {}
		}
		
		return data;
	}
	
	
	private String makeUserData() {
		String data = "";
		for(int i = 0; i < um.getUserSize(); i++) {
			data += um.getUser(i).getCode() + "/";
			data += um.getUser(i).getId() + "/";
			data += um.getUser(i).getPw() + "/";
			data += um.getUser(i).getName() + "\n";
		}
		data = data.substring(0, data.length() - 1);
		
		return data;
	}
	
	private String makeAccData() {
		String data = "";
		for(int i = 0; i < am.getAccsSize(); i++) {
			data += am.getAcc(i).getRep() + "/";
			data += am.getAcc(i).getUserCode() + "/";
			data += am.getAcc(i).getAccNum() + "/";
			data += am.getAcc(i).getPw() + "/";
			data += am.getAcc(i).getMoney() + "\n";
		}
		data = data.substring(0, data.length() - 1);
		
		return data;
	}
}
