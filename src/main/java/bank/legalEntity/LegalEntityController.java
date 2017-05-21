package bank.legalEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/legalEntity")
public class LegalEntityController {

	@Autowired
	private LegalEntityService service;
	
	@GetMapping
	public List<LegalEntity> findAll(){
		return service.findAll();
	}
	
	@PostMapping("/search")
	public List<LegalEntity> search(@RequestBody LegalEntity legalEntity){
		return service.search(legalEntity);
	}
}
