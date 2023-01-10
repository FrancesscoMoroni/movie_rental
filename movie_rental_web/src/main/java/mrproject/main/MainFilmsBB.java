package mrproject.main;

import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import movie_rental.dao.FilmDAO;
import movie_rental.dao.MovieRentalDAO;
import movie_rental.dao.MovieRentalHasFilmDAO;
import movie_rental.entities.Film;
import movie_rental.entities.User;
import movie_rental.entities.MovieRental;
import movie_rental.entities.MovieRentalHasFilm;

@Named
@RequestScoped
public class MainFilmsBB {
	
	private List<Film> films;
	
	@Inject
	FilmDAO filmDAO;
	
	@Inject
	MovieRentalDAO movieRentalDAO;
	
	@Inject
	MovieRentalHasFilmDAO movieRentalHasFilmDAO;
	
	@PostConstruct
    public void init() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		String sql = (String) session.getAttribute("sql");
		if (sql == null) {
			sql = "SELECT f FROM Film f";
		}
		List<Film> films = filmDAO.getFilms(sql);
		setFilms(films);
    }
	
	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}
	
	
	public void addToCart(Film film) {
		User user = getUserFromRC();
		MovieRental movieRental = movieRentalDAO.getActiveMovieRental(user.getIdusers());
		if (movieRental == null) {
			movieRentalDAO.create(user);
			movieRental = movieRentalDAO.getActiveMovieRental(user.getIdusers());
			movieRentalHasFilmDAO.create(film, movieRental);
		} else {
			movieRentalHasFilmDAO.create(film, movieRental);
		}
		showMessage();	
	}

	 public void showMessage() {
		 FacesContext ctx = FacesContext.getCurrentInstance();
		 ResourceBundle txtMain = ResourceBundle.getBundle("resources.textMain", ctx.getViewRoot().getLocale());
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", txtMain.getString("succesfullAdd"));
		 PrimeFaces.current().dialog().showMessageDynamic(message);
	 }
	
	public User getUserFromRC() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		RemoteClient remoteClient = RemoteClient.load(session);
		return (User) remoteClient.getDetails();
	}
}
