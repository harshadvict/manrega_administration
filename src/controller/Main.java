package controller;

import java.sql.SQLException;
import java.util.Scanner;

import service.ManagerFunctionality;
import service.PublicPortalService;
import service.WorkerLogInSignup;
import service.managerLogin;
import service.managerSignup;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//loop check variable
		boolean loop_check=true;
		
		//scanner object
		Scanner sc=new Scanner(System.in);
		
		//while loop to perform function for no of time
		while(loop_check) {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("\t\t\t\tMANREGA ADMINISTRATION SYSTEM");
		System.out.println("-----------------------------------------------------------------------------");
		
		System.out.println("Please choose the option-->");
		System.out.println("1. Manager sign up \n 2.Manager log in \n 3.worker sign up\n 4.worker log in\n 5.public view \n 6.exit");
		System.out.println("-----------------------------------------------------------------------------");
		
		int choice;
		choice=sc.nextInt();
		  switch(choice) {
		  
		  case 1:
			  //manager sign up part
			  
			  managerSignup signupObj=new managerSignup();
			  signupObj.signUp();
			  break;
		  case 2:
			  //manager log in part
			  
			  managerLogin loginObj=new managerLogin();
			  String tempId=loginObj.ManagerLogin();
			  if(tempId.equals("false")) {
				  System.out.println("Alert:-please check Id/password");
				  
			  }
			  else {
				  //manager functionality part calling ManagerFunctionality function to include manager functionality.
				  
				  ManagerFunctionality functionObj=new ManagerFunctionality();
				  
				  //calling method of the ManagerFunctionality class using object

				  functionObj.managerFunctionality(tempId);
			  }
			  break;
		  case 3:
			  //worker sign up part
			  
			  //creating object for WorkerLogInSignup
			  
			  WorkerLogInSignup signUpObj=new WorkerLogInSignup();
			  
			  //calling method of the WorkerLogInSignup class using object
			  signUpObj.workerSignUp();
			  break;
		  case 4:
			  //worker log in part
			  
			  //creating object for WorkerLogInSignup

			  WorkerLogInSignup logInObj=new WorkerLogInSignup();
			  
			  //calling method of the WorkerLogInSignup class using object
			  logInObj.workerLogin();
			  
			  break;
		  case 5:
			  //public view part
			  PublicPortalService publicPortalObj=new PublicPortalService();
			  publicPortalObj.ProjectDetailForPublic();
			  break;
		  default:
			  //exit the database part
			   break;
		  }
		
		
		}
	}

}
