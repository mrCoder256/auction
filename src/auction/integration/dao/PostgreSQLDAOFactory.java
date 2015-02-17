package auction.integration.dao;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import auction.integration.dao.impl.BidDAO;
import auction.integration.dao.impl.LotDAO;
import auction.integration.dao.impl.UserDAO;

public class PostgreSQLDAOFactory extends DAOFactory {

	private static PostgreSQLDAOFactory _singleton;
	private EntityManagerFactory emf;
	private static final Logger log = Logger.getLogger(PostgreSQLDAOFactory.class);

	private PostgreSQLDAOFactory() {	
		Properties props = new Properties();

		props.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");		
		props.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/auction");		
		props.put("javax.persistence.jdbc.user", "postgres");
		props.put("javax.persistence.jdbc.password", "1q1q");
		
		emf = Persistence.createEntityManagerFactory("auction", props);
		log.debug("Created entity manager factory for PostgreSQLDAOFactory");
	}
	
	public static PostgreSQLDAOFactory getInstance() {
		if (_singleton == null) {
			_singleton = new PostgreSQLDAOFactory();
		}
		return _singleton;
	}
	
	public BidDAO<?> getBidDAO() {
		log.debug("Created BidDAO in PostgreSQLDAOFactory");
		return new BidDAO(emf);
	}
	
	public LotDAO getLotDAO() {
		log.debug("Created LotDAO in PostgreSQLDAOFactory");
		return new LotDAO(emf);
	}
	
	public UserDAO<?> getUserDAO() {
		log.debug("Created UserDAO in PostgreSQLDAOFactory");
		return new UserDAO(emf);
	}

}
