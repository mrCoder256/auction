package auction.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the bid database table.
 * 
 */
@Entity
@NamedQuery(name="Bid.findAll", query="SELECT b FROM Bid b")
public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private float bid;

	@Column(name="bids_time", insertable = false)
	private Timestamp bidsTime;

	//bi-directional many-to-one association to Lot
	@ManyToOne
	@JoinColumn(name="id_lot")
	private Lot lot;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_bidder")
	private User user;

	public Bid() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getBid() {
		return this.bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public Timestamp getBidsTime() {
		return this.bidsTime;
	}

	public void setBidsTime(Timestamp bidsTime) {
		this.bidsTime = bidsTime;
	}

	public Lot getLot() {
		return this.lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String toString(){
		return "Bid: " + getId() + ", " + getUser().getId()
			+ ", " + getLot().getId() + ", " + getBid() + ", " + getBidsTime();
	}

}