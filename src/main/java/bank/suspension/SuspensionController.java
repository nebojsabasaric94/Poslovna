package bank.suspension;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bank.analyticsOfStatements.AnalyticsOfStatements;
import bank.analyticsOfStatements.AnalyticsOfStatementsService;
import bank.analyticsOfStatements.MyValidationEventHandler;
import bank.dailyAccountBalance.DailyAccountBalance;
import bank.dailyAccountBalance.DailyAccountBalanceService;
import bank.interbankTransfer.InterbankTransfer;
import bank.interbankTransfer.InterbankTransferService;
import bank.interbankTransfer.Transfers;
import bank.itemTransfer.ItemTransfer;
import bank.legalEntity.LegalEntity;
import bank.legalEntityAccount.LegalEntityAccount;
import bank.legalEntityAccount.LegalEntityAccountService;
import bank.paymentType.PaymentTypeService;

@RestController
@RequestMapping("/suspension")
public class SuspensionController {

	private final SuspensionService suspensionService;
	private final LegalEntityAccountService legalEntityAccountService;
	private final DailyAccountBalanceService dailyAccountBalanceService;
	private final PaymentTypeService paymentTypeService;
	private final InterbankTransferService interbankTransferService;
	private final AnalyticsOfStatementsService analyticsOfStatementsService;
	@Autowired
	public SuspensionController(final SuspensionService service,final LegalEntityAccountService legalEntityAccountService,
			final DailyAccountBalanceService dailyAccountBalanceService,final PaymentTypeService paymentTypeService,final InterbankTransferService interbankTransferService,
			final AnalyticsOfStatementsService analyticsOfStatementsService) {
		this.suspensionService = service;
		this.legalEntityAccountService = legalEntityAccountService;
		this.dailyAccountBalanceService = dailyAccountBalanceService;
		this.paymentTypeService = paymentTypeService;
		this.interbankTransferService = interbankTransferService;
		this.analyticsOfStatementsService = analyticsOfStatementsService;
	}

	@GetMapping
	public ResponseEntity<List<Suspension>> findAll() {
		return new ResponseEntity<>(suspensionService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Suspension suspension) throws JAXBException {
		LegalEntityAccount creditorAccount = legalEntityAccountService.findByAccountNumber(suspension.getTransferToAccount());
		LegalEntityAccount debtorAccount = legalEntityAccountService.findOne(suspension.getLegalEntityAccount().getId());
		suspension.setLegalEntityAccount(debtorAccount);
		DailyAccountBalance dailyAccountBalanceDebtor = dailyAccountBalanceService.findByAccountNumberAndDate(suspension.getLegalEntityAccount(),new java.util.Date());
		
		AnalyticsOfStatements a = generateAnalitycsOfStatemnt(suspension, creditorAccount, dailyAccountBalanceDebtor.getPreviousState());
		analyticsOfStatementsService.save(a);
		String debtorCode = debtorAccount.getBrojRacuna().substring(0,3);
		String creditorCode = creditorAccount.getBrojRacuna().substring(0,3);		
		if(creditorCode.equals(debtorCode)){
			//ako su iz iste banke skini sa racuna uplatioca i stavi na racun primaoca, sacuvaj stanje za taj dan
			dailyAccountBalanceDebtor.setTrafficToTheBurden(dailyAccountBalanceDebtor.getTrafficToTheBurden() + a.getSum());
			dailyAccountBalanceDebtor.setNewState(dailyAccountBalanceDebtor.getPreviousState()+dailyAccountBalanceDebtor.getTrafficToBenefit()-dailyAccountBalanceDebtor.getTrafficToTheBurden());
			dailyAccountBalanceDebtor.getAnalyticsOfStatements().add(a);
			dailyAccountBalanceService.save(dailyAccountBalanceDebtor);
			
			DailyAccountBalance dailyAccountBalanceCreditor = dailyAccountBalanceService.findByAccountNumberAndDate(creditorAccount, a.getCurrencyDate());
			dailyAccountBalanceCreditor.setTrafficToBenefit(dailyAccountBalanceCreditor.getTrafficToBenefit()+a.getSum());
			dailyAccountBalanceCreditor.setNewState(dailyAccountBalanceCreditor.getPreviousState()+dailyAccountBalanceCreditor.getTrafficToBenefit()-dailyAccountBalanceCreditor.getTrafficToTheBurden());
			dailyAccountBalanceCreditor.getAnalyticsOfStatements().add(a);
			dailyAccountBalanceService.save(dailyAccountBalanceCreditor);
			
			debtorAccount.setVazeci(false);
			legalEntityAccountService.save(debtorAccount);
		}
		else{
			//ako nisu iz iste banke,skini sa racuna uplatioca i napravi odgovarajucu poruku(MT102 ili MT103)
			if(a.getSum() >= 250000){
				//ako je rtgs odmah skini novac sa racuna,prebaci novac na racun, izvezi u xml
				dailyAccountBalanceDebtor.setTrafficToTheBurden(dailyAccountBalanceDebtor.getTrafficToTheBurden() + a.getSum());
				dailyAccountBalanceDebtor.setNewState(dailyAccountBalanceDebtor.getPreviousState()+dailyAccountBalanceDebtor.getTrafficToBenefit()-dailyAccountBalanceDebtor.getTrafficToTheBurden());
				dailyAccountBalanceDebtor.getAnalyticsOfStatements().add(a);
				dailyAccountBalanceService.save(dailyAccountBalanceDebtor);
				
				DailyAccountBalance dailyAccountBalanceCreditor = dailyAccountBalanceService.findByAccountNumberAndDate(creditorAccount, a.getCurrencyDate());
				dailyAccountBalanceCreditor.setTrafficToBenefit(dailyAccountBalanceCreditor.getTrafficToBenefit()+a.getSum());
				dailyAccountBalanceCreditor.setNewState(dailyAccountBalanceCreditor.getPreviousState()+dailyAccountBalanceCreditor.getTrafficToBenefit()-dailyAccountBalanceCreditor.getTrafficToTheBurden());
				dailyAccountBalanceCreditor.getAnalyticsOfStatements().add(a);
				dailyAccountBalanceService.save(dailyAccountBalanceCreditor);
				
				InterbankTransfer mt103 = new InterbankTransfer();
				mt103.setBank(creditorAccount.getBank());
				mt103.setSenderBank(debtorAccount.getBank());
				mt103.setDate(a.getCurrencyDate());
				
				ItemTransfer itemTransfer = new ItemTransfer();
				itemTransfer.setAnalyticsOfStatements(a);
				itemTransfer.setInterbankTransfer(mt103);
				
				mt103.setItemTransfers(new ArrayList<ItemTransfer>());
				mt103.getItemTransfers().add(itemTransfer);
				mt103.setSum(a.getSum());
				mt103.setTypeOfMessage("MT103");
				mt103.setProcessed(true);
				saveTransfers(mt103);
				interbankTransferService.save(mt103);
				debtorAccount.setVazeci(false);
				legalEntityAccountService.save(debtorAccount);
				
			}
			else{
				dailyAccountBalanceDebtor.setTrafficToTheBurden(dailyAccountBalanceDebtor.getTrafficToTheBurden() + a.getSum());
				dailyAccountBalanceDebtor.setNewState(dailyAccountBalanceDebtor.getPreviousState()+dailyAccountBalanceDebtor.getTrafficToBenefit()-dailyAccountBalanceDebtor.getTrafficToTheBurden());
				dailyAccountBalanceDebtor.getAnalyticsOfStatements().add(a);
				dailyAccountBalanceService.save(dailyAccountBalanceDebtor);
				
				InterbankTransfer mt102 = interbankTransferService.findByDateAndBanks(a.getCurrencyDate(),debtorAccount.getBank(),creditorAccount.getBank());
				mt102.setSum(mt102.getSum()+a.getSum());
				ItemTransfer itemTransfer = new ItemTransfer();
				itemTransfer.setAnalyticsOfStatements(a);
				itemTransfer.setInterbankTransfer(mt102);
				mt102.getItemTransfers().add(itemTransfer);
				
				interbankTransferService.save(mt102);
				debtorAccount.setVazeci(false);
				legalEntityAccountService.save(debtorAccount);
			}
		}		
		
		suspensionService.save(suspension);
	}
	
	@GetMapping("/deleteSuspension/{id}")
	public List<Suspension> deleteBank(@PathVariable Long id){
		suspensionService.delete(id);
		
		return suspensionService.findAll();
	}
	
	@PostMapping("/search")
	public List<Suspension> search(@RequestBody Suspension suspension){
		return suspensionService.search(suspension);
	}


	private Transfers getTransfers(File file) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(Transfers.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		jaxbUnmarshaller.setEventHandler(new MyValidationEventHandler());
		Transfers list = (Transfers) jaxbUnmarshaller.unmarshal(file);
		
		return list;
	}
	
	private void saveTransfers(InterbankTransfer interbankTransfer) throws JAXBException {
		Transfers transfers = getTransfers(new File("transfers.xml"));
		transfers.getInterbankTransfers().add(interbankTransfer);
		File file = new File("transfers.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Transfers.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(transfers, file);
		jaxbMarshaller.marshal(transfers, System.out);
	}	
	
	private AnalyticsOfStatements generateAnalitycsOfStatemnt(Suspension suspension,LegalEntityAccount creditorAccount,Float sum){
		AnalyticsOfStatements a = new AnalyticsOfStatements();
		a.setAccountCreditor(creditorAccount.getBrojRacuna());
		String creditor = "";
		if(creditorAccount.getClient() instanceof LegalEntity)
			creditor = ((LegalEntity)creditorAccount.getClient()).getNaziv_klijenta();
		else
			creditor = creditorAccount.getClient().getFirstName() + creditorAccount.getClient().getLastName();
		a.setCreditor_recipient(creditor);
		a.setCurrencyDate(new Date(new java.util.Date().getTime()));
		a.setDateOfReceipt(new Date(new java.util.Date().getTime()));
		String debtor = "";
		if(suspension.getLegalEntityAccount().getClient() instanceof LegalEntity)
			debtor = ((LegalEntity)suspension.getLegalEntityAccount().getClient()).getNaziv_klijenta();
		else
			debtor = suspension.getLegalEntityAccount().getClient().getFirstName() + creditorAccount.getClient().getLastName();		
		
		a.setDebtor_originator(debtor);
		a.setDebtorAccount(suspension.getLegalEntityAccount().getBrojRacuna());
		a.setEmergency(false);
		a.setModelApproval(97);
		a.setPaymentCurrency(suspension.getLegalEntityAccount().getCurrency());
		a.setPaymentType(paymentTypeService.findOne((long) 1));
		a.setPlace(suspension.getLegalEntityAccount().getClient().getResidence());
		a.setPurposeOfPayment("Gasenje racuna");
		a.setReferenceNumberCreditor("4547121");
		a.setSum(sum);
		return a;
	}
	
	
	@PutMapping("/update")
	public  Suspension update(@RequestBody Suspension s) {
		return suspensionService.save(s);
	}
	
}