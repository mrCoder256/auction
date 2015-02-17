package auction.business_logic;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import auction.gui.broadcaster.BroadcastType;
import auction.gui.broadcaster.Broadcaster;
import auction.integration.dao.DAOFactory;
import auction.integration.dao.impl.LotDAO;
import auction.integration.domain.Lot;
import auction.service.LotFromService;

public class JobWithLot implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		int lotId = (Integer) context.getJobDetail().getJobDataMap().get("lot");
		LotDAO<?> DAO = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL).getLotDAO();		
		Lot lot = (Lot) DAO.find(lotId);
		
		if (lot.getBids().size() == 0)
			lot.setState("NOT_SOLD");
		else lot.setState("SOLD");
		
		LotFromService changedLot = new LotFromService();
		changedLot.setCode(String.valueOf(lot.getId()));
		changedLot.setState(lot.getState());
		
		Broadcaster.broadcast(BroadcastType.CHANGED_LOT_STATE, changedLot);
	}

}
