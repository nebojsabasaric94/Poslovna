package bank.exchageRateList;

import java.util.ArrayList;
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

import bank.bank.Bank;
import bank.bank.BankService;

@RestController
@RequestMapping("/exchangeRateList")
public class ExchangeRateListController {

	private final ExchangeRateListService exchangeRateListService;
	private final BankService bankService;
	

	@Autowired
	public ExchangeRateListController(final ExchangeRateListService service, final BankService bankService) {
		this.exchangeRateListService = service;
		this.bankService = bankService;
	}

	@GetMapping
	public ResponseEntity<List<ExchangeRateList>> findAll() {
		return new ResponseEntity<>(exchangeRateListService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody ExchangeRateList exchangeRateList) {
		exchangeRateList.setId(null);
		exchangeRateListService.save(exchangeRateList);
	}
	

	@GetMapping("/deleteExchangeRateList/{id}")
	public List<ExchangeRateList> deleteBank(@PathVariable Long id){
		exchangeRateListService.delete(id);
		
		return exchangeRateListService.findAll();
	}
	
	@GetMapping("/nextExchangeRateList/{bankId}")
	public List<ExchangeRateList> getNextExhangeRateList(@PathVariable Long bankId){
		
		Bank bank = bankService.findOne(bankId);
		/*List<ExchangeRateList> exchangeRateLists = new ArrayList<ExchangeRateList>();
		for(int i = 0 ; i < exchangeRateListService.findAll().size(); i++){
			if(exchangeRateListService.findAll().get(i).getCommercialBankRate().getId() == bank.getId()){
				exchangeRateLists.add(exchangeRateListService.findAll().get(i));
			}
		}*/
		List<ExchangeRateList> exchangeRateLists = bank.getExchangeRateLists();
		
		
		return exchangeRateLists;
		
	}
	@PostMapping("/search")
	public List<ExchangeRateList> search(@RequestBody ExchangeRateList exchangeRateList){
		return exchangeRateListService.search(exchangeRateList);
	}
	
}