package movie_rental.dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import movie_rental.entities.MovieRental;
import movie_rental.entities.User;

@Stateless
public class MovieRentalDAO {

	private final static String UNIT_NAME = "filmDS";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public MovieRental merge(MovieRental movieRental) {
		return em.merge(movieRental);
	}
	
	public void create(User user) {
		MovieRental movieRental = new MovieRental();
		movieRental.setRentalDate(new Date(System.currentTimeMillis()));
		movieRental.setReturnDate(new Date(System.currentTimeMillis()));
		movieRental.setRentalStatus((byte) 0);
		movieRental.setUser(user);
		em.persist(movieRental);
	}

	public MovieRental getActiveMovieRental(Integer idUser) {
		MovieRental movieRental = null;
		String sql = "SELECT m FROM MovieRental m WHERE m.rentalStatus  = 0 AND m.user = " + idUser;
		TypedQuery<MovieRental> query = em.createQuery(sql, MovieRental.class);
		try {
			movieRental = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return movieRental;
	}
	
	public List<MovieRental> getRentalHistory(Integer idUser) {
		List<MovieRental> movieRentals = null;
		String sql = "SELECT m FROM MovieRental m WHERE m.rentalStatus  = 1 AND m.user = " + idUser;
		Query query = em.createQuery(sql);
		
		try {
			movieRentals = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return movieRentals;
	}
	
}
