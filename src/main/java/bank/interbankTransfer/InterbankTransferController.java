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

import bank.analyticsOfStatements.AnalyticsOfStatements;
import bank.analyticsOfStatements.AnalyticsOfStatementsXml;

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

	@GetMapping("/next/{id}")
	public List<InterbankTransfer> findNext(@PathVariable Long id) {

		List<InterbankTransfer> list = new ArrayList<InterbankTransfer>();
		for (int i = 0; i < interbankTransferService.findAll().size(); i++) {
			if (interbankTransferService.findAll().get(i).getBank().getId() == id) {
				list.add(interbankTransferService.findAll().get(i));
			}
		}
		return list;
	}

	@PostMapping("/xml")
	@ResponseStatus(HttpStatus.CREATED)
	public void save() throws JAXBException {
		ArrayList<InterbankTransfer> interbankTransfers = (ArrayList<InterbankTransfer>) interbankTransferService
				.findAll();

		for (InterbankTransfer t : interbankTransfers) {
			if (!t.getProcessed()) {
				t.setProcessed(true);
				interbankTransferService.save(t);
				saveTransfersToXml(t);
			}
		}
	}

	@GetMapping("/deleteInterbankTransfer/{id}")
	public List<InterbankTransfer> deleteBank(@PathVariable Long id) {
		interbankTransferService.delete(id);

		return interbankTransferService.findAll();
	}

	@PostMapping("/search")
	public List<InterbankTransfer> search(@RequestBody InterbankTransfer interbankTransfer) {
		return interbankTransferService.search(interbankTransfer);
	}

	private void saveTransfersToXml(InterbankTransfer interbankTransfer) throws JAXBException {
		InterbankTransferXml xml = generateInterBankTransferXml(interbankTransfer);

		File file = new File("transfers\\" + interbankTransfer.getIdMessage() + ".xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(InterbankTransferXml.class);

		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(xml, file);
		jaxbMarshaller.marshal(xml, System.out);
	}

	private InterbankTransferXml generateInterBankTransferXml(InterbankTransfer interbankTransfer) {
		InterbankTransferXml xml = new InterbankTransferXml();
		xml.setDate(interbankTransfer.getDate());
		xml.setRecieverBankCode(interbankTransfer.getBank().getBankCode());
		xml.setSenderBankCode(interbankTransfer.getSenderBank().getBankCode());
		xml.setSum(interbankTransfer.getSum());
		xml.setTypeOfMessage(interbankTransfer.getTypeOfMessage());
		xml.setStatements(new ArrayList<>());
		for (int i = 0; i < interbankTransfer.getItemTransfers().size(); i++) {
			AnalyticsOfStatements a = interbankTransfer.getItemTransfers().get(i).getAnalyticsOfStatements();
			AnalyticsOfStatementsXml statementsXml = new AnalyticsOfStatementsXml();
			statementsXml.setAccountCreditor(a.getAccountCreditor());
			statementsXml.setCreditor_recipient(a.getCreditor_recipient());
			statementsXml.setCurrencyDate(a.getCurrencyDate());
			statementsXml.setDateOfReceipt(a.getDateOfReceipt());
			statementsXml.setDebtor_originator(a.getDebtor_originator());
			statementsXml.setDebtorAccount(a.getDebtorAccount());
			statementsXml.setEmergency(a.isEmergency());
			statementsXml.setModelApproval(a.getModelApproval());
			statementsXml.setModelAssigments(a.getModelAssigments());
			statementsXml.setPaymentCurrency(a.getPaymentCurrency().getOfficial_code());
			statementsXml.setPaymentType(a.getPaymentType().getNameOfPaymentType());
			statementsXml.setPlace(a.getPlace().getName());
			statementsXml.setPurposeOfPayment(a.getPurposeOfPayment());
			statementsXml.setReferenceNumberAssigments(a.getReferenceNumberAssigments());
			statementsXml.setReferenceNumberCreditor(a.getReferenceNumberCreditor());
			statementsXml.setStatus(a.getStatus());
			statementsXml.setSum(a.getSum());
			statementsXml.setTypeOfMistake(a.getTypeOfMistake());

			xml.getStatements().add(statementsXml);
		}
		return xml;
	}
}