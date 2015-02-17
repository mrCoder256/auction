package auction.integration.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import auction.integration.dao.AbstractDAO;
import auction.integration.domain.Lot;

public class LotDAO<T> extends AbstractDAO<T> {

	private static final Logger log = Logger.getLogger(LotDAO.class);
	private static final String SELECT_QUERY = "SELECT x FROM Lot x ORDER BY x.id";

	public LotDAO(EntityManagerFactory emf) {
		super(emf);
	}

	protected String createSelectQuery() {
		return SELECT_QUERY;
	}

	public T find(Object param) {
		if (param == null) {
			log.error(this.getClass().getSimpleName()
					+ ": null parameter passed into find method.");
			return null;
		}

		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		} catch (Exception e) {
			log.fatal("An error occurred while creating entity manager", e);
			return null;
		}

		Lot el = new Lot();

		try {
			em.getTransaction().begin();
			el = em.find(Lot.class, param);
			em.getTransaction().commit();

			if (log.isDebugEnabled()) {
				if (el != null) {
					log.debug("Lot with Id=" + param + " successfully found.");
				} else
					log.error("Lot with Id=" + param + " not found.");
			}
		} catch (Exception e) {
			log.error("An error occurred while searching for the lot with Id="
					+ param, e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return (T) el;
	}

	public int getId(Object obj) {
		if (obj == null) {
			log.error(this.getClass().getSimpleName()
					+ ": null parameter passed into getId method.");
			return 0;
		}

		return ((Lot) obj).getId();
	}
}
