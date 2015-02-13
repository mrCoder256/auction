package auction.integration.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")

@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.findByLogin", query="SELECT u FROM User u WHERE u.login = :login")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String fname;

	private String lname;

	private String login;

	private String password;

	//bi-directional many-to-one association to Bid
	@OneToMany(mappedBy="user")
	private List<Bid> bids;

	//bi-directional many-to-one association to Lot
	@OneToMany(mappedBy="user")
	private List<Lot> lots;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Bid> getBids() {
		return this.bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		bid.setUser(this);

		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		bid.setUser(null);

		return bid;
	}

	public List<Lot> getLots() {
		return this.lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}

	public Lot addLot(Lot lot) {
		getLots().add(lot);
		lot.setUser(this);

		return lot;
	}

	public Lot removeLot(Lot lot) {
		getLots().remove(lot);
		lot.setUser(null);

		return lot;
	}
	
	public String toString(){
		return "User: " + getId() + ", " + getPassword();
	}

}