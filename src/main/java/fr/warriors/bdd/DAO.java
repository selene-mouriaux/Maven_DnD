package fr.warriors.bdd;

import java.sql.Connection;
import java.util.List;


public abstract class DAO<T> {
	protected Connection connect = null;

	public DAO(Connection conn) {
		this.connect = conn;
	}

	/**
	 * Méthode de création
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract T create(T obj);

	/**
	 * Méthode pour effacer
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract T delete(T obj);

	/**
	 * Méthode de mise à jour
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract T update(T obj);

	/**
	 * Méthode de recherche des informations par Id
	 * 
	 * @param id
	 * @return T
	 */
	public abstract T findById(String id);

	/**
	 * Méthode de recherche des informations de toute la table
	 *  
	 * @return
	 */
	public abstract List<T> findAll();

}
