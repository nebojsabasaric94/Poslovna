package bank.analyticsOfStatements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyticsOfStatements")
public class AnalyticsOfStatementsController {

	private final AnalyticsOfStatementsService analyticsOfStatementsService;
	
	

	@Autowired
	public AnalyticsOfStatementsController(final AnalyticsOfStatementsService service) {
		this.analyticsOfStatementsService = service;
	}

	@GetMapping
	public ResponseEntity<List<AnalyticsOfStatements>> findAll() {
		return new ResponseEntity<>(analyticsOfStatementsService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody AnalyticsOfStatements analyticsOfStatements) {
		analyticsOfStatements.setItemNumber(null);
		analyticsOfStatementsService.save(analyticsOfStatements);
	}
	
	
}