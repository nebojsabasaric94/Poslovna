package bank.place;

import java.util.List;

public interface PlaceService {
	public List<Place> findAll();

	public Place save(Place place);

	public Place findOne(Long id);
	
	public void delete(Long id);
	
	public List<Place> search(Place place);

	public Place findByName(String place);
}
