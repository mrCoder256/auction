
package auction.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for lotFromService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lotFromService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="finish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lotFromService", propOrder = {
    "code",
    "description",
    "finish",
    "name",
    "owner",
    "price",
    "state"
})
public class LotFromService {

	private String code;
	private String name;
	private String finish;
	private String state;
	private String owner;
	private String description;
	private String price;
	
	/**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getCode() {
		return code;
	}
	/**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setCode(String code) {
		this.code = code;
	}
	/**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getName() {
		return name;
	}
	/**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * Gets the value of the finish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getFinish() {
		return finish;
	}
	/**
     * Sets the value of the finish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setFinish(String finish) {
		this.finish = finish;
	}
	/**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getState() {
		return state;
	}
	/**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setState(String state) {
		this.state = state;
	}
	/**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getOwner() {
		return owner;
	}
	/**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getDescription() {
		return description;
	}
	/**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String getPrice() {
		return price;
	}
	/**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setPrice(String price) {
		this.price = price;
	}

}
