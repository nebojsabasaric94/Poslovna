package bank.place;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {
	private final PlaceRepository repository;

	@Autowired
	public PlaceServiceImpl(final PlaceRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Place> findAll() {
		return (List<Place>) repository.findAll();
	}

	@Override
	public Place save(Place place) {
		return repository.save(place);
	}

	@Override
	public Place findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}
}