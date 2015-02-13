package auction.integration.dao.impl;

import java.util.Collection;

import auction.integration.dao.DAOFactory;
import auction.integration.domain.Lot;
import auction.integration.domain.User;

public class Test {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {		
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL); //DAOFactory.getInstance();
		BidDAO<?> DAO = (BidDAO<?>) factory.getBidDAO();
		LotDAO<?> DAO2 = (LotDAO<?>) factory.getLotDAO();
		UserDAO<?> DAO1 = (UserDAO<?>) factory.getUserDAO();

		Collection<Lot> arr = (Collection<Lot>) DAO2.select();
		for (Lot lot : arr)
			System.out.println(lot.getLotName());
		
//		User el = new User();
//		el.setLogin("sniper");
//		el.setPassword("1234rewq");
//		el.setFname("Alex");
//		el.setLname("Sniper");
//		DAO1.insert(el);
//		
//		User el1 = DAO1.findByLogin("sniper");
//		el1.setFname("qwe");
//		DAO1.update(el1);
//		DAO1.delete(DAO1.findByLogin("sniper"));
		
		
//		DAO.select();
//		Lot clch1 = (Lot) DAO2.find(2);
//		Bid clch = (Bid) DAO.find(2);
//		User clch2 = (User) DAO1.findByLogin("asda777");
//		clch.setFinishDate(new Timestamp(System.currentTimeMillis()));
//		DAO2.update(clch);
//		DAO.delete(DAO.find(1));


//		DateFormat dff = new SimpleDateFormat("HH:mm:SS");
//		List<Bid> col = ((Lot) DAO2.find(2)).getBids();
//		for (Bid c : col) {
//			System.out.println(c.toString());
////			System.out.println(dff.format(c.getFinishDate().getTime()));
//		}
		
		
//		List<Map<String, String>> arr = DAO2.getLots();
//		for (Map<String, String> el : arr) {
//			System.out.println(el.get("code") + " " + el.get("name") + " " + el.get("finish") + " " + el.get("state"));
//		}
//		System.out.println(DAO2.getLotInfo(3));
		
//		Map<String, String> arg = new HashMap<String, String>();
//		arg.put("login", "sky");
//		arg.put("password", "password");
//		arg = DAO2.authentication(arg);
//		System.out.println(arg.toString());
		
//		Lot u = (Lot) DAO2.find(44);
//		System.out.println(u.toString());
//		//****
//		u.setPrice(1001);
//		DAO2.update(u);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		u = (Lot) DAO2.find(44);
//		System.out.println(u.toString());
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
/*
        Date runTime = DateBuilder.evenMinuteDate(new Date());
        System.out.println("runTime="+runTime);
        System.out.println("now=\t"+new Date());
        try {
			TradesScheduler.schedule(5, runTime);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  */
		
//    	try {
//			Thread.sleep(2000);
//			TradesScheduler.cancel(5);
//		} catch (InterruptedException | SchedulerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        	
		
		System.out.println();
		System.out.println("I can do everything!");
	}
}
