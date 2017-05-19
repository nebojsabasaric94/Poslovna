package bank.paymentType;

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

@RestController
@RequestMapping("/paymentType")
public class PaymentTypeController {

	private final PaymentTypeService paymentTypeService;
	
	

	@Autowired
	public PaymentTypeController(final PaymentTypeService service) {
		this.paymentTypeService = service;
	}

	@GetMapping
	public ResponseEntity<List<PaymentType>> findAll() {
		return new ResponseEntity<>(paymentTypeService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody PaymentType paymentType) {
		paymentTypeService.save(paymentType);
	}
	
	@GetMapping("/deletePaymentType/{id}")
	public List<PaymentType> deleteBank(@PathVariable Long id){
		paymentTypeService.delete(id);
		
		return paymentTypeService.findAll();
	}
	
	@PostMapping("/search")
	public List<PaymentType> search(@RequestBody PaymentType paymentType){
		return paymentTypeService.search(paymentType);
	}
	
}