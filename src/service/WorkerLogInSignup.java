package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dao.ManagerFunctionalityDao;
import model.workerSkill;

public class WorkerLogInSignup implements WorkerLogInSignupInterface {

	@Override
	public void workerLogin() {
		//worker log in part
		
	}

	@Override
	public void workerSignUp() {
		//worker sign up part
		System.out.println("please enter all the details to sign up");
		BufferedReader bfr=new BufferedReader(new InputStreamReader(System.in));
		
		//object for skill class
		
		workerSkill skillObj=new workerSkill(0, null);
		
		//creating object for ManagerFunctionalityDao class
		ManagerFunctionalityDao functionalityDaoObj=new ManagerFunctionalityDao();
		
		//creating array list of type workerSkill
		ArrayList<workerSkill> list=new ArrayList<>();

		
		try {
			System.out.println("enter id");
			Long id=Long.parseLong(bfr.readLine());
			System.out.println("enter worker name");
			String name=bfr.readLine();
			System.out.println("enter password");
			String pass1=bfr.readLine();
			System.out.println("choose skill you have");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.print("Id\t\tSkill");
			
			//calling method of ManagerFunctionalityDao class to show all skill
			list=functionalityDaoObj.showSkill();
			
			for(workerSkill obj:list) {
				System.out.print(obj.getSkill_id());
				System.out.print("\t\t");
				System.out.print(obj.getSkill_name());

			}
			
			System.out.println("----------------------------------------------------------------------------------");
			Long skill_id=Long.parseLong(bfr.readLine());
			
			
			System.out.println("to be added");
			

			

			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
