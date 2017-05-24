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

import bank.legalEntityAccount.LegalEntityAccount;
import bank.legalEntityAccount.LegalEntityAccountService;

@RestController
@RequestMapping("/dailyAccountBalance")
public class DailyAccountBalanceController {

	private final DailyAccountBalanceService dailyAccountBalanceService;
	private final LegalEntityAccountService legalEntityAccountService;
	

	@Autowired
	public DailyAccountBalanceController(final DailyAccountBalanceService service, final LegalEntityAccountService legalEntityAccountService) {
		this.dailyAccountBalanceService = service;
		this.legalEntityAccountService = legalEntityAccountService;
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
	
	@GetMapping("/nextLegalEntityAccount/{legalEntityAccountId}")
	public List<DailyAccountBalance> nextLegalEntityAccount(@PathVariable Long legalEntityAccountId){
		LegalEntityAccount legalEntityAccount = legalEntityAccountService.findOne(legalEntityAccountId);
		
		return legalEntityAccount.getDailyAccountBalances();
	}
	
	@PostMapping("/search")
	public List<DailyAccountBalance> search(@RequestBody DailyAccountBalance dailyAccountBalance){
		return dailyAccountBalanceService.search(dailyAccountBalance);
	}
}