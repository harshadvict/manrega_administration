package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ManagerFunctionalityDao;
import dao.WorkerFunctionalityDao;
import model.manregaProjectWork;
import model.manregaWorkerDetails;
import model.workerSkill;

public class WorkerFunctionality implements WorkerFunctionalityInterface{

	@Override
	public Long calculateAmount(String Id) {
		
		//worker earned amount calculation part
		WorkerFunctionalityDao functionalityDaoObj=new WorkerFunctionalityDao();
		
		Long amount=functionalityDaoObj.calculateAmountDao(Id);
		System.out.println("amount earned:- "+"Rs"+amount);
		return null;
	}

	@Override
	public void changeWork(String WorkerId) {
		// method to change work
		
		ManagerFunctionalityDao skillPrintObj=new ManagerFunctionalityDao();
		
		//creating array list to store the skill
		ArrayList<workerSkill> list=new ArrayList<>();
		
		list=skillPrintObj.showSkill();
		
		System.out.println("-------------------------------------");
		System.out.println("Skill id \t\t Skill Name");
		System.out.println("-------------------------------------");

		for(workerSkill skillObj : list) {
			System.out.print(skillObj.getSkill_id());
			System.out.print("\t\t");
			System.out.println(skillObj.getSkill_name());
		}
		System.out.println("-------------------------------------");
		System.out.println("Please choose new skill ");
		Scanner sc=new Scanner(System.in);
		Long id=sc.nextLong();
		
		System.out.println("work under choosen skill");
		skillPrintObj.showWork(id);
		
		System.out.println("Please choose new work id");
		
		Long workId=sc.nextLong();
		
		//worker earned amount calculation part
		WorkerFunctionalityDao functionalityDaoObj=new WorkerFunctionalityDao();
		
		functionalityDaoObj.changeWork(workId,id,WorkerId);
	}

	@Override
	public void LeaveWork(String Id,String Pass) {
		// Worker work leaving part
		System.out.println("------------------------------------------------------------------------");
		System.out.println("Warning:-after this you will not be part of the work");
		System.out.println("------------------------------------------------------------------------");
		System.out.print("please confirm your Id:->");
		Scanner sc=new Scanner(System.in);
		String WorkerId=sc.nextLine();
		System.out.println();
		System.out.print("please confirm your Pass:->");
		String workerPass=sc.nextLine();
		
		if(Id.equals(WorkerId) && Pass.equals(workerPass)) {
			
			//object for the WorkerFunctionalityDao class for calling leaveWork method 
			WorkerFunctionalityDao obj=new WorkerFunctionalityDao();
			
			obj.LeaveWorkDao(WorkerId);
			return;
		}
		else {
			System.out.println("Wrong id or password");
			return;
		}
		
	}

	@Override
	public void showWork(String workerId) {
		// show worker work
		WorkerFunctionalityDao showObj=new WorkerFunctionalityDao();
		
		manregaProjectWork workDetailObj=showObj.showWork(workerId);
		
		//to print all the value of the worker work part
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println(" Your Work Detail");
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Id\t\tWork Name\t\tSkill\t\tWork Duration\t\tPay\t\tManager Name\t\tLocation Name");
		
		System.out.print(workDetailObj.getWork_id());
		System.out.print("\t\t");
		System.out.print(workDetailObj.getWork_name());
		System.out.print("\t\t");
		System.out.print(workDetailObj.getSkill().getSkill_name());
		System.out.print("\t\t");
		System.out.print(workDetailObj.getWork_duartion());
		System.out.print("\t\t");
		System.out.print(workDetailObj.getPay());
		System.out.print("\t\t");
		System.out.print(workDetailObj.getManager_name());
		System.out.print("\t\t");
		System.out.print(workDetailObj.getLocation().getLocation_name());
		System.out.print("\t\t");
		
		return;
		
	}

}