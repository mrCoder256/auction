
package auction.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.11
 * Tue Feb 17 15:07:36 EET 2015
 * Generated source version: 2.7.11
 */

@XmlRootElement(name = "getLotInfo", namespace = "http://service.auction/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLotInfo", namespace = "http://service.auction/")

public class GetLotInfo {

    @XmlElement(name = "arg0")
    private int arg0;

    public int getArg0() {
        return this.arg0;
    }

    public void setArg0(int newArg0)  {
        this.arg0 = newArg0;
    }

}

