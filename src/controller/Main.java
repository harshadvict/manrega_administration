package controller;

import java.util.Scanner;

import service.managerSignup;

public class Main {

	public static void main(String[] args) {
		
		//loop check variable
		boolean loop_check=true;
		
		//scanner object
		Scanner sc=new Scanner(System.in);
		
		//while loop to perform function for no of time
		while(loop_check) {
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
			  
			  
			  break;
		  case 3:
			  //worker sign in part
			  
			  
			  break;
		  case 4:
			  //worker log in part
			  
			  
			  break;
		  case 5:
			  //public view part
			  
			  
			  break;
		  default:
			  //exit the database part
			  
			  
			  break;
		  }
		
		
		}
	}

}
