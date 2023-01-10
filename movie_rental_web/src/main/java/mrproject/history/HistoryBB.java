package mrproject.history;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.model.LazyDataModel;

import movie_rental.dao.MovieRentalDAO;
import movie_rental.dao.MovieRentalHasFilmDAO;
import movie_rental.entities.MovieRental;
import movie_rental.entities.MovieRentalHasFilm;
import movie_rental.entities.User;
import mrproject.lazydata.MovieRentalLazyData;

@Named
@RequestScoped
public class HistoryBB {

	private User user;
	private List<MovieRental> movieRentals;
	private LazyDataModel<MovieRental> lazyMovieRental;
	
	public User getUser() {
		return user;
	}
	
	public List<MovieRental> getMovieRentals() {
		return movieRentals;
	}

	public void setMovieRentals(List<MovieRental> movieRentals) {
		this.movieRentals = movieRentals;
	}
	
	public LazyDataModel<MovieRental> getLazyMovieRental() {
		return lazyMovieRental;
	}

	public void setLazyMovieRental(LazyDataModel<MovieRental> lazyMovieRental) {
		this.lazyMovieRental = lazyMovieRental;
	}
	
	@Inject
	MovieRentalDAO movieRentalDAO;
	
	@Inject
	MovieRentalHasFilmDAO movieRentalHasFilmDAO;
	
	@PostConstruct
    public void init() {
		user = getUserFromRC();
		movieRentals = movieRentalDAO.getRentalHistory(user.getIdusers());
		lazyMovieRental = new MovieRentalLazyData(movieRentals);
    }
	
	public User getUserFromRC() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		RemoteClient remoteClient = RemoteClient.load(session);
		return (User) remoteClient.getDetails();
	}
	
	public double rentalCost(MovieRental movieRental) {
		return movieRentalHasFilmDAO.getSumPrice(movieRental);
	}
}
