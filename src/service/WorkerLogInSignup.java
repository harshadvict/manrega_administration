package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import dao.ManagerFunctionalityDao;
import dao.WorkerLogInSignupDao;
import model.manregaProjectWork;
import model.manregaWorkerDetails;
import model.workerSkill;

public class WorkerLogInSignup implements WorkerLogInSignupInterface {

	@Override
	public void workerLogin() {
		
		Scanner sc=new Scanner(System.in);
		//worker log in part
		System.out.println("please enter Id:->");
		String id=sc.nextLine();
		System.out.println("please enter password");
		String pass=sc.nextLine();
		
		//creating object of the class manregaWorkerDetails
		manregaWorkerDetails workerObj=new manregaWorkerDetails(null, null, null, null, null, null);
		
		workerObj.setWorker_id(id);
		workerObj.setWorker_password(pass);
		
		//creating object for class WorkerLogInSignupDao for worker sign in
		WorkerLogInSignupDao workerLogInObj=new WorkerLogInSignupDao();
		
		boolean loginStatus=workerLogInObj.WorkerSignIn(workerObj);
		
		if(loginStatus==true) {
			System.out.println("------------------------------------------------------------------------------------------");
			System.out.println("Login Successfull");
			System.out.println("------------------------------------------------------------------------------------------");
			
			boolean loop=true;
			do {
				
				System.out.println("1.your total amout earned");
				System.out.println("2.change work");
				System.out.println("3.Leave Work");
				
				WorkerFunctionality functionalityObj=new WorkerFunctionality();
				int choice=sc.nextInt();
				
				switch (choice){
					case 1:
						//total amount earned part/function call
						
						functionalityObj.calculateAmount(id);
						break;
					case 2:
						//change work (code-part)
						break;
					case 3:
						//leave work (code-part)
						break;
					default:
						//to break loop
						break;
				}
			}while(loop==true);
		
		}
		else {
			System.out.println("------------------------------------------------------------------------------------------");
			System.out.println("log in failed");
			System.out.println("------------------------------------------------------------------------------------------");

		}
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
		
		//object for work class
		manregaProjectWork workObj=new manregaProjectWork(0,null, null, 0, null, 0, null, null, null);
		
		//creating array list of type workerSkill
		ArrayList<workerSkill> list=new ArrayList<>();

		
		try {
			System.out.println("enter id");
			String id=bfr.readLine();
			System.out.println("enter worker name");
			String name=bfr.readLine();
			System.out.println("enter password");
			String pass1=bfr.readLine();
			System.out.println("choose skill you have");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("Id\t\tSkill");
			
			//calling method of ManagerFunctionalityDao class to show all skill
			list=functionalityDaoObj.showSkill();
			
			for(workerSkill obj:list) {
				System.out.print(obj.getSkill_id());
				System.out.print("\t\t");
				System.out.println(obj.getSkill_name());

			}
			
			System.out.println("----------------------------------------------------------------------------------");
			
			System.out.println("enter the skill id");
			Long skill_id=Long.parseLong(bfr.readLine());
			
			skillObj.setSkill_id(skill_id);
			
			System.out.print("work suitable to your skill (Please choose)");
			functionalityDaoObj.showWork(skill_id);
			
			Long work_id=Long.parseLong(bfr.readLine());
			workObj.setWork_id(work_id);
			
			//creating object for work
			manregaWorkerDetails workerObj=new manregaWorkerDetails(id, name, pass1, skillObj, workObj, null);
			
			WorkerLogInSignupDao workerDaoObj=new WorkerLogInSignupDao();
			workerDaoObj.LoadingWorkerData(workerObj);
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
