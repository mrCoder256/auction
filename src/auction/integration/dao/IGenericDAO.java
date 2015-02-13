package auction.integration.dao;

import java.util.Collection;

public interface IGenericDAO<T>{
	public Collection<?> select();
	public int insert(T object);
	public boolean update(T object);
	public boolean delete(T object);
	public T find(T param);
}
