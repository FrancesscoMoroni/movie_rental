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
	
	@Id
	@Column(name="idmovie_rental_has_films")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="movie_rental_idmovie_rental")
	private MovieRental movieRental;
	
	
	@ManyToOne
	@JoinColumn(name="films_idfilms")
	private Film film;
	
	private float price;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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
