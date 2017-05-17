package bank.country;

import java.util.List;

public interface CountryService {
	public List<Country> findAll();

	public Country save(Country country);

	public void delete(Long id);

	public Country findOne(Long id);

	public List<Country> search(Country country);

}
