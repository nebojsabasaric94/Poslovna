package bank.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bank.bank.Bank;
import bank.country.Country;
import bank.country.CountryService;

@RestController
@RequestMapping("/place")
public class PlaceController {

	private final PlaceService placeService;
	private final CountryService countryService;
	

	@Autowired
	public PlaceController(final PlaceService service, final CountryService countryService) {
		this.placeService = service;
		this.countryService = countryService;
	}

	@GetMapping
	public ResponseEntity<List<Place>> findAll() {
		return new ResponseEntity<>(placeService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Place place) {
		place.setId(null);
		placeService.save(place);
	}
	
	@GetMapping("/nextPlaces/{countryId}")
	public List<Place> next(@PathVariable Long countryId){
		Country country = countryService.findOne(countryId);
		List<Place> places = country.getPlaces();
		/*for(int i = 0; i < placeService.findAll().size(); i++){
			if(placeService.findAll().get(i).getCountry().getId() == country.getId()){
				places.add(placeService.findAll().get(i));
			}
		}*/
		return places;
	}
	
	@GetMapping("/deletePlace/{placeId}")
	public void deletePlace(@PathVariable Long placeId){
		placeService.delete(placeId);
	}
	
	@PostMapping("/search")
	public List<Place> search(@RequestBody Place place){
		return placeService.search(place);
	}
	
	
	@PutMapping("/update")
	public  Place update(@RequestBody Place place) {
		return placeService.save(place);
	}
	
}