package auction.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import org.quartz.SchedulerException;

import auction.business_logic.TradesScheduler;
import auction.gui.AuctionUI;
import auction.integration.dao.DAOFactory;
import auction.integration.dao.impl.LotDAO;
import auction.integration.domain.Bid;
import auction.integration.domain.Lot;
import auction.integration.domain.User;


@WebService(targetNamespace = "http://service.auction/", endpointInterface = "auction.service.AuctionSEI", portName = "AuctionPort", serviceName = "AuctionService")
public class Auction implements AuctionSEI {
	
	private final DAOFactory FACTORY = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL); // ************************************
	
	public ArrayList<LotFromService> getLots(){
		Collection<Lot> list = FACTORY.getLotDAO().select();
		ArrayList<LotFromService> arr = new ArrayList<LotFromService>();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		
		for (Lot lot : list){
			LotFromService el = new LotFromService();
			el.setCode(String.valueOf(lot.getId()));
			el.setName(lot.getLotName());
			el.setFinish(dateFormat.format(lot.getFinishDate()));
			el.setState(lot.getState());
			arr.add(el);
		}
		return arr;
	}

	public ArrayList<BidFromService> getBidsOnLot(int lotId){
		List<Bid> list = ((Lot) FACTORY.getLotDAO().find(lotId)).getBids();
		ArrayList<BidFromService> arr = new ArrayList<BidFromService>();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		
		if (list == null) 
			return null;
		for (Bid bid : list){
			BidFromService el = new BidFromService();
			el.setBid(String.valueOf(bid.getBid()));
			el.setDate(dateFormat.format(bid.getBidsTime().getTime()));
			el.setBidder(bid.getUser().getFname() + " " + bid.getUser().getLname());			
			arr.add(el);
		}
		return arr;
	}
	
	public LotFromService getLotInfo(int lotId){
		Lot lot = (Lot) FACTORY.getLotDAO().find(lotId);
		LotFromService info = new LotFromService();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		
		info.setCode(String.valueOf(lot.getId()));
		info.setName(lot.getLotName());
		info.setState(lot.getState());
		info.setFinish(dateFormat.format(lot.getFinishDate().getTime()));
		info.setOwner(lot.getUser().getFname() + " " + lot.getUser().getLname());
		info.setDescription(lot.getDescription());
		info.setPrice(String.valueOf(lot.getPrice()));
		return info;
	}
	
	public boolean newBid(BidFromService arg){
		Bid bid = new Bid();
		
		bid.setUser((User) FACTORY.getUserDAO().find(Integer.parseInt(arg.getBidder())));
		bid.setLot((Lot) FACTORY.getLotDAO().find(Integer.parseInt(arg.getLot())));
		bid.setBid(Float.parseFloat(arg.getBid()));
		return (FACTORY.getBidDAO().insert(bid) != 0);
	}
	
	public boolean newLot(LotFromService arg){		
		Lot lot = new Lot();
		Date parsedDate = null;
		try {
			parsedDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(arg.getFinish());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lot.setUser((User) FACTORY.getUserDAO().find(Integer.parseInt(arg.getOwner())));
		if (arg.getName().length() > 25) arg.setName(arg.getName().substring(0, 24));
		lot.setLotName(arg.getName());
		lot.setFinishDate(new Timestamp(parsedDate.getTime()));
		lot.setPrice(Float.parseFloat(arg.getPrice()));
		if (arg.getDescription().length() > 50) arg.setDescription(arg.getDescription().substring(0, 49));
		lot.setDescription(arg.getDescription());
		int id = FACTORY.getLotDAO().insert(lot);
		try {
			TradesScheduler.schedule(id, parsedDate);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return (id != 0);
	}

	public boolean cencelTheLot(int lotId){	
		LotDAO DAO = (LotDAO) FACTORY.getLotDAO();
		Lot lot = (Lot) DAO.find(lotId);
		
		lot.setState("CANCELLED");
    	try {
			TradesScheduler.cancel(lotId);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return DAO.update(lot);
	}
	
	public int registration(UserFrowService arg){
		if (arg.getLogin().length() > 20 ||
				arg.getPassword().length() > 20 ||
				arg.getFname().length() > 15 ||
				arg.getLname().length() > 15)
			return 0;
		if (FACTORY.getUserDAO().findByLogin(arg.getLogin()) != null)
			return -1; //current login already exist
		
		User user = new User();		
		user.setLogin(arg.getLogin());
		user.setPassword(arg.getPassword());
		user.setFname(arg.getFname());
		user.setLname(arg.getLname());
		return FACTORY.getUserDAO().insert(user);
	}
	
	public UserFrowService authentication(UserFrowService arg){
		if (arg.getLogin().length() > 20)
			return null;
		User user = FACTORY.getUserDAO().findByLogin(arg.getLogin());
		
		if (!user.getPassword().equals(arg.getPassword())){
			return null;
		}
		arg.setId(String.valueOf(user.getId()));
		arg.setLname(user.getLname());
		arg.setFname(user.getFname());
		return arg;
	}
	
}
