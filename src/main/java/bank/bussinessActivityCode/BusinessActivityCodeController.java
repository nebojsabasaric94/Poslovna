package bank.bussinessActivityCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessActivityCodeController {

	@Autowired 
	private BusinessActivityCodeService service;
}
