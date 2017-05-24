package bank.itemTransfer;

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

import bank.analyticsOfStatements.AnalyticsOfStatements;
import bank.analyticsOfStatements.AnalyticsOfStatementsService;
import bank.interbankTransfer.InterbankTransfer;
import bank.interbankTransfer.InterbankTransferService;

@RestController
@RequestMapping("/itemTransfer")
public class ItemTransferController {

	private final ItemTransferService itemTransferService;
	private final InterbankTransferService interbankTransferService;
	private final AnalyticsOfStatementsService analyticsOfStatementsService;

	@Autowired
	public ItemTransferController(final ItemTransferService service, final InterbankTransferService interbankTransferService, final AnalyticsOfStatementsService analyticsOfStatementsService) {
		this.itemTransferService = service;
		this.interbankTransferService = interbankTransferService;
		this.analyticsOfStatementsService = analyticsOfStatementsService;
	}

	@GetMapping
	public ResponseEntity<List<ItemTransfer>> findAll() {
		return new ResponseEntity<>(itemTransferService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody ItemTransfer ItemTransfer) {
		ItemTransfer.setId(null);
		itemTransferService.save(ItemTransfer);
	}
	
	@GetMapping("/deleteItemTransfer/{id}")
	public List<ItemTransfer> deleteBank(@PathVariable Long id){
		itemTransferService.delete(id);
		
		return itemTransferService.findAll();
	}
	
	@PostMapping("/search")
	public List<ItemTransfer> search(@RequestBody ItemTransfer itemTransfer){
		return itemTransferService.search(itemTransfer);
	}
	
	@GetMapping("/nextInterbankTransfer/{interbankTransferId}")
	public List<ItemTransfer> nextInterbankTransfer(@PathVariable Long interbankTransferId){
		InterbankTransfer interbankTransfer = interbankTransferService.findOne(interbankTransferId);
		
		return interbankTransfer.getItemTransfers();
	}
	
	@GetMapping("/nextAnalyticsOfStatements/{analyticsOfStatementsId}")
	public List<ItemTransfer> nextAnalyticsOfStatements(@PathVariable Long analyticsOfStatementsId){
		AnalyticsOfStatements analyticsOfStatements = analyticsOfStatementsService.findOne(analyticsOfStatementsId);
		
		return analyticsOfStatements.getItemTransfer();
	}
}