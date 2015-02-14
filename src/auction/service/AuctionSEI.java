package auction.service;

import java.util.ArrayList;

import javax.jws.WebService;

@WebService(name = "AuctionSEI", targetNamespace = "http://service.auction/")
public interface AuctionSEI {

	public ArrayList<LotFromService> getLots();

	public ArrayList<BidFromService> getBidsOnLot(int lotId);

	public LotFromService newLot(LotFromService arg);

	public BidFromService newBid(BidFromService arg);

	public LotFromService getLotInfo(int lotId);

	public LotFromService cencelTheLot(int lotId);

	public int registration(UserFrowService arg);

	public UserFrowService authentication(UserFrowService arg);

}