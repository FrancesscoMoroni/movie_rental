package movie_rental.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movie_rental database table.
 * 
 */
@Entity
@Table(name="movie_rental_has_films")
@NamedQuery(name="MovieRentalHasFilm.findAll", query="SELECT m FROM MovieRentalHasFilm m")
public class MovieRentalHasFilm implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private MovieRentalHasFilmPK id;
	
	@ManyToOne
	@JoinColumn(name="movie_rental_idmovie_rental", insertable=false, updatable=false)
	private MovieRental movieRental;
	
	
	@ManyToOne
		@JoinColumns({
		@JoinColumn(name="films_idfilms", insertable=false, updatable=false),
		@JoinColumn(name="directors_iddirectors", insertable=false, updatable=false)
	})
	private Film film;
	
	private float price;
	
	public MovieRentalHasFilmPK getId() {
		return this.id;
	}

	public void setId(MovieRentalHasFilmPK id) {
		this.id = id;
	}
	
	public MovieRental getMovieRental() {
		return this.movieRental;
	}
	
	public void setMovieRental(MovieRental movieRental) {
		this.movieRental = movieRental;
	}
	
	public Film getFilms() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


}
