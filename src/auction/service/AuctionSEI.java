package auction.service;

import java.util.ArrayList;

import javax.jws.WebService;

@WebService(name = "AuctionSEI", targetNamespace = "http://service.auction/")
public interface AuctionSEI {

	public ArrayList<LotFromService> getLots();

	public ArrayList<BidFromService> getBidsOnLot(int lotId);

	public LotFromService getLotInfo(int lotId);

	public boolean newBid(BidFromService arg);

	public boolean newLot(LotFromService arg);

	public boolean cencelTheLot(int lotId);

	public int registration(UserFrowService arg);

	public UserFrowService authentication(UserFrowService arg);

}