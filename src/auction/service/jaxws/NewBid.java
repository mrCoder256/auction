
package auction.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.11
 * Wed Jan 07 16:08:55 EET 2015
 * Generated source version: 2.7.11
 */

@XmlRootElement(name = "newBid", namespace = "http://service.auction/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "newBid", namespace = "http://service.auction/")

public class NewBid {

    @XmlElement(name = "arg0")
    private auction.service.BidFromService arg0;

    public auction.service.BidFromService getArg0() {
        return this.arg0;
    }

    public void setArg0(auction.service.BidFromService newArg0)  {
        this.arg0 = newArg0;
    }

}

