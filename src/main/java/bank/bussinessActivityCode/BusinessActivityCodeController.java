package bank.bussinessActivityCode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/businessActivityCode")
public class BusinessActivityCodeController {

	@Autowired 
	private BusinessActivityCodeService service;
	
	@GetMapping
	public List<BusinessActivityCode> findAll(){
		
		return service.findAll();
	}
	
	@PostMapping("/search")
	public List<BusinessActivityCode> search(@RequestBody BusinessActivityCode businessActivityCode){
		return service.search(businessActivityCode);
	}
}
