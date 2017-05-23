package bank.country;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {
	private final CountryRepository repository;

	@Autowired
	public CountryServiceImpl(final CountryRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Country> findAll() {
		return (List<Country>) repository.findAll();
	}

	@Override
	public Country save(Country country) {
		return repository.save(country);
	}

	@Override
	public Country findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public List<Country> search(Country country) {
		String id = "%";
		if (country.getId() != null)
			id = "" + country.getId();
		return repository.search(id, country.getName(), country.getCountry_code());
	}

}