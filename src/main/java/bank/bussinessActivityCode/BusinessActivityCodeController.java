package bank.bussinessActivityCode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bank.bank.Bank;

@RestController
@RequestMapping("/businessActivityCode")
public class BusinessActivityCodeController {

	@Autowired 
	private BusinessActivityCodeService service;
	
	@GetMapping
	public List<BusinessActivityCode> findAll(){
		
		return service.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody BusinessActivityCode bac) {
		bac.setId(null);
		service.save(bac);
	}
	
	@PostMapping("/search")
	public List<BusinessActivityCode> search(@RequestBody BusinessActivityCode businessActivityCode){
		return service.search(businessActivityCode);
	}
	
	
	@PutMapping("/update")
	public  BusinessActivityCode update(@RequestBody BusinessActivityCode b) {
		return service.save(b);
	}
}
