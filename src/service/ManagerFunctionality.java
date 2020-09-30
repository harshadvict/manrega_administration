package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.ManagerFunctionalityDao;
import model.manregaProjectWork;
import model.manregaWorkLocation;
import model.workerSkill;
import utility.ConnectionManager;

public class ManagerFunctionality implements managerFunctionalityInterface {

	public void managerFunctionality() throws ClassNotFoundException, SQLException {
		

		//creating object for connectionManager class
		ConnectionManager con =new ConnectionManager();
		Connection conn=con.getConnection();
		
		//loop break variable
		boolean check=true;
		//manager functionality class
		
		do{
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("1.Create new work");
		System.out.println("2.See all work under (you/any manager)");
		System.out.println("3.Add new skill");
		System.out.println("4.Add Location");
		System.out.println("5.Delete particular work");
		System.out.println("6.Delete particular location");
		System.out.println("7.Delete particular skill");
		System.out.println("8.Show all worker under you");
		System.out.println("9.Show all worker under particular work");
		System.out.println("any key to exit");
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		Scanner sc=new Scanner(System.in);
		int choice =sc.nextInt();
		
		
		//using switch case to select multiple functionality on the choice.
		switch(choice) {
		case 1:
			newWork(conn);
			break;
		case 2:
			seeAllWork(conn);
			break;
		case 3:
			addSkill(conn);
			break;
		case 4:
			addLocation(conn);
			break;
		case 5:
			deleteWork(conn);
			break;
		case 6:
			deleteLocation(conn);
			break;
		case 7:
			deleteSkill(conn);
			break;
		case 8:
			allWorker(conn);
			break;
		case 9:
			WorkerUnderParticularWork(conn);
			break;
		default:
			check=false;
			break;
		}
	}while(check==true);
		
}
	

	@Override
	public void newWork(Connection conn) {
		//function to add new work
		
		//object of manager class
		//manregaProjectWork workObj=new manregaProjectWork(0, null, null, 0, null, 0, null, null);
		
		//object of the skill class
		workerSkill skillObj=new workerSkill(0, null);
		
		//object of the location class
		manregaWorkLocation locationObj=new manregaWorkLocation(0, null);
		
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("please enter data:->");
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		//creating buffered reader to read 
		BufferedReader bfr=new BufferedReader(new InputStreamReader(System.in));
		
		
		try {
			
			//reading value from the console
			
			System.out.print("Work Id:->");
			long id=Long.parseLong(bfr.readLine());
			System.out.print("enter skill required Id:->");
			long skill_id=Long.parseLong(bfr.readLine());
			System.out.print("enter Location Id:->");
			long Location_id=Long.parseLong(bfr.readLine());
			System.out.print("enter worker no:->");
			int worker_no=Integer.parseInt(bfr.readLine());
			System.out.print("enter work duration:->");
			String duration=bfr.readLine();
			System.out.print("enter payable amount:->");
			long pay=Long.parseLong(bfr.readLine());
			System.out.println("enter manager id:->");
			String manager_id=bfr.readLine();
			System.out.println("enter name of work:->");
			String work_name=bfr.readLine();
			
			//setting data in the workerSkill class using object to pass to the manregaProjectWork class constructor.
			skillObj.setSkill_id(skill_id);
			
			//setting data in the manregaWorkLocation class using object to pass to the manregaProjectWork class constructor.
			locationObj.setLocation_id(Location_id);
			
			//calling the constructor of the manregaProjectWork class
			manregaProjectWork workObj=new manregaProjectWork(id, skillObj, locationObj, worker_no, duration, pay, null, manager_id,work_name);
			
			//creating object for the ManagerFunctionalityDao class to load work data in database
			ManagerFunctionalityDao functionalityDaoObj=new ManagerFunctionalityDao();
			
			//calling method using class object
			functionalityDaoObj.newWorkDao(workObj,conn);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void seeAllWork(Connection conn) {
		// function to see all work
		System.out.println("enter manager id to view:-> ");
		Scanner sc =new Scanner(System.in);
		String id=sc.nextLine();
		
		//Arraylist for the manregaProjectWork Class to load data from the called method allWorkView.
		ArrayList<manregaProjectWork> list=new ArrayList<>();

		//object for ManagerFunctionalityDao class
		ManagerFunctionalityDao functionalityDaoObj=new ManagerFunctionalityDao();
		
		//calling function of ManagerFunctionalityDao class
		
		list=functionalityDaoObj.allWorkView(id, conn);
		
		System.out.println("Work Id\t\tLocation name\t\tSkill Required\t\tRequired worker number\t\t Work Duration\t\tPay Per Day\t\tUnder Manager ");
		for(manregaProjectWork obj :list) {
			System.out.print(obj.getWork_id());
			System.out.print("\t\t");
			System.out.print(obj.getLocation().getLocation_name());
			System.out.print("\t\t");
			System.out.print(obj.getSkill().getSkill_name());
			System.out.print("\t\t");
			System.out.print(obj.getWorker_no());
			System.out.print("\t\t");
			System.out.print(obj.getWork_duartion());
			System.out.print("\t\t");
			System.out.print(obj.getPay());
			System.out.print("\t\t");
			System.out.println(obj.getManager_name());
			System.out.println("=============================================================================================");
		}
	}

	@Override
	public void addSkill(Connection conn) {
		// function to add skill
		System.out.println("Please enter new skill id");
		Scanner sc=new Scanner(System.in);
		Long skill_id=sc.nextLong();
		sc.nextLine();
		System.out.println("please enter new skill name");
		String Skill_name=sc.nextLine();
		workerSkill skillObj=new workerSkill(skill_id, Skill_name);
		
		ManagerFunctionalityDao functionalityDaoObj=new ManagerFunctionalityDao();
		functionalityDaoObj.addSkill(skillObj, conn);
	}

	@Override
	public void addLocation(Connection conn) {
		// function to add location
		System.out.println("Please enter new location id");
		Scanner sc=new Scanner(System.in);
		Long location_id=sc.nextLong();
		sc.nextLine();
		System.out.println("please enter new skill name");
		String location_name=sc.nextLine();
		manregaWorkLocation locationObj=new manregaWorkLocation(location_id, location_name);
		
		ManagerFunctionalityDao functionalityDaoObj=new ManagerFunctionalityDao();
		functionalityDaoObj.addLocation(locationObj, conn);
	}

	@Override
	public void deleteWork(Connection conn) {
		// function to delete particular work
		System.out.println("enter the  work id need to be deleted");
		Scanner sc=new Scanner(System.in);
		Long id=sc.nextLong();
		
		//object for ManagerFunctionalityDao class
		ManagerFunctionalityDao functionalityDaoObj=new ManagerFunctionalityDao();
		
		//using ManagerFunctionalityDao object to call the function of the class ManagerFunctionalityDao
		functionalityDaoObj.deleteWork(conn, id);

	}

	@Override
	public void deleteLocation(Connection conn) {
		//function to particular location
		System.out.println("enter the  Location id need to be deleted");
		Scanner sc=new Scanner(System.in);
		Long id=sc.nextLong();
		
		//object for ManagerFunctionalityDao class
		ManagerFunctionalityDao functionalityDaoObj=new ManagerFunctionalityDao();
		
		//using ManagerFunctionalityDao object to call the function of the class ManagerFunctionalityDao
		functionalityDaoObj.deleteLocation(conn, id);
	}

	@Override
	public void deleteSkill(Connection conn) {
		//function to delete particular skill
		System.out.println("enter the  skill id need to be deleted");
		Scanner sc=new Scanner(System.in);
		Long id=sc.nextLong();
		
		//object for ManagerFunctionalityDao class
		ManagerFunctionalityDao functionalityDaoObj=new ManagerFunctionalityDao();
		
		//using ManagerFunctionalityDao object to call the function of the class ManagerFunctionalityDao
		functionalityDaoObj.deleteSkill(conn, id);
	}

	@Override
	public void allWorker(Connection conn) {
		//function to see all worker
		//to be added
	}

	@Override
	public void WorkerUnderParticularWork(Connection conn) {
		//function to see worker under particular work
		//to be added
	}
}
