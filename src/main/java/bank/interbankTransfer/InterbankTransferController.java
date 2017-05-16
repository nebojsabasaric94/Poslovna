package bank.interbankTransfer;

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
@RequestMapping("/interbankTransfer")
public class InterbankTransferController {

	private final InterbankTransferService interbankTransferService;
	
	

	@Autowired
	public InterbankTransferController(final InterbankTransferService service) {
		this.interbankTransferService = service;
	}

	@GetMapping
	public ResponseEntity<List<InterbankTransfer>> findAll() {
		return new ResponseEntity<>(interbankTransferService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody InterbankTransfer interbankTransfer) {
		interbankTransfer.setIdMessage(null);
		interbankTransferService.save(interbankTransfer);
	}
	
	@GetMapping("/deleteInterbankTransfer/{id}")
	public List<InterbankTransfer> deleteBank(@PathVariable Long id){
		interbankTransferService.delete(id);
		
		return interbankTransferService.findAll();
	}
	
	
}