package mrproject.cart;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import movie_rental.dao.MovieRentalDAO;
import movie_rental.dao.MovieRentalHasFilmDAO;
import movie_rental.dao.UserDAO;
import movie_rental.entities.MovieRental;
import movie_rental.entities.MovieRentalHasFilm;
import movie_rental.entities.User;


@Named
@RequestScoped
public class CartBB {
	
	private List<MovieRentalHasFilm> movieRentalHasFilms;
	private User user;
	private double price;
	private MovieRental movieRental;
	
	public List<MovieRentalHasFilm> getMovieRentalHasFilms() {
		return movieRentalHasFilms;
	}

	public void setMovieRentalHasFilms(List<MovieRentalHasFilm> movieRentalHasFilms) {
		this.movieRentalHasFilms = movieRentalHasFilms;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	@Inject
	MovieRentalDAO movieRentalDAO;
	
	@Inject
	MovieRentalHasFilmDAO movieRentalHasFilmDAO;
	
	@PostConstruct
    public void init() {
		user = getUserFromRC();
		movieRental = movieRentalDAO.getActiveMovieRental(user.getIdusers());
		if (movieRental != null) {
			movieRentalHasFilms = movieRentalHasFilmDAO.getFullList(movieRental);
			price = movieRentalHasFilmDAO.getSumPrice(movieRental);
		}
    }
	
	public User getUserFromRC() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		RemoteClient remoteClient = RemoteClient.load(session);
		return (User) remoteClient.getDetails();
	}
	
	public void removeItem(MovieRentalHasFilm movieRentalHasFilm) {
		movieRentalHasFilmDAO.remove(movieRentalHasFilm);
		movieRentalHasFilms.remove(movieRentalHasFilm);
	}
	
	public void confirmeCart() {
		if (movieRental != null) {
			movieRental.setRentalStatus((byte) 1);
			movieRentalDAO.merge(movieRental);
			movieRentalHasFilms = null;
			price = 0;
		}
	}
}
