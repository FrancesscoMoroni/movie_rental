package movie_rental_jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the films database table.
 * 
 */
@Embeddable
public class FilmPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idfilms;

	@Column(name="directors_iddirectors", insertable=false, updatable=false)
	private int directorsIddirectors;

	public FilmPK() {
	}
	public int getIdfilms() {
		return this.idfilms;
	}
	public void setIdfilms(int idfilms) {
		this.idfilms = idfilms;
	}
	public int getDirectorsIddirectors() {
		return this.directorsIddirectors;
	}
	public void setDirectorsIddirectors(int directorsIddirectors) {
		this.directorsIddirectors = directorsIddirectors;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FilmPK)) {
			return false;
		}
		FilmPK castOther = (FilmPK)other;
		return 
			(this.idfilms == castOther.idfilms)
			&& (this.directorsIddirectors == castOther.directorsIddirectors);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idfilms;
		hash = hash * prime + this.directorsIddirectors;
		
		return hash;
	}
}