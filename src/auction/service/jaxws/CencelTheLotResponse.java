
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

@XmlRootElement(name = "cencelTheLotResponse", namespace = "http://service.auction/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cencelTheLotResponse", namespace = "http://service.auction/")

public class CencelTheLotResponse {

    @XmlElement(name = "return")
    private auction.service.LotFromService _return;

    public auction.service.LotFromService getReturn() {
        return this._return;
    }

    public void setReturn(auction.service.LotFromService new_return)  {
        this._return = new_return;
    }

}

