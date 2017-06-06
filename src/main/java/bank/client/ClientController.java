package bank.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bank.currency.Currency;
import bank.place.Place;
import bank.place.PlaceService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@Autowired
	private PlaceService placeService;
	
	@GetMapping
	public List<Client> findAll(){
		return service.findAll();
	}
	
	@PostMapping("/search")
	public List<Client> search(@RequestBody Client client){
		return service.search(client);
	}
	
	@GetMapping("/nextPlace/{placeId}")
	public List<Client> getNextPlace(@PathVariable Long placeId){
		Place place = placeService.findOne(placeId);
		
		return place.getClients();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Client client) {
		client.setId(null);
		service.save(client);
	}
	@PutMapping("/update")
	public  Client update(@RequestBody Client c) {
		return service.save(c);
	}
	

}
