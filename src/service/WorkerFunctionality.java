package service;

import dao.WorkerFunctionalityDao;

public class WorkerFunctionality implements WorkerFunctionalityInterface{

	@Override
	public Long calculateAmount(String Id) {
		
		//worker earned amount calculation part
		WorkerFunctionalityDao functionalityDaoObj=new WorkerFunctionalityDao();
		
		Long amount=functionalityDaoObj.calculateAmountDao(Id);
		System.out.println("amount earned:-"+"₹"+amount);
		return null;
	}

}
