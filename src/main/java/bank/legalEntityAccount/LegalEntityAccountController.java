package bank.legalEntityAccount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.bank.Bank;
import bank.bank.BankService;
import bank.client.Client;
import bank.client.ClientService;
import bank.currency.Currency;
import bank.currency.CurrencyService;
import bank.legalEntity.LegalEntity;
import bank.legalEntity.LegalEntityService;

@RestController
@RequestMapping("/legalEntityAccount")
public class LegalEntityAccountController {
	
	private final LegalEntityAccountService legalEntityAccountService;
	private final BankService bankService;
	private final CurrencyService currencyService;
	private final ClientService clientService;
	private final LegalEntityService legalEntityService;
	
	@Autowired
	public LegalEntityAccountController(LegalEntityAccountService legalEntityAccountService, final BankService bankService, final CurrencyService currencyService,
			final ClientService clientService, final LegalEntityService legalEntityService) {
		this.legalEntityAccountService = legalEntityAccountService;
		this.bankService = bankService;
		this.currencyService = currencyService;
		this.clientService = clientService;
		this.legalEntityService = legalEntityService;
	}
	
	@GetMapping
	public List<LegalEntityAccount> findAll(){
		return legalEntityAccountService.findAll();
	}
	
	@GetMapping("/nextBank/{bankId}")
	public List<LegalEntityAccount> getNextBank(@PathVariable Long bankId){
		Bank bank = bankService.findOne(bankId);
		
		return bank.getLegalEntityAccount();
	}
	
	@GetMapping("/nextCurrency/{currencyId}")
	public List<LegalEntityAccount> getNextCurrency(@PathVariable Long currencyId){
		Currency currency = currencyService.findOne(currencyId);
		
		return currency.getLegalEntityAccount();
	}
	
	@GetMapping("/nextClient/{clientId}")
	public List<LegalEntityAccount> nextClient(@PathVariable Long clientId){
		Client client = clientService.findOne(clientId);
		
		return client.getLegalEntityAccount();
	}
	
	@GetMapping("/nextLegalEntity/{legalEntityId}")
	public List<LegalEntityAccount> nextLegalEntity(@PathVariable Long legalEntityId){
		LegalEntity legalEntity = legalEntityService.findOne(legalEntityId);
		
		return legalEntity.getLegalEntityAccount();
	}
	
	
	@PostMapping("/search")
	public List<LegalEntityAccount> search(@RequestBody LegalEntityAccount legalEntityAccount){
		return legalEntityAccountService.search(legalEntityAccount);
	}
	
}

