package bank.legalEntityAccount;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.pdf.codec.Base64.InputStream;

import bank.bank.Bank;
import bank.bank.BankService;
import bank.client.Client;
import bank.client.ClientService;
import bank.currency.Currency;
import bank.currency.CurrencyService;
import bank.dailyAccountBalance.DailyAccountBalance;
import bank.dailyAccountBalance.DailyAccountBalanceService;
import bank.legalEntity.LegalEntity;
import bank.legalEntity.LegalEntityService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/legalEntityAccount")
public class LegalEntityAccountController {

	private final LegalEntityAccountService legalEntityAccountService;
	private final BankService bankService;
	private final CurrencyService currencyService;
	private final ClientService clientService;
	private final LegalEntityService legalEntityService;
	private final DailyAccountBalanceService dailyAccountBalanceService;

	@Autowired
	public LegalEntityAccountController(LegalEntityAccountService legalEntityAccountService,
			final BankService bankService, final CurrencyService currencyService, final ClientService clientService,
			final LegalEntityService legalEntityService, final DailyAccountBalanceService dailyAccountBalanceService) {
		this.legalEntityAccountService = legalEntityAccountService;
		this.bankService = bankService;
		this.currencyService = currencyService;
		this.clientService = clientService;
		this.legalEntityService = legalEntityService;
		this.dailyAccountBalanceService = dailyAccountBalanceService;
	}

	@GetMapping
	public List<LegalEntityAccount> findAll() {
		return legalEntityAccountService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody LegalEntityAccount legalEntityAccount) {
		legalEntityAccount.setId(null);
		legalEntityAccountService.save(legalEntityAccount);
	}

	@GetMapping("/nextBank/{bankId}")
	public List<LegalEntityAccount> getNextBank(@PathVariable Long bankId) {
		Bank bank = bankService.findOne(bankId);

		return bank.getLegalEntityAccount();
	}

	@GetMapping("/nextCurrency/{currencyId}")
	public List<LegalEntityAccount> getNextCurrency(@PathVariable Long currencyId) {
		Currency currency = currencyService.findOne(currencyId);

		return currency.getLegalEntityAccount();
	}

	@GetMapping("/nextClient/{clientId}")
	public List<LegalEntityAccount> nextClient(@PathVariable Long clientId) {
		Client client = clientService.findOne(clientId);

		return client.getLegalEntityAccount();
	}

	@GetMapping("/nextLegalEntity/{legalEntityId}")
	public List<LegalEntityAccount> nextLegalEntity(@PathVariable Long legalEntityId) {
		LegalEntity legalEntity = legalEntityService.findOne(legalEntityId);

		return legalEntity.getLegalEntityAccount();
	}

	@PostMapping("/search")
	public List<LegalEntityAccount> search(@RequestBody LegalEntityAccount legalEntityAccount) {
		return legalEntityAccountService.search(legalEntityAccount);
	}

	@GetMapping("/pdf/{id}")
	public void exportAccountsToPdf(@PathVariable Long id) throws Exception {
		ArrayList<AccountForBank> list = new ArrayList<>();

		ArrayList<LegalEntityAccount> accounts = legalEntityAccountService.findByBank(id);
		Bank bank = bankService.findOne(id);
		Date date = new Date();
		for (LegalEntityAccount account : accounts) {
			String clientName = "";
			if (account.getClient().getTypeOfClient().equals("Fizicko lice")) {
				clientName = account.getClient().getFirstName() + " " + account.getClient().getLastName();
			} else {
				clientName = ((LegalEntity) account.getClient()).getNaziv_klijenta();
			}
			DailyAccountBalance dailyAccountBalance = dailyAccountBalanceService.findByAccountNumberAndDate(account, new Date());
			Float state = (float) 0.0;
			if (dailyAccountBalance.getTrafficToBenefit() == 0 && dailyAccountBalance.getTrafficToTheBurden() == 0)
				state = dailyAccountBalance.getPreviousState();
			else
				state = dailyAccountBalance.getNewState();
			AccountForBank afb = new AccountForBank(clientName, account.getBrojRacuna(), state.toString(),
					account.getCurrency().getOfficial_code());
			list.add(afb);
		}

		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);


		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ItemDataSource", itemsJRBean);
		parameters.put("BankName", bank.getName());
		parameters.put("Date", date);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport("excerptBank.jasper", parameters, new JREmptyDataSource());
	    File file = new File("D://racuni.pdf");
	    OutputStream outputStream = new FileOutputStream(file);
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	
	}

}
