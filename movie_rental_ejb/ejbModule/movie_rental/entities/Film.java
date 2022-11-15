package movie_rental.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the films database table.
 * 
 */
@Entity
@Table(name="films")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilmPK id;

	private byte archival;

	@Lob
	private String description;

	@Lob
	@Column(name="film_img")
	private String filmImg;

	private String filmscol;

	@Column(name="movie_genre")
	private String movieGenre;

	private String name;

	private float price;

	private int rating;

	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	private Date releaseDate;

	//bi-directional many-to-one association to Director
	@ManyToOne
	@JoinColumn(name="directors_iddirectors", insertable=false, updatable=false)
	private Director director;

	//bi-directional many-to-one association to MovieRentalHasFilm
	@OneToMany(mappedBy="film")
	private List<MovieRentalHasFilm> movieRentalHasFilms;

	public Film() {
	}

	public FilmPK getId() {
		return this.id;
	}

	public void setId(FilmPK id) {
		this.id = id;
	}

	public byte getArchival() {
		return this.archival;
	}

	public void setArchival(byte archival) {
		this.archival = archival;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilmImg() {
		return this.filmImg;
	}

	public void setFilmImg(String filmImg) {
		this.filmImg = filmImg;
	}

	public String getFilmscol() {
		return this.filmscol;
	}

	public void setFilmscol(String filmscol) {
		this.filmscol = filmscol;
	}

	public String getMovieGenre() {
		return this.movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Director getDirector() {
		return this.director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public List<MovieRentalHasFilm> getMovieRentalHasFilms() {
		return this.movieRentalHasFilms;
	}

	public void setMovieRentalHasFilms(List<MovieRentalHasFilm> movieRentalHasFilms) {
		this.movieRentalHasFilms = movieRentalHasFilms;
	}

	public MovieRentalHasFilm addMovieRentalHasFilm(MovieRentalHasFilm movieRentalHasFilm) {
		getMovieRentalHasFilms().add(movieRentalHasFilm);
		movieRentalHasFilm.setFilm(this);

		return movieRentalHasFilm;
	}

	public MovieRentalHasFilm removeMovieRentalHasFilm(MovieRentalHasFilm movieRentalHasFilm) {
		getMovieRentalHasFilms().remove(movieRentalHasFilm);
		movieRentalHasFilm.setFilm(null);

		return movieRentalHasFilm;
	}

}