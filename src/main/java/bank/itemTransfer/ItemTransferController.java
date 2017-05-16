package bank.itemTransfer;

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
@RequestMapping("/itemTransfer")
public class ItemTransferController {

	private final ItemTransferService ItemTransferService;
	
	

	@Autowired
	public ItemTransferController(final ItemTransferService service) {
		this.ItemTransferService = service;
	}

	@GetMapping
	public ResponseEntity<List<ItemTransfer>> findAll() {
		return new ResponseEntity<>(ItemTransferService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody ItemTransfer ItemTransfer) {
		ItemTransfer.setId(null);
		ItemTransferService.save(ItemTransfer);
	}
	
	
}