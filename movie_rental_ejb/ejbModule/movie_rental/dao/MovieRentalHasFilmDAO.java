package movie_rental.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import movie_rental.entities.Film;
import movie_rental.entities.MovieRental;
import movie_rental.entities.MovieRentalHasFilm;

@Stateless
public class MovieRentalHasFilmDAO {
	private final static String UNIT_NAME = "filmDS";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public MovieRentalHasFilm merge(MovieRentalHasFilm movieRentalHasFilm) {
		return em.merge(movieRentalHasFilm);
	}
	
	public void create(Film film, MovieRental movieRental) {
		MovieRentalHasFilm movieRentalHasFilm = new MovieRentalHasFilm();
		movieRentalHasFilm.setMovieRental(movieRental);
		movieRentalHasFilm.setFilm(film);
		movieRentalHasFilm.setPrice(film.getPrice());
		em.persist(movieRentalHasFilm);
	}
	
	public void remove(MovieRentalHasFilm movieRentalHasFilm) {
		em.remove(em.merge(movieRentalHasFilm));
	}
	
	public List<MovieRentalHasFilm> getFullList(MovieRental movieRental) {
		List<MovieRentalHasFilm> movieRentalHasFilms = null;
		String sql = "SELECT m FROM MovieRentalHasFilm m WHERE m.movieRental = " + movieRental.getIdmovieRental();
		Query query = em.createQuery(sql);
		try {
			movieRentalHasFilms = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return movieRentalHasFilms;
	}
	
	public double getSumPrice(MovieRental movieRental) {
		double sum;
		String sql = "SELECT Sum(price) FROM MovieRentalHasFilm m WHERE m.movieRental = " + movieRental.getIdmovieRental();
		Query query = em.createQuery(sql);
		try {
			sum = (double) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return sum;
	}

}
