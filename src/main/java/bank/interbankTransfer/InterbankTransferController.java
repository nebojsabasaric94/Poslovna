package bank.interbankTransfer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
@RequestMapping("/interbankTransfer")
public class InterbankTransferController {

	private final InterbankTransferService interbankTransferService;
	
	

	@Autowired
	public InterbankTransferController(final InterbankTransferService service) {
		this.interbankTransferService = service;
	}

	@GetMapping
	public ResponseEntity<List<InterbankTransfer>> findAll() {
		return new ResponseEntity<>(interbankTransferService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/xml")
	@ResponseStatus(HttpStatus.CREATED)
	public void save() throws JAXBException {
		ArrayList<InterbankTransfer> interbankTransfers = (ArrayList<InterbankTransfer>) interbankTransferService.findAll();

		for(InterbankTransfer t : interbankTransfers){
			if(!t.getProcessed()){
				t.setProcessed(true);
				interbankTransferService.save(t);
				saveTransfersToXml(t);
			}
		}
	}
	
	@GetMapping("/deleteInterbankTransfer/{id}")
	public List<InterbankTransfer> deleteBank(@PathVariable Long id){
		interbankTransferService.delete(id);
		
		return interbankTransferService.findAll();
	}
	
	@PostMapping("/search")
	public List<InterbankTransfer> search(@RequestBody InterbankTransfer interbankTransfer){
		return interbankTransferService.search(interbankTransfer);
	}

	private void saveTransfersToXml(InterbankTransfer interbankTransfer) throws JAXBException {
		File file = new File("transfers\\"+interbankTransfer.getIdMessage()+".xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(InterbankTransfer.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(interbankTransfer, file);
		jaxbMarshaller.marshal(interbankTransfer, System.out);
	}	
}