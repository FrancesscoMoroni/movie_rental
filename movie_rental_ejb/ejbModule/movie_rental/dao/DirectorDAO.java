package movie_rental.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import movie_rental.entities.Director;


//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class DirectorDAO {
	
	private final static String UNIT_NAME = "filmDS";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Director director) {
		em.persist(director);
	}

	public Director merge(Director director) {
		return em.merge(director);
	}

	public void remove(Director director) {
		em.remove(em.merge(director));
	}


}
