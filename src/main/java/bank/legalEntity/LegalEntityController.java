package bank.legalEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/legalEntity")
public class LegalEntityController {

	@Autowired
	private LegalEntityService service;
}
