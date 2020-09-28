package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import dao.managerSignupDao;
import model.manregaManager;
import utility.passwordValidation;

public class managerSignup implements managerSignupInterface{

	
	//manager signup method definition
	
	@Override
	public void signUp() {
		
		
		//Scanner sc=new Scanner(System.in);
		
		BufferedReader bfr=new BufferedReader(new InputStreamReader(System.in));
		
		//object of manager model class
		manregaManager managerObj=new manregaManager(null, null, null);
		
		//importing password validation class
		passwordValidation validationObj=new passwordValidation();
		
		//object for managerSignupDao
		
		managerSignupDao managerSignupobj=new managerSignupDao();
		
		boolean checkPassValid;
		
		System.out.println("please enter detail to sign up-->");
		
		try {
			System.out.print("Enter Id:->");
			String id = bfr.readLine();
			managerObj.setManager_id(id);
			System.out.println();
			
			
			System.out.print("Enter name:->");
			String name;
			name=bfr.readLine();
			managerObj.setManager_name(name);
			System.out.println();
			
			do {
			System.out.print("password:->");
			String pass1=bfr.readLine();
			System.out.println();
			
			System.out.print("re-enter password:->");
			String pass2=bfr.readLine();
			System.out.println();
			
			//calling password validation class method
			checkPassValid=validationObj.passValid(pass1,pass2);
			
			if(checkPassValid==true) {
				System.out.println("Sign up Successfull");
				managerObj.setManager_password(pass1);
			}
			else {
				System.out.println("password don't match (renter)");
			}
			}while(checkPassValid!=true);
			
			managerSignupobj.managerSignUp(managerObj);
			
			//return i;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return false;
	}

}
