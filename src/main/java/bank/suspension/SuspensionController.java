package bank.suspension;

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

import bank.paymentType.PaymentType;

@RestController
@RequestMapping("/suspension")
public class SuspensionController {

	private final SuspensionService suspensionService;
	
	

	@Autowired
	public SuspensionController(final SuspensionService service) {
		this.suspensionService = service;
	}

	@GetMapping
	public ResponseEntity<List<Suspension>> findAll() {
		return new ResponseEntity<>(suspensionService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Suspension suspension) {
		suspension.setId(null);
		suspensionService.save(suspension);
	}
	
	@GetMapping("/deleteSuspension/{id}")
	public List<Suspension> deleteBank(@PathVariable Long id){
		suspensionService.delete(id);
		
		return suspensionService.findAll();
	}
	
	
}