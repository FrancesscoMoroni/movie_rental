package movie_rental.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the films database table.
 * 
 */
@Embeddable
public class MovieRentalHasFilmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="idmovie_rental_has_films")
	private int idmovieRentalHasFilms;

	@Column(name="movie_rental_idmovie_rental")
	private int movieRentalIdmovieRental;
	
	@Column(name="films_idfilms")
	private int filmsIdfilms;

	public MovieRentalHasFilmPK() {
	}
	public int getIdmovieRentalHasFilms() {
		return this.idmovieRentalHasFilms;
	}
	public void getIdmovieRentalHasFilms(int idmovieRentalHasFilms) {
		this.idmovieRentalHasFilms = idmovieRentalHasFilms;
	}
	public int getMovieRentalIdmovieRental() {
		return this.movieRentalIdmovieRental;
	}
	public void setMovieRentalIdmovieRental(int movieRentalIdmovieRental) {
		this.movieRentalIdmovieRental = movieRentalIdmovieRental;
	}
	public int getFilmsIdfilms() {
		return this.movieRentalIdmovieRental;
	}
	public void setFilmsIdfilms(int filmsIdfilms) {
		this.filmsIdfilms = filmsIdfilms;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MovieRentalHasFilmPK)) {
			return false;
		}
		MovieRentalHasFilmPK castOther = (MovieRentalHasFilmPK)other;
		return 
			(this.idmovieRentalHasFilms == castOther.idmovieRentalHasFilms)
			&& (this.movieRentalIdmovieRental == castOther.movieRentalIdmovieRental)
			&& (this.filmsIdfilms == castOther.filmsIdfilms);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idmovieRentalHasFilms;
		hash = hash * prime + this.movieRentalIdmovieRental;
		hash = hash * prime + this.filmsIdfilms;
		
		return hash;
	}
}