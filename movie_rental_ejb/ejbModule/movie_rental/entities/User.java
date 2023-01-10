package movie_rental.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idusers;

	private String login;

	private String password;

	private String role;

	//bi-directional many-to-one association to MovieRental
	@OneToMany(mappedBy="user")
	private List<MovieRental> movieRentals;

	public User() {
	}
	
	public User(String login, String pass, String role) {
		this.setLogin(login);
		this.setPassword(pass);
		this.setRole(role);
	}

	public int getIdusers() {
		return this.idusers;
	}

	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<MovieRental> getMovieRentals() {
		return this.movieRentals;
	}

	public void setMovieRentals(List<MovieRental> movieRentals) {
		this.movieRentals = movieRentals;
	}

	public MovieRental addMovieRental(MovieRental movieRental) {
		getMovieRentals().add(movieRental);
		movieRental.setUser(this);

		return movieRental;
	}

	public MovieRental removeMovieRental(MovieRental movieRental) {
		getMovieRentals().remove(movieRental);
		movieRental.setUser(null);

		return movieRental;
	}

}