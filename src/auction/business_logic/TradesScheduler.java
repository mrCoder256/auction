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

public class TradesScheduler {

	public static void schedule(int lotId, Date finishTradesTime) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory("quartz.properties");
        Scheduler sched = sf.getScheduler();
        
        System.out.println("setting");
        
        JobDetail job = JobBuilder.newJob(JobWithLot.class)
            .withIdentity("job"+lotId, "group1").build();  
        job.getJobDataMap().put("lot", lotId);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+lotId, "group1")
        	.startAt(finishTradesTime).build();
        sched.scheduleJob(job, trigger);
        
        System.out.println("before start");
        System.out.println(finishTradesTime);
        
        sched.start();
	}
	
	public static void cancel(int lotId) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        
        System.out.println("cancelling");
        
        sched.unscheduleJob(new TriggerKey("trigger"+lotId, "group1"));
	}
}
