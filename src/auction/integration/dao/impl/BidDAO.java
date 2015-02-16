package auction.integration.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import auction.integration.dao.AbstractDAO;
import auction.integration.domain.Bid;

public class BidDAO<T> extends AbstractDAO<T> {

	private static final Logger log = Logger.getLogger(BidDAO.class);
	
	public BidDAO(EntityManagerFactory emf) {
		super(emf);
	}
	
	protected String createSelectQuery() {
		return "SELECT x FROM Bid x ORDER BY x.id";
	}

	public T find(Object param) {
		if (param == null) {
			log.error(this.getClass().getSimpleName() + ": null parameter passed into find method.");
			return null;
		}
		
		EntityManager em = null;
		try{
			em = emf.createEntityManager();
		} catch (Exception e) {
			log.fatal("An error occurred while creating entity manager", e);
			return null;
		}
		
		Bid el = new Bid();
		
		try {
			em.getTransaction().begin();
			el = em.find(Bid.class, param);
			em.getTransaction().commit();		
			
			if (log.isDebugEnabled()){
				if (el != null) {
					log.debug("Bid with Id=" + param + " successfully found."); 
				} else log.error("Bid with Id=" + param + " not found.");
			}
		} catch (Exception e) {
			log.error("An error occurred while searching for the bid with Id=" + param, e);//e.printStackTrace();
			// ����� ���������� � ������ ������
			em.getTransaction().rollback();
		} finally {
			// ���������� ������ � ���������� ���������
			em.close();
		}
		return (T) el;
	}

	public int getId(Object obj) {
		if (obj == null) {
			log.error(this.getClass().getSimpleName() + ": null parameter passed into getId method.");
			return 0;
		}
		
		return ((Bid) obj).getId();
	}
}
