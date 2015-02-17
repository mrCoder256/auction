package auction.integration.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import auction.integration.dao.impl.BidDAO;
import auction.integration.domain.Bid;

public abstract class AbstractDAO<T> implements IGenericDAO<T> {

	private static final Logger log = Logger.getLogger(BidDAO.class);
	protected EntityManagerFactory emf;

	public AbstractDAO(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}	
	
	protected abstract String createSelectQuery();
	
	public Collection<?> select() {
		EntityManager em = null;
		try{
			em = emf.createEntityManager();
		} catch (Exception e) {
			log.fatal("An error occurred while creating entity manager", e);
			return null;
		}
		
		List<?> list = null;
		try {
			em.getTransaction().begin();
			Query q = em.createQuery(createSelectQuery()); 
			list = q.getResultList();			
			em.getTransaction().commit();
			
			log.debug("Selected all rows by " + this.getClass().getSimpleName());
		} catch (Exception e) {
			log.error("An error occurred while selecting rows by " + this.getClass().getSimpleName(), e);
			// ����� ���������� � ������ ������
			em.getTransaction().rollback();
		} finally {
			// ���������� ������ � ���������� ���������
			em.close();
		}
		return (Collection<?>) list;	
	}
	
	public int insert(Object obj) {
		if (obj == null) {
			log.error(this.getClass().getSimpleName() + ": null parameter passed into insert method.");
			return 0;
		}
		
		int newId = 0;
		EntityManager em = null;
		try{
			em = emf.createEntityManager();
		} catch (Exception e) {
			log.fatal("An error occurred while creating entity manager", e);
			return 0;
		}		
		
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
			newId = getId(obj);
			
			log.debug("Entity with Id=" + getId(obj) + " seccessfully inserted by " + this.getClass().getSimpleName());
		} catch (Exception e) {
			log.error("An error occurred while inserting entity with Id=" + newId + 
				" by " + this.getClass().getSimpleName(), e);
			// ����� ���������� � ������ ������
			newId = 0;
			em.getTransaction().rollback();
		} finally {
			// ���������� ������ � ���������� ���������
			em.close();
		}
		return newId;
	}

	public abstract int getId(Object obj);

	public boolean update(Object obj) {
		if (obj == null) {
			log.error(this.getClass().getSimpleName() + ": null parameter passed into update method.");
			return false;
		}
		
		boolean successfully = true;
		EntityManager em = null;
		try{
			em = emf.createEntityManager();
		} catch (Exception e) {
			log.fatal("An error occurred while creating entity manager", e);
			return false;
		}		
		
		try {
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
			
			log.debug("Entity with Id=" + getId(obj) + " seccessfully updated by " + this.getClass().getSimpleName());
		} catch (Exception e) {
			log.error("An error occurred while updating entity with Id=" + getId(obj) + 
				" by " + this.getClass().getSimpleName(), e);
			// ����� ���������� � ������ ������
			successfully = false;
			em.getTransaction().rollback();
		} finally {
			// ���������� ������ � ���������� ���������
			em.close();
		}
		return successfully;
	}

	public boolean delete(Object obj) {
		if (obj == null) {
			log.error(this.getClass().getSimpleName() + ": null parameter passed into delete method.");
			return false;
		}
		
		boolean successfully = true;
		EntityManager em = null;
		try{
			em = emf.createEntityManager();
		} catch (Exception e) {
			log.fatal("An error occurred while creating entity manager", e);
			return false;
		}		
		
		try {
			em.getTransaction().begin();
			Object toBeRemoved = em.merge(obj);
			em.remove(toBeRemoved);
			em.getTransaction().commit();
			
			log.debug("Entity with Id=" + getId(obj) + " seccessfully deleted "
				+ "by " + this.getClass().getSimpleName());
		} catch (Exception e) {
			log.error("An error occurred while deleting entity with Id=" + getId(obj) + 
				" by " + this.getClass().getSimpleName(), e);
			// ����� ���������� � ������ ������
			successfully = false;
			em.getTransaction().rollback();
		} finally {
			// ���������� ������ � ���������� ���������
			em.close();
		}
		return successfully;
	}

}
