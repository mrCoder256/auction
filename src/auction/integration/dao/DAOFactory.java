package auction.integration.dao;

import auction.integration.dao.impl.BidDAO;
import auction.integration.dao.impl.LotDAO;
import auction.integration.dao.impl.UserDAO;

public abstract class DAOFactory {
	
	public static final int POSTGRESQL = 1;

	public abstract BidDAO getBidDAO();
	public abstract LotDAO getLotDAO();
	public abstract UserDAO getUserDAO();
	
	public static DAOFactory getDAOFactory(int whichFactory) {		  
		switch (whichFactory) {
			case POSTGRESQL: 
				return PostgreSQLDAOFactory.getInstance();
			default:
				return null;
		}
	}
}
