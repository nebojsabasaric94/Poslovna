package bank.legalEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/legalEntity")
public class LegalEntityController {

	@Autowired
	private LegalEntityService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<LegalEntity> findAll(){
		return service.findAll();
	}
	@RequestMapping(value = "/{id}/",method = RequestMethod.GET)
	public LegalEntity findOne(@PathVariable("id") Long id){
		return service.findOne(id);
	}
	
	@RequestMapping(value = "/{id}/",method= RequestMethod.DELETE)
	public void delete(@PathVariable("id")Long id){
		service.delete(id);
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public List<LegalEntity> search(@RequestBody LegalEntity legalEntity){
		return service.search(legalEntity);
	}
}
