
package auction.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for bidFromService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bidFromService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bidder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bidFromService", propOrder = {
    "bid",
    "bidder",
    "date",
    "lot"
})
public class BidFromService {
	
	private String bid;
	private String lot;
	private String date;
	private String bidder;
	
	/**
     * Gets the value of the bid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getBid() {
		return bid;
	}
	/**
     * Sets the value of the bid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setBid(String bid) {
		this.bid = bid;
	}
	/**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getDate() {
		return date;
	}
	/**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setDate(String date) {
		this.date = date;
	}
	/**
     * Gets the value of the bidder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getBidder() {
		return bidder;
	}
	/**
     * Sets the value of the bidder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setBidder(String bidder) {
		this.bidder = bidder;
	}
	/**
     * Gets the value of the lot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getLot() {
		return lot;
	}
	/**
     * Sets the value of the lot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setLot(String lot) {
		this.lot = lot;
	}
	
	

}
