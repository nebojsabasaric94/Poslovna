package bank.nationalBank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bank.bank.Bank;

@RestController
@RequestMapping("/nationalBank")
public class NationalBankController {

	private final NationalBankService nationalBankService;
	//private final SelfCertificateService certificateService;

	@Autowired
	public NationalBankController(final NationalBankService nationalBankService) {
		this.nationalBankService = nationalBankService;
		//this.certificateService = certificateService;
	}

	@GetMapping
	public ResponseEntity<List<NationalBank>> findAll() {
		return new ResponseEntity<>(nationalBankService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody NationalBank nationalBank) {
		nationalBank.setId(null);
		nationalBankService.save(nationalBank);
	}
	
	@PutMapping("/update")
	public  NationalBank update(@RequestBody NationalBank Nbank) {
		return nationalBankService.save(Nbank);
	}


}
