package auction.integration.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import auction.integration.dao.AbstractDAO;
import auction.integration.domain.User;

public class UserDAO<T> extends AbstractDAO<T> {

	private static final Logger log = Logger.getLogger(UserDAO.class);
	private static final String SELECT_QUERY = "SELECT x FROM User x ORDER BY x.id";

	public UserDAO(EntityManagerFactory emf) {
		super(emf);
	}

	protected String createSelectQuery() {
		return SELECT_QUERY;
	}

	public T find(Object param) {
		if (param == null) {
			log.error(this.getClass().getSimpleName()
					+ ": null parameter passed into find method.");
		}

		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		} catch (Exception e) {
			log.fatal("An error occurred while creating entity manager", e);
			return null;
		}

		User el = new User();

		try {
			em.getTransaction().begin();
			el = em.find(User.class, param);
			em.getTransaction().commit();

			if (log.isDebugEnabled()) {
				if (el != null) {
					log.debug("User with Id=" + param + " successfully found.");
				} else
					log.error("User with Id=" + param + " not found.");
			}
		} catch (Exception e) {
			log.error("An error occurred while searching for the user with Id="
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

		return ((User) obj).getId();
	}

	public User findByLogin(String login) {
		if (login == null) {
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

		List<?> list = null;

		try {
			em.getTransaction().begin();
			Query q = em.createNamedQuery("User.findByLogin").setParameter(
					"login", login);
			list = q.getResultList();
			em.getTransaction().commit();

			if (log.isDebugEnabled()) {
				if (list.size() != 0) {
					log.debug("User with login=" + login
							+ " successfully found.");
				} else
					log.error("User with login=" + login + " not found.");
			}
		} catch (Exception e) {
			log.error(
					"An error occurred while searching for the user with login="
							+ login, e);
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return (list.size() != 0) ? (User) list.get(0) : null;
	}
}
