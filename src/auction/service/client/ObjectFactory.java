
package auction.service.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the auction.service.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CencelTheLot_QNAME = new QName("http://service.auction/", "cencelTheLot");
    private final static QName _GetLotInfoResponse_QNAME = new QName("http://service.auction/", "getLotInfoResponse");
    private final static QName _GetLotsResponse_QNAME = new QName("http://service.auction/", "getLotsResponse");
    private final static QName _NewBid_QNAME = new QName("http://service.auction/", "newBid");
    private final static QName _NewBidResponse_QNAME = new QName("http://service.auction/", "newBidResponse");
    private final static QName _Registration_QNAME = new QName("http://service.auction/", "registration");
    private final static QName _NewLotResponse_QNAME = new QName("http://service.auction/", "newLotResponse");
    private final static QName _GetBidsOnLot_QNAME = new QName("http://service.auction/", "getBidsOnLot");
    private final static QName _GetBidsOnLotResponse_QNAME = new QName("http://service.auction/", "getBidsOnLotResponse");
    private final static QName _Authentication_QNAME = new QName("http://service.auction/", "authentication");
    private final static QName _AuthenticationResponse_QNAME = new QName("http://service.auction/", "authenticationResponse");
    private final static QName _RegistrationResponse_QNAME = new QName("http://service.auction/", "registrationResponse");
    private final static QName _NewLot_QNAME = new QName("http://service.auction/", "newLot");
    private final static QName _GetLots_QNAME = new QName("http://service.auction/", "getLots");
    private final static QName _CencelTheLotResponse_QNAME = new QName("http://service.auction/", "cencelTheLotResponse");
    private final static QName _GetLotInfo_QNAME = new QName("http://service.auction/", "getLotInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: auction.service.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLotsResponse }
     * 
     */
    public GetLotsResponse createGetLotsResponse() {
        return new GetLotsResponse();
    }

    /**
     * Create an instance of {@link GetLotInfoResponse }
     * 
     */
    public GetLotInfoResponse createGetLotInfoResponse() {
        return new GetLotInfoResponse();
    }

    /**
     * Create an instance of {@link CencelTheLot }
     * 
     */
    public CencelTheLot createCencelTheLot() {
        return new CencelTheLot();
    }

    /**
     * Create an instance of {@link Registration }
     * 
     */
    public Registration createRegistration() {
        return new Registration();
    }

    /**
     * Create an instance of {@link NewLotResponse }
     * 
     */
    public NewLotResponse createNewLotResponse() {
        return new NewLotResponse();
    }

    /**
     * Create an instance of {@link NewBid }
     * 
     */
    public NewBid createNewBid() {
        return new NewBid();
    }

    /**
     * Create an instance of {@link NewBidResponse }
     * 
     */
    public NewBidResponse createNewBidResponse() {
        return new NewBidResponse();
    }

    /**
     * Create an instance of {@link NewLot }
     * 
     */
    public NewLot createNewLot() {
        return new NewLot();
    }

    /**
     * Create an instance of {@link GetLots }
     * 
     */
    public GetLots createGetLots() {
        return new GetLots();
    }

    /**
     * Create an instance of {@link RegistrationResponse }
     * 
     */
    public RegistrationResponse createRegistrationResponse() {
        return new RegistrationResponse();
    }

    /**
     * Create an instance of {@link Authentication }
     * 
     */
    public Authentication createAuthentication() {
        return new Authentication();
    }

    /**
     * Create an instance of {@link AuthenticationResponse }
     * 
     */
    public AuthenticationResponse createAuthenticationResponse() {
        return new AuthenticationResponse();
    }

    /**
     * Create an instance of {@link GetBidsOnLotResponse }
     * 
     */
    public GetBidsOnLotResponse createGetBidsOnLotResponse() {
        return new GetBidsOnLotResponse();
    }

    /**
     * Create an instance of {@link GetBidsOnLot }
     * 
     */
    public GetBidsOnLot createGetBidsOnLot() {
        return new GetBidsOnLot();
    }

    /**
     * Create an instance of {@link GetLotInfo }
     * 
     */
    public GetLotInfo createGetLotInfo() {
        return new GetLotInfo();
    }

    /**
     * Create an instance of {@link CencelTheLotResponse }
     * 
     */
    public CencelTheLotResponse createCencelTheLotResponse() {
        return new CencelTheLotResponse();
    }

    /**
     * Create an instance of {@link UserFrowService }
     * 
     */
    public UserFrowService createUserFrowService() {
        return new UserFrowService();
    }

    /**
     * Create an instance of {@link LotFromService }
     * 
     */
    public LotFromService createLotFromService() {
        return new LotFromService();
    }

    /**
     * Create an instance of {@link BidFromService }
     * 
     */
    public BidFromService createBidFromService() {
        return new BidFromService();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CencelTheLot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "cencelTheLot")
    public JAXBElement<CencelTheLot> createCencelTheLot(CencelTheLot value) {
        return new JAXBElement<CencelTheLot>(_CencelTheLot_QNAME, CencelTheLot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLotInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "getLotInfoResponse")
    public JAXBElement<GetLotInfoResponse> createGetLotInfoResponse(GetLotInfoResponse value) {
        return new JAXBElement<GetLotInfoResponse>(_GetLotInfoResponse_QNAME, GetLotInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLotsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "getLotsResponse")
    public JAXBElement<GetLotsResponse> createGetLotsResponse(GetLotsResponse value) {
        return new JAXBElement<GetLotsResponse>(_GetLotsResponse_QNAME, GetLotsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewBid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "newBid")
    public JAXBElement<NewBid> createNewBid(NewBid value) {
        return new JAXBElement<NewBid>(_NewBid_QNAME, NewBid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewBidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "newBidResponse")
    public JAXBElement<NewBidResponse> createNewBidResponse(NewBidResponse value) {
        return new JAXBElement<NewBidResponse>(_NewBidResponse_QNAME, NewBidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Registration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "registration")
    public JAXBElement<Registration> createRegistration(Registration value) {
        return new JAXBElement<Registration>(_Registration_QNAME, Registration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewLotResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "newLotResponse")
    public JAXBElement<NewLotResponse> createNewLotResponse(NewLotResponse value) {
        return new JAXBElement<NewLotResponse>(_NewLotResponse_QNAME, NewLotResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBidsOnLot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "getBidsOnLot")
    public JAXBElement<GetBidsOnLot> createGetBidsOnLot(GetBidsOnLot value) {
        return new JAXBElement<GetBidsOnLot>(_GetBidsOnLot_QNAME, GetBidsOnLot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBidsOnLotResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "getBidsOnLotResponse")
    public JAXBElement<GetBidsOnLotResponse> createGetBidsOnLotResponse(GetBidsOnLotResponse value) {
        return new JAXBElement<GetBidsOnLotResponse>(_GetBidsOnLotResponse_QNAME, GetBidsOnLotResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authentication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "authentication")
    public JAXBElement<Authentication> createAuthentication(Authentication value) {
        return new JAXBElement<Authentication>(_Authentication_QNAME, Authentication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "authenticationResponse")
    public JAXBElement<AuthenticationResponse> createAuthenticationResponse(AuthenticationResponse value) {
        return new JAXBElement<AuthenticationResponse>(_AuthenticationResponse_QNAME, AuthenticationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "registrationResponse")
    public JAXBElement<RegistrationResponse> createRegistrationResponse(RegistrationResponse value) {
        return new JAXBElement<RegistrationResponse>(_RegistrationResponse_QNAME, RegistrationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NewLot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "newLot")
    public JAXBElement<NewLot> createNewLot(NewLot value) {
        return new JAXBElement<NewLot>(_NewLot_QNAME, NewLot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLots }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "getLots")
    public JAXBElement<GetLots> createGetLots(GetLots value) {
        return new JAXBElement<GetLots>(_GetLots_QNAME, GetLots.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CencelTheLotResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "cencelTheLotResponse")
    public JAXBElement<CencelTheLotResponse> createCencelTheLotResponse(CencelTheLotResponse value) {
        return new JAXBElement<CencelTheLotResponse>(_CencelTheLotResponse_QNAME, CencelTheLotResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLotInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.auction/", name = "getLotInfo")
    public JAXBElement<GetLotInfo> createGetLotInfo(GetLotInfo value) {
        return new JAXBElement<GetLotInfo>(_GetLotInfo_QNAME, GetLotInfo.class, null, value);
    }

}
