package bank.dailyAccountBalance;

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
@RequestMapping("/dailyAccountBalance")
public class DailyAccountBalanceController {

	private final DailyAccountBalanceService dailyAccountBalanceService;
	
	

	@Autowired
	public DailyAccountBalanceController(final DailyAccountBalanceService service) {
		this.dailyAccountBalanceService = service;
	}

	@GetMapping
	public ResponseEntity<List<DailyAccountBalance>> findAll() {
		return new ResponseEntity<>(dailyAccountBalanceService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody DailyAccountBalance dailyAccountBalance) {
		dailyAccountBalanceService.save(dailyAccountBalance);
	}
	
	@GetMapping("/deleteDailyAccountBalance/{id}")
	public List<DailyAccountBalance> deleteBank(@PathVariable Long id){
		dailyAccountBalanceService.delete(id);
		
		return dailyAccountBalanceService.findAll();
	}
	
	
}