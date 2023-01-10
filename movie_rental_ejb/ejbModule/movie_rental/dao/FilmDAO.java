package movie_rental.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import movie_rental.entities.Film;
import movie_rental.entities.MovieRental;

@Stateless
public class FilmDAO {
	private final static String UNIT_NAME = "filmDS";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Film film) {
		em.persist(film);
	}
	
	public Film merge(Film film) {
		return em.merge(film);
	}

	public List<Film> getFilms() {
		List<Film> films = null;
		String sql = "SELECT f FROM Film f";
		Query query = em.createQuery(sql);
		try {
			films = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return films;
	}
	
	public List<Film> getFilms(String filtr) {
		List<Film> films = null;
		Query query = em.createQuery(filtr);
		try {
			films = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return films;
	}
	/*
	public boolean isInDatabase(String login) {
		User user = new User();
		String sql = "SELECT u FROM User u WHERE u.login = '" + login + "'";
		TypedQuery<User> query = em.createQuery(sql, User.class);
		
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if (user != null) {
			return false;
		}
		
		return true;
	}
	*/
}
