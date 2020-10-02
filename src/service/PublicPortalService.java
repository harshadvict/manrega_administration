package service;

import java.util.Scanner;

import dao.PublicPortalServiceDao;

public class PublicPortalService implements PublicPortalServiceInterface{

	@Override
	public void ProjectDetailForPublic() {
		//method to run the functionality for the public
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("Welcome Please choose option to see the detail of the ongoing Project work");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("1.What work is going where?");
		Scanner sc=new Scanner(System.in);
		
		int choice=sc.nextInt();
		
		switch (choice) {
		case 1:
			OngoingProject();
			break;
		default:
			break; 
		}
		
	}

	@Override
	public void OngoingProject() {
		//to display all work 
		PublicPortalServiceDao DaoObj=new PublicPortalServiceDao();
		DaoObj.ongoingProjectDao();
		return;
	}
	

}
