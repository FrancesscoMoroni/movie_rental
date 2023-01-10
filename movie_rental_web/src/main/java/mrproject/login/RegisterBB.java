package mrproject.login;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import movie_rental.dao.UserDAO;
import movie_rental.entities.User;

@Named
@RequestScoped
public class RegisterBB {
	private static final String PAGE_MAIN = "/pages/main_page/main_page?faces-redirect=true";
	private static final String PAGE_LOGIN = "/pages/login_page/login_page";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private String login;
	private String pass;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	LoginBB loginBB;
	
	public String doRegister() {
		User user = new User(login, pass, "user");
		if (userDAO.isInDatabase(login)) {
			try {
				userDAO.create(user);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			loginBB.storeClient(user);
			
			return PAGE_MAIN;
		}
		FacesContext ctx = FacesContext.getCurrentInstance();
		ResourceBundle txtRegister = ResourceBundle.getBundle("resources.textRegister", ctx.getViewRoot().getLocale());
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				txtRegister.getString("accountExist"), null));
		
		return null;
	}
}
