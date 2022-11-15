package movie_rental.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the directors database table.
 * 
 */
@Entity
@Table(name="directors")
@NamedQuery(name="Director.findAll", query="SELECT d FROM Director d")
public class Director implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int iddirectors;

	private String name;

	private String surname;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="director")
	private List<Film> films;

	public Director() {
	}

	public int getIddirectors() {
		return this.iddirectors;
	}

	public void setIddirectors(int iddirectors) {
		this.iddirectors = iddirectors;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setDirector(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setDirector(null);

		return film;
	}

}