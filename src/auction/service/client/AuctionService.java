package auction.service.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2015-02-17T15:08:08.748+02:00
 * Generated source version: 2.7.11
 * 
 */
@WebServiceClient(name = "AuctionService", 
                  wsdlLocation = "http://localhost:8080/auction/services/AuctionPort?wsdl",
                  targetNamespace = "http://service.auction/") 
public class AuctionService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.auction/", "AuctionService");
    public final static QName AuctionPort = new QName("http://service.auction/", "AuctionPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/auction/services/AuctionPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AuctionService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/auction/services/AuctionPort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AuctionService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AuctionService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AuctionService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AuctionService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AuctionService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public AuctionService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns AuctionSEI
     */
    @WebEndpoint(name = "AuctionPort")
    public AuctionSEI getAuctionPort() {
        return super.getPort(AuctionPort, AuctionSEI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AuctionSEI
     */
    @WebEndpoint(name = "AuctionPort")
    public AuctionSEI getAuctionPort(WebServiceFeature... features) {
        return super.getPort(AuctionPort, AuctionSEI.class, features);
    }

}
