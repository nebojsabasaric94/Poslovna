package bank.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place")
public class PlaceController {

	private final PlaceService placeService;
	
	

	@Autowired
	public PlaceController(final PlaceService service) {
		this.placeService = service;
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
	
	
}