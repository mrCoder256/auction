
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

@XmlRootElement(name = "newBidResponse", namespace = "http://service.auction/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "newBidResponse", namespace = "http://service.auction/")

public class NewBidResponse {

    @XmlElement(name = "return")
    private boolean _return;

    public boolean getReturn() {
        return this._return;
    }

    public void setReturn(boolean new_return)  {
        this._return = new_return;
    }

}

