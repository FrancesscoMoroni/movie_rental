package mrproject.main;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import movie_rental.dao.FilmDAO;

@Named
@RequestScoped
public class MainSearchBB {
	private String name;
	private String sort;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSort() {
		return sort;
	}
	
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Inject
	FilmDAO filmDAO;
	
	@Inject
	MainFilmsBB mainFilmsBB;
	
	public void filter() {
		String sql = "SELECT f FROM Film f";
		
		if (name != null) {
			sql += " WHERE f.name LIKE '" + name + "%'";
		}
		
		if (sort != null) {
			sql += " ORDER BY " + sort;
		}
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("sql", sql);
	}
}
