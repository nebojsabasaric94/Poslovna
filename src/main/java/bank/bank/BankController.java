package bank.bank;

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

@RestController
@RequestMapping("/bank")
public class BankController {

	private final BankService bankService;
	
	

	@Autowired
	public BankController(final BankService service) {
		this.bankService = service;
	}

	@GetMapping
	public ResponseEntity<List<Bank>> findAll() {
		return new ResponseEntity<>(bankService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Bank bank) {
		bank.setId(null);
		bankService.save(bank);
	}
	
	@GetMapping("/deleteBank/{id}")
	public List<Bank> deleteBank(@PathVariable Long id){
		bankService.delete(id);
		
		return bankService.findAll();
	}
	
	@PostMapping("/search")
	public List<Bank> search(@RequestBody Bank bank){
		return bankService.search(bank);
	}
	
	@PutMapping("/update")
	public  Bank update(@RequestBody Bank bank) {
		return bankService.save(bank);
	}
	
	
}