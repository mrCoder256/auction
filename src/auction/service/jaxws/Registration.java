
package auction.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.11
 * Sat Feb 14 17:18:55 EET 2015
 * Generated source version: 2.7.11
 */

@XmlRootElement(name = "registration", namespace = "http://service.auction/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registration", namespace = "http://service.auction/")

public class Registration {

    @XmlElement(name = "arg0")
    private auction.service.UserFrowService arg0;

    public auction.service.UserFrowService getArg0() {
        return this.arg0;
    }

    public void setArg0(auction.service.UserFrowService newArg0)  {
        this.arg0 = newArg0;
    }

}

