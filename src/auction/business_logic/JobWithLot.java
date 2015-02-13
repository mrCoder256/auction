package auction.business_logic;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import auction.integration.dao.DAOFactory;
import auction.integration.dao.impl.LotDAO;
import auction.integration.domain.Lot;

public class JobWithLot implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
        
        System.out.println("start of executing");
        
		int lotId = (Integer) context.getJobDetail().getJobDataMap().get("lot");
		LotDAO DAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getLotDAO();		
		Lot lot = (Lot) DAO.find(lotId);
		
		System.out.println("lotId="+lot.getId());
		System.out.println("lot bids: "+lot.getBids());
		
		if (lot.getBids().size() == 0)
			lot.setState("NOT_SOLD");
		else lot.setState("SOLD");
		System.out.println(DAO.update(lot));
	}

}
