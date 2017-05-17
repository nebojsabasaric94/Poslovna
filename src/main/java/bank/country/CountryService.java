package bank.country;

import java.util.List;

public interface CountryService {
	List<Country> findAll();

	Country save(Country country);

	Country findOne(Long id);
	
	public void delete(Long id);
}
