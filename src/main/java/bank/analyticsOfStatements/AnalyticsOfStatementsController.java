package bank.analyticsOfStatements;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.currency.Currency;
import bank.currency.CurrencyService;
import bank.dailyAccountBalance.DailyAccountBalance;
import bank.dailyAccountBalance.DailyAccountBalanceService;
import bank.interbankTransfer.InterbankTransfer;
import bank.interbankTransfer.InterbankTransferService;
import bank.interbankTransfer.Transfers;
import bank.itemTransfer.ItemTransfer;
import bank.legalEntityAccount.LegalEntityAccount;
import bank.legalEntityAccount.LegalEntityAccountService;
import bank.paymentType.PaymentType;
import bank.paymentType.PaymentTypeService;
import bank.place.Place;
import bank.place.PlaceService;

@RestController
@RequestMapping("/analyticsOfStatements")
public class AnalyticsOfStatementsController {

	private final AnalyticsOfStatementsService analyticsOfStatementsService;
	private final PlaceService placeService;
	private final CurrencyService currencyService;
	private final DailyAccountBalanceService dailyAccountBalanceService;
	private final PaymentTypeService paymentTypeService;
	private final LegalEntityAccountService legalEntityAccountService;
	private final InterbankTransferService interbankTransferService;
	@Autowired
	public AnalyticsOfStatementsController(final AnalyticsOfStatementsService service, final PlaceService placeService, final CurrencyService currencyService, 
			final DailyAccountBalanceService dailyAccountBalanceService, final PaymentTypeService paymentTypeService,
			final LegalEntityAccountService legalEntityAccountService,final InterbankTransferService interbankTransferService) {
		this.analyticsOfStatementsService = service;
		this.placeService = placeService;
		this.currencyService = currencyService;
		this.dailyAccountBalanceService = dailyAccountBalanceService;
		this.paymentTypeService = paymentTypeService;
		this.legalEntityAccountService = legalEntityAccountService;
		this.interbankTransferService = interbankTransferService;
	}

	@GetMapping
	public List<AnalyticsOfStatements> findAll() {
		return analyticsOfStatementsService.findAll();
	}
	
	@GetMapping("/xml")
	public void loadXML() throws JAXBException{
		File file = new File("analytic.xml");
		getStatements(file);
	}
	
	private Transfers getTransfers(File file) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(Transfers.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		jaxbUnmarshaller.setEventHandler(new MyValidationEventHandler());
		Transfers list = (Transfers) jaxbUnmarshaller.unmarshal(file);
		
		return list;
	}
	private Statements getStatements(File file) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(Statements.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		jaxbUnmarshaller.setEventHandler(new MyValidationEventHandler());
		Statements list = (Statements) jaxbUnmarshaller.unmarshal(file);
		
		for(int i =0; i < list.getAnalyticsOfStatements().size()-1;i++){
			for(int j= i+1;j<list.getAnalyticsOfStatements().size();j++){
				if(list.getAnalyticsOfStatements().get(i).getCurrencyDate().after(list.getAnalyticsOfStatements().get(j).getCurrencyDate()))
					Collections.swap(list.getAnalyticsOfStatements(), i, j);
			}
		}
		//analyticsOfStatementsService.findAll().size();
		for(int i = analyticsOfStatementsService.findAll().size() ; i < list.getAnalyticsOfStatements().size();i++){
			AnalyticsOfStatements a = analyticsOfStatementsService.save(list.getAnalyticsOfStatements().get(i));
			
			if(a.getPaymentType().getNameOfPaymentType().equals("Nalog za uplatu")){
				String creditorAccountNumber = a.getAccountCreditor();
				LegalEntityAccount creditorAccount = legalEntityAccountService.findByAccountNumber(creditorAccountNumber);
				DailyAccountBalance dailyAccountBalance = dailyAccountBalanceService.findByAccountNumberAndDate(creditorAccount,a.getCurrencyDate());
				dailyAccountBalance.setTrafficToBenefit(dailyAccountBalance.getTrafficToBenefit()+a.getSum());
				dailyAccountBalance.setNewState(dailyAccountBalance.getPreviousState()+dailyAccountBalance.getTrafficToBenefit()-dailyAccountBalance.getTrafficToTheBurden());
				dailyAccountBalance.getAnalyticsOfStatements().add(a);
				
				dailyAccountBalanceService.save(dailyAccountBalance);
			}
			else if(a.getPaymentType().getNameOfPaymentType().equals("Nalog za isplatu")){
				String debtorAccountNumber = a.getDebtorAccount();
				LegalEntityAccount debtorAccount = legalEntityAccountService.findByAccountNumber(debtorAccountNumber);
				DailyAccountBalance dailyAccountBalance = dailyAccountBalanceService.findByAccountNumberAndDate(debtorAccount, a.getCurrencyDate());
				dailyAccountBalance.setTrafficToTheBurden(dailyAccountBalance.getTrafficToTheBurden() + a.getSum());
				dailyAccountBalance.setNewState(dailyAccountBalance.getPreviousState()+dailyAccountBalance.getTrafficToBenefit()-dailyAccountBalance.getTrafficToTheBurden());
				dailyAccountBalance.getAnalyticsOfStatements().add(a);
				dailyAccountBalanceService.save(dailyAccountBalance);
			}else if(a.getPaymentType().getNameOfPaymentType().equals("Nalog za prenos")){
				
				String debtorAccountNumber = a.getDebtorAccount();
				LegalEntityAccount debtorAccount = legalEntityAccountService.findByAccountNumber(debtorAccountNumber);
				String creditorAccountNumber = a.getAccountCreditor();
				LegalEntityAccount creditorAccount = legalEntityAccountService.findByAccountNumber(creditorAccountNumber);
				
				
				if(debtorAccount.getBank().equals(creditorAccount.getBank())){
					//ako su iz iste banke skini sa racuna uplatioca i stavi na racun primaoca, sacuvaj stanje za taj dan
					DailyAccountBalance dailyAccountBalanceDebtor = dailyAccountBalanceService.findByAccountNumberAndDate(debtorAccount, a.getCurrencyDate());
					dailyAccountBalanceDebtor.setTrafficToTheBurden(dailyAccountBalanceDebtor.getTrafficToTheBurden() + a.getSum());
					dailyAccountBalanceDebtor.setNewState(dailyAccountBalanceDebtor.getPreviousState()+dailyAccountBalanceDebtor.getTrafficToBenefit()-dailyAccountBalanceDebtor.getTrafficToTheBurden());
					dailyAccountBalanceDebtor.getAnalyticsOfStatements().add(a);
					dailyAccountBalanceService.save(dailyAccountBalanceDebtor);
					
					DailyAccountBalance dailyAccountBalanceCreditor = dailyAccountBalanceService.findByAccountNumberAndDate(creditorAccount, a.getCurrencyDate());
					dailyAccountBalanceCreditor.setTrafficToBenefit(dailyAccountBalanceCreditor.getTrafficToBenefit()+a.getSum());
					dailyAccountBalanceCreditor.setNewState(dailyAccountBalanceCreditor.getPreviousState()+dailyAccountBalanceCreditor.getTrafficToBenefit()-dailyAccountBalanceCreditor.getTrafficToTheBurden());
					dailyAccountBalanceCreditor.getAnalyticsOfStatements().add(a);
					dailyAccountBalanceService.save(dailyAccountBalanceCreditor);
					
					
				}
				else{
					//ako nisu iz iste banke,skini sa racuna uplatioca i napravi odgovarajucu poruku(MT102 ili MT103)
					if(a.getEmergency() || a.getSum() >= 250000){
						//ako je rtgs odmah skini novac sa racuna,prebaci novac na racun, izvezi u xml
						DailyAccountBalance dailyAccountBalanceDebtor = dailyAccountBalanceService.findByAccountNumberAndDate(debtorAccount, a.getCurrencyDate());
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
						
						
					}
					else{
						DailyAccountBalance dailyAccountBalance = dailyAccountBalanceService.findByAccountNumberAndDate(debtorAccount, a.getCurrencyDate());
						dailyAccountBalance.setTrafficToTheBurden(dailyAccountBalance.getTrafficToTheBurden() + a.getSum());
						dailyAccountBalance.setNewState(dailyAccountBalance.getPreviousState()+dailyAccountBalance.getTrafficToBenefit()-dailyAccountBalance.getTrafficToTheBurden());
						dailyAccountBalance.getAnalyticsOfStatements().add(a);
						dailyAccountBalanceService.save(dailyAccountBalance);
						
						InterbankTransfer mt102 = interbankTransferService.findByDateAndBanks(a.getCurrencyDate(),debtorAccount.getBank(),creditorAccount.getBank());
						mt102.setSum(mt102.getSum()+a.getSum());
						ItemTransfer itemTransfer = new ItemTransfer();
						itemTransfer.setAnalyticsOfStatements(a);
						itemTransfer.setInterbankTransfer(mt102);
						mt102.getItemTransfers().add(itemTransfer);
						
						interbankTransferService.save(mt102);
					}
				}			
			}
			//analyticsOfStatementsService.save(list.getAnalyticsOfStatements().get(i));
		}
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
	
	@PostMapping("/search")
	public List<AnalyticsOfStatements> search(@RequestBody AnalyticsOfStatements analyticsOfStatements){
		
		return analyticsOfStatementsService.search(analyticsOfStatements);
	}
	
	@GetMapping("/nextPlace/{placeId}")
	public List<AnalyticsOfStatements> getNextPlace(@PathVariable Long placeId){
		Place place = placeService.findOne(placeId);
		
		return place.getAnalyticsOfStatements();
	}
	
	@GetMapping("/nextCurrency/{currencyId}")
	public List<AnalyticsOfStatements> nextCurrency(@PathVariable Long currencyId){
		Currency currency = currencyService.findOne(currencyId);
		
		return currency.getAnalyticsOfStatements();
	}
	
	@GetMapping("/nextDailyAccountBalance/{dailyAccountBalanceId}")
	public List<AnalyticsOfStatements> nextDailyAccountBalance(@PathVariable Long dailyAccountBalanceId){
		DailyAccountBalance dailyAccountBalance = dailyAccountBalanceService.findOne(dailyAccountBalanceId);
		
		return dailyAccountBalance.getAnalyticsOfStatements();
	}
	
	@GetMapping("/nextPaymentType/{paymentTypeId}")
	public List<AnalyticsOfStatements> nextPaymentType(@PathVariable Long paymentTypeId){
		PaymentType paymentType = paymentTypeService.findOne(paymentTypeId);
	
		return paymentType.getAnalyticsOfStatements();
	}
	
}