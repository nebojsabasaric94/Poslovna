package bank.place;

import java.util.List;

public interface PlaceService {
	List<Place> findAll();

	Place save(Place place);

	Place findOne(Long id);
}
