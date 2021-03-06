
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package auction.service.client;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2015-01-07T16:09:27.334+02:00
 * Generated source version: 2.7.11
 * 
 */

@javax.jws.WebService(
                      serviceName = "AuctionService",
                      portName = "AuctionPort",
                      targetNamespace = "http://service.auction/",
                      wsdlLocation = "http://localhost:8080/auction/services/AuctionPort?wsdl",
                      endpointInterface = "auction.service.client.AuctionSEI")
                      
public class AuctionSEIImpl implements AuctionSEI {

    private static final Logger LOG = Logger.getLogger(AuctionSEIImpl.class.getName());

    /* (non-Javadoc)
     * @see auction.service.client.AuctionSEI#cencelTheLot(int  arg0 )*
     */
    public boolean cencelTheLot(int arg0) { 
        LOG.info("Executing operation cencelTheLot");
        System.out.println(arg0);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see auction.service.client.AuctionSEI#newLot(auction.service.client.LotFromService  arg0 )*
     */
    public boolean newLot(auction.service.client.LotFromService arg0) { 
        LOG.info("Executing operation newLot");
        System.out.println(arg0);
        try {
            boolean _return = true;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see auction.service.client.AuctionSEI#getBidsOnLot(int  arg0 )*
     */
    public java.util.List<auction.service.client.BidFromService> getBidsOnLot(int arg0) { 
        LOG.info("Executing operation getBidsOnLot");
        System.out.println(arg0);
        try {
            java.util.List<auction.service.client.BidFromService> _return = new java.util.ArrayList<auction.service.client.BidFromService>();
            auction.service.client.BidFromService _returnVal1 = new auction.service.client.BidFromService();
            _returnVal1.setBid("Bid-961145566");
            _returnVal1.setBidder("Bidder856861707");
            _returnVal1.setDate("Date2017669512");
            _returnVal1.setLot("Lot934099583");
            _return.add(_returnVal1);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see auction.service.client.AuctionSEI#registration(auction.service.client.UserFrowService  arg0 )*
     */
    public int registration(auction.service.client.UserFrowService arg0) { 
        LOG.info("Executing operation registration");
        System.out.println(arg0);
        try {
            int _return = -585692151;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see auction.service.client.AuctionSEI#getLots(*
     */
    public java.util.List<auction.service.client.LotFromService> getLots() { 
        LOG.info("Executing operation getLots");
        try {
            java.util.List<auction.service.client.LotFromService> _return = new java.util.ArrayList<auction.service.client.LotFromService>();
            auction.service.client.LotFromService _returnVal1 = new auction.service.client.LotFromService();
            _returnVal1.setCode("Code512677373");
            _returnVal1.setDescription("Description-448044446");
            _returnVal1.setFinish("Finish698981419");
            _returnVal1.setName("Name1612036888");
            _returnVal1.setOwner("Owner-1103306246");
            _returnVal1.setPrice("Price-331448614");
            _returnVal1.setState("State549723685");
            _return.add(_returnVal1);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see auction.service.client.AuctionSEI#getLotInfo(int  arg0 )*
     */
    public auction.service.client.LotFromService getLotInfo(int arg0) { 
        LOG.info("Executing operation getLotInfo");
        System.out.println(arg0);
        try {
            auction.service.client.LotFromService _return = new auction.service.client.LotFromService();
            _return.setCode("Code296997908");
            _return.setDescription("Description338292564");
            _return.setFinish("Finish-646598345");
            _return.setName("Name1574911553");
            _return.setOwner("Owner127661127");
            _return.setPrice("Price-730240689");
            _return.setState("State-929181011");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see auction.service.client.AuctionSEI#authentication(auction.service.client.UserFrowService  arg0 )*
     */
    public auction.service.client.UserFrowService authentication(auction.service.client.UserFrowService arg0) { 
        LOG.info("Executing operation authentication");
        System.out.println(arg0);
        try {
            auction.service.client.UserFrowService _return = new auction.service.client.UserFrowService();
            _return.setFname("Fname-118344084");
            _return.setId("Id1530442336");
            _return.setLname("Lname-1679767237");
            _return.setLogin("Login1245119967");
            _return.setPassword("Password-421503268");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see auction.service.client.AuctionSEI#newBid(auction.service.client.BidFromService  arg0 )*
     */
    public boolean newBid(auction.service.client.BidFromService arg0) { 
        LOG.info("Executing operation newBid");
        System.out.println(arg0);
        try {
            boolean _return = true;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
