package bank.legalEntityAccount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/legalEntityAccount")
public class LegalEntityAccountController {

	@Autowired
	private LegalEntityAccountService service;
	
	@GetMapping
	public List<LegalEntityAccount> findAll(){
		return service.findAll();
	}
	
	@PostMapping("/search")
	public List<LegalEntityAccount> search(@RequestBody LegalEntityAccount legalEntityAccount){
		return service.search(legalEntityAccount);
	}
}
