package service;

import java.util.Scanner;

import dao.managerLoginDao;
import model.manregaManager;

public class managerLogin implements managerLoginInterface{

	@Override
	public boolean ManagerLogin() {
		
		System.out.println("please enter the id");
		Scanner sc=new Scanner(System.in);
		String id=sc.nextLine();
		
		System.out.println("please enter the pass");
		String pass=sc.nextLine();
		
		//manager model class obj
		manregaManager managerObj=new manregaManager(null,null,null);
		
		managerObj.setManager_id(id);
		managerObj.setManager_password(pass);
		
		//creating object for managerLoginDao
		managerLoginDao loginObj=new managerLoginDao();
		
		//calling method of the managerLoginDao
		boolean present =loginObj.managerLogin(managerObj);
		return present;
	}

}
