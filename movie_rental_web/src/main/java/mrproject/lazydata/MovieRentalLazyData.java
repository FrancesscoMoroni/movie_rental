package mrproject.lazydata;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import movie_rental.entities.MovieRental;

public class MovieRentalLazyData extends LazyDataModel<MovieRental> {
	
	private static final long serialVersionUID = 1L;
	
	private List<MovieRental> dataSource;
	
	public void setDataSource(List<MovieRental> dataSource) {
		this.dataSource = dataSource;
	}
	public List<MovieRental> getDataSource() {
		return this.dataSource;
	}
	
	public MovieRentalLazyData(List<MovieRental> movieRentals) {
		dataSource = movieRentals;
	}
	
    public int count() {
        return (int) dataSource.stream()
                .count();
    }
	
	@Override
	public List<MovieRental> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		 // apply offset & filters
        List<MovieRental> movieRentals = dataSource.stream()
                .skip(offset)
                .limit(pageSize)
                .collect(Collectors.toList());
        setRowCount(count());
        return movieRentals;
	}
	
}
