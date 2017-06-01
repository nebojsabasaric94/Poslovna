package bank.analyticsOfStatements;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bank.currency.Currency;
import bank.currency.CurrencyService;
import bank.dailyAccountBalance.DailyAccountBalance;
import bank.dailyAccountBalance.DailyAccountBalanceService;
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

	@Autowired
	public AnalyticsOfStatementsController(final AnalyticsOfStatementsService service, final PlaceService placeService, final CurrencyService currencyService, 
			final DailyAccountBalanceService dailyAccountBalanceService, final PaymentTypeService paymentTypeService) {
		this.analyticsOfStatementsService = service;
		this.placeService = placeService;
		this.currencyService = currencyService;
		this.dailyAccountBalanceService = dailyAccountBalanceService;
		this.paymentTypeService = paymentTypeService;
	}

	@GetMapping
	public List<AnalyticsOfStatements> findAll() throws JAXBException {
		//return new ResponseEntity<>(analyticsOfStatementsService.findAll(), HttpStatus.OK);
		File file = new File("analytic.xml");
		/*JAXBContext jaxbContext = JAXBContext.newInstance(AnalyticsOfStatements.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller(); //unmarshaller
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(new File("C:\\accountStatement.xsd"));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jaxbUnmarshaller.setSchema(schema);*/
		Statements list = getStatements(file);
		
		
		//AnalyticsOfStatements mt102 = (AnalyticsOfStatements) jaxbUnmarshaller.unmarshal(file);
		//System.out.println(mt102);
		return list.getAnalyticsOfStatements();
	}
	
	private Statements getStatements(File file) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(Statements.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		jaxbUnmarshaller.setEventHandler(new MyValidationEventHandler());
		Statements list = (Statements) jaxbUnmarshaller.unmarshal(file);
		for(int i = 0 ; i < list.getAnalyticsOfStatements().size();i++){
			analyticsOfStatementsService.save(list.getAnalyticsOfStatements().get(i));
		}
		return list;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody AnalyticsOfStatements analyticsOfStatements) throws JAXBException {
		analyticsOfStatements.setItemNumber(null);
		//AnalyticsOfStatements analyticsOfStatementsXML = analyticsOfStatementsService.save(analyticsOfStatements);
		
		//System.out.println(analyticsOfStatements);
		Statements statements = getStatements(new File("analytic.xml"));
		statements.getAnalyticsOfStatements().add(analyticsOfStatements);
		File file = new File("analytic.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Statements.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(statements, file);
		jaxbMarshaller.marshal(statements, System.out);
		
		
		
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