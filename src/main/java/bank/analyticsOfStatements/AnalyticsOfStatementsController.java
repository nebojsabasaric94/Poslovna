package bank.analyticsOfStatements;

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
	public ResponseEntity<List<AnalyticsOfStatements>> findAll() {
		return new ResponseEntity<>(analyticsOfStatementsService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody AnalyticsOfStatements analyticsOfStatements) {
		analyticsOfStatements.setItemNumber(null);
		analyticsOfStatementsService.save(analyticsOfStatements);
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