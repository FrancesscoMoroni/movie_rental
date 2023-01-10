package movie_rental.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the movie_rental database table.
 * 
 */
@Entity
@Table(name="movie_rental")
@NamedQuery(name="MovieRental.findAll", query="SELECT m FROM MovieRental m")
public class MovieRental implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idmovie_rental")
	private int idmovieRental;

	@Temporal(TemporalType.DATE)
	@Column(name="rental_date")
	private Date rentalDate;

	@Column(name="rental_status")
	private byte rentalStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="return_date")
	private Date returnDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_idusers")
	private User user;

	//bi-directional many-to-one association to MovieRentalHasFilm
	@OneToMany(mappedBy="movieRental",
			cascade = CascadeType.ALL)
	private List<MovieRentalHasFilm> movieRentalHasFilms;

	public MovieRental() {
	}

	public int getIdmovieRental() {
		return this.idmovieRental;
	}

	public void setIdmovieRental(int idmovieRental) {
		this.idmovieRental = idmovieRental;
	}

	public Date getRentalDate() {
		return this.rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public byte getRentalStatus() {
		return this.rentalStatus;
	}

	public void setRentalStatus(byte rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<MovieRentalHasFilm> getMovieRentalHasFilms() {
		return this.movieRentalHasFilms;
	}

	public void setMovieRentalHasFilms(List<MovieRentalHasFilm> movieRentalHasFilms) {
		this.movieRentalHasFilms = movieRentalHasFilms;
	}

	public MovieRentalHasFilm addMovieRentalHasFilm(MovieRentalHasFilm movieRentalHasFilm) {
		getMovieRentalHasFilms().add(movieRentalHasFilm);
		movieRentalHasFilm.setMovieRental(this);

		return movieRentalHasFilm;
	}

	public MovieRentalHasFilm removeMovieRentalHasFilm(MovieRentalHasFilm movieRentalHasFilm) {
		getMovieRentalHasFilms().remove(movieRentalHasFilm);
		movieRentalHasFilm.setMovieRental(null);

		return movieRentalHasFilm;
	}

}