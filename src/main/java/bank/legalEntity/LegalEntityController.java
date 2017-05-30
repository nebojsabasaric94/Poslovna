package bank.legalEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bank.bank.Bank;
import bank.bussinessActivityCode.BusinessActivityCode;
import bank.bussinessActivityCode.BusinessActivityCodeService;

@RestController
@RequestMapping("/legalEntity")
public class LegalEntityController {

	@Autowired
	private LegalEntityService service;
	
	@Autowired
	private BusinessActivityCodeService businessActivityCodeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<LegalEntity> findAll(){
		return service.findAll();
	}
	@RequestMapping(value = "/{id}/",method = RequestMethod.GET)
	public LegalEntity findOne(@PathVariable("id") Long id){
		return service.findOne(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody LegalEntity legalEntity) {
		legalEntity.setId(null);
		service.save(legalEntity);
	}
	
	@RequestMapping(value = "/{id}/",method= RequestMethod.DELETE)
	public void delete(@PathVariable("id")Long id){
		service.delete(id);
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public List<LegalEntity> search(@RequestBody LegalEntity legalEntity){
		return service.search(legalEntity);
	}
	
	@GetMapping("/nextFilterBussinesActivity/{bussinessId}")
	public List<LegalEntity> nextFilterBussinesActivity(@PathVariable Long bussinessId){
		BusinessActivityCode businessActivityCode = businessActivityCodeService.findOne(bussinessId);
		
		return businessActivityCode.getLegalEntities();
	}
}
