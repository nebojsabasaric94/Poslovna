package bank.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {

	private final CountryService countryService;
	
	@Autowired
	public CountryController(final CountryService service) {
		this.countryService = service;
	}

	@GetMapping
	public ResponseEntity<List<Country>> findAll() {
		return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Country country) {
		country.setId(null);
		countryService.save(country);
	}
	

	@GetMapping("/deleteCountry/{countryId}")
	public void deletePlace(@PathVariable Long countryId){
		countryService.delete(countryId);
	}
	
	

	@PostMapping("/search")
	public List<Country> search(@RequestBody Country country){
		return countryService.search(country);
	}

}