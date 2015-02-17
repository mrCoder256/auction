package auction.business_logic;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import auction.integration.dao.DAOFactory;
import auction.integration.dao.impl.LotDAO;
import auction.integration.domain.Lot;
import auction.service.LotFromService;

public class TradesScheduler {

	public static void schedule(int lotId, Date finishTradesTime) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(JobWithLot.class)
            .withIdentity("job"+lotId, "group1").build();  
        job.getJobDataMap().put("lot", lotId);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+lotId, "group1")
        	.startAt(finishTradesTime).build();
        
        SchedulerFactory sf = new StdSchedulerFactory("quartz.properties");
        Scheduler sched = sf.getScheduler();
        sched.scheduleJob(job, trigger);
        
        sched.start();
	}
	
	public static LotFromService cancel(int lotId) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        sched.unscheduleJob(new TriggerKey("trigger"+lotId, "group1"));
        
		LotDAO<?> DAO = (LotDAO<?>) DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL)
				.getLotDAO();
		Lot lot = (Lot) DAO.find(lotId);
		lot.setState("CANCELLED");
		if (!DAO.update(lot))
			return null;
		
		LotFromService changedLot = new LotFromService();
		changedLot.setCode(String.valueOf(lot.getId()));
		changedLot.setState(lot.getState());
		
		return changedLot;
	}
}
