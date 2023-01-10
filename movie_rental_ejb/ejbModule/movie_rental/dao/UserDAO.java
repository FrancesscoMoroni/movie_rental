package movie_rental.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import movie_rental.entities.User;

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "filmDS";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(User user) {
		em.persist(user);
	}

	public User getUserFromDatabase(String login, String pass) {
		User user = new User();
		String sql = "SELECT u FROM User u WHERE u.login = '" + login + "' AND u.password = '" + pass + "'";
		TypedQuery<User> query = em.createQuery(sql, User.class);
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	public boolean isInDatabase(String login) {
		User user = new User();
		String sql = "SELECT u FROM User u WHERE u.login = '" + login + "'";
		TypedQuery<User> query = em.createQuery(sql, User.class);
		
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if (user != null) {
			return false;
		}
		
		return true;
	}
}
