package bank.accountStatement;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bank.analyticsOfStatements.AnalyticsOfStatements;
import bank.analyticsOfStatements.AnalyticsOfStatementsService;
import bank.analyticsOfStatements.AnalyticsOfStatementsXml;
import bank.dailyAccountBalance.DailyAccountBalance;
import bank.dailyAccountBalance.DailyAccountBalanceService;
import bank.legalEntity.LegalEntity;
import bank.legalEntityAccount.LegalEntityAccount;
import bank.legalEntityAccount.LegalEntityAccountService;

@RestController
@RequestMapping("/accountStatement")
public class AccountStatementController {

	@Autowired
	private AnalyticsOfStatementsService analyticsOfStatementsService;
	
	@Autowired
	private DailyAccountBalanceService dailyAccountBalanceService;
	
	@Autowired
	private LegalEntityAccountService legalEntityAccountService;
	
	@PostMapping("/xml/{startDate}/{endDate}")
	public void exportToXml(@PathVariable("startDate")Date startDate,@PathVariable("endDate")Date endDate,@RequestBody LegalEntityAccount account) throws JAXBException{
		LegalEntityAccount legalEntityAccount = legalEntityAccountService.findByAccountNumber(account.getBrojRacuna());
		ArrayList<AnalyticsOfStatements> income = analyticsOfStatementsService.findIncomeForPeriod(startDate,endDate,legalEntityAccount);
		for(int i = 0; i < income.size();i++){
			if(income.get(i).getPaymentType().getNameOfPaymentType().equals("Nalog za prenos"))//ako je nalog za prenos i ako nisu iz iste banke primaocu ne lezu pare, ne treba da ukljucim taj nalog
			{
				String debBank = income.get(i).getDebtorAccount().substring(0, 3);
				if(!debBank.equals(legalEntityAccount.getBrojRacuna().substring(0,3)))//ako nisu iz iste banke 
					income.remove(i);
			}
		}

		ArrayList<AnalyticsOfStatements> expense = analyticsOfStatementsService.findExpenseForPeriod(startDate, endDate, legalEntityAccount);
		DailyAccountBalance startDailyAccountBalance = dailyAccountBalanceService.findAccountStateAt(legalEntityAccount, startDate);
		DailyAccountBalance endDailyAccountBalance = dailyAccountBalanceService. findAccountStateAt(legalEntityAccount, endDate);
		ArrayList<AnalyticsOfStatements> all = analyticsOfStatementsService.findAllForPeriod(startDate, endDate, legalEntityAccount);
		for(int i = 0; i < all.size();i++){
			if(all.get(i).getPaymentType().getNameOfPaymentType().equals("Nalog za prenos"))//ako je nalog za prenos i ako nisu iz iste banke primaocu ne lezu pare, ne treba da ukljucim taj nalog
			{
				if(all.get(i).getAccountCreditor().equals(legalEntityAccount.getBrojRacuna())){
					String debBank = all.get(i).getDebtorAccount().substring(0, 3);
					if(!debBank.equals(legalEntityAccount.getBrojRacuna().substring(0,3)))//ako nisu iz iste banke 
						all.remove(i);
				}
			}
		}		
		Float incomeSum = (float) 0.0;
		for(int i=0;i < income.size();i++){
			incomeSum += income.get(i).getSum();
		}
		Float expenseSum = (float)0.0;
		for(int i=0;i < expense.size();i++){
			expenseSum += expense.get(i).getSum();
		}
		
		AccountStatementXml accountStatement = new AccountStatementXml();
		accountStatement.setTrafficToBenefit(incomeSum);
		accountStatement.setTrafficToTheBurden(expenseSum);
		accountStatement.setFromDate(startDate);
		accountStatement.setToDate(endDate);
		accountStatement.setStartAccountState(startDailyAccountBalance.getPreviousState());
		if(endDailyAccountBalance.getTrafficToBenefit() == 0 && endDailyAccountBalance.getTrafficToTheBurden() == 0)
			accountStatement.setStateAtTheEndOfPeriod(endDailyAccountBalance.getPreviousState());
		else
			accountStatement.setStateAtTheEndOfPeriod(endDailyAccountBalance.getNewState());
		accountStatement.setAccountNumber(legalEntityAccount.getBrojRacuna());
		accountStatement.setStatements(new ArrayList<AnalyticsOfStatementsXml>());
		for(int i = 0; i < all.size();i++){
			AnalyticsOfStatements a = all.get(i);
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
			accountStatement.getStatements().add(statementsXml);
		}
		saveTransfersToXml(accountStatement);
	}
	
	@PostMapping("/pdf/{startDate}/{endDate}")
	public void exportToPdf(@PathVariable("startDate")Date startDate,@PathVariable("endDate")Date endDate,@RequestBody LegalEntityAccount account) throws JAXBException{
		LegalEntityAccount legalEntityAccount = legalEntityAccountService.findByAccountNumber(account.getBrojRacuna());
		String clientName = "";
		if(legalEntityAccount.getClient().getTypeOfClient().equals("Fizicko lice")){
			clientName = legalEntityAccount.getClient().getFirstName() + " " + legalEntityAccount.getClient().getLastName();
		}
		else{
			clientName = ((LegalEntity) legalEntityAccount.getClient()).getNaziv_klijenta();
		}	
		
		ArrayList<AnalyticsOfStatements> income = analyticsOfStatementsService.findIncomeForPeriod(startDate,endDate,legalEntityAccount);
		for(int i = 0; i < income.size();i++){//brisem ako je primalac i ako je nalog za prenos
			if(income.get(i).getPaymentType().getNameOfPaymentType().equals("Nalog za prenos"))//ako je nalog za prenos i ako nisu iz iste banke primaocu ne lezu pare, ne treba da ukljucim taj nalog
			{
				String debBank = income.get(i).getDebtorAccount().substring(0, 3);
				if(!debBank.equals(legalEntityAccount.getBrojRacuna().substring(0,3)))//ako nisu iz iste banke 
					income.remove(i);
			}
		}

		ArrayList<AnalyticsOfStatements> expense = analyticsOfStatementsService.findExpenseForPeriod(startDate, endDate, legalEntityAccount);
		DailyAccountBalance startDailyAccountBalance = dailyAccountBalanceService.findAccountStateAt(legalEntityAccount, startDate);
		DailyAccountBalance endDailyAccountBalance = dailyAccountBalanceService. findAccountStateAt(legalEntityAccount, endDate);
		ArrayList<AnalyticsOfStatements> all = analyticsOfStatementsService.findAllForPeriod(startDate, endDate, legalEntityAccount);
		for(int i = 0; i < all.size();i++){
			if(all.get(i).getPaymentType().getNameOfPaymentType().equals("Nalog za prenos"))//ako je nalog za prenos i ako nisu iz iste banke primaocu ne lezu pare, ne treba da ukljucim taj nalog
			{
				if(all.get(i).getAccountCreditor().equals(legalEntityAccount.getBrojRacuna())){
					String debBank = all.get(i).getDebtorAccount().substring(0, 3);
					if(!debBank.equals(legalEntityAccount.getBrojRacuna().substring(0,3)))//ako nisu iz iste banke 
						all.remove(i);
				}
			}
		}		
		Float incomeSum = (float) 0.0;
		for(int i=0;i < income.size();i++){
			incomeSum += income.get(i).getSum();
		}
		Float expenseSum = (float)0.0;
		for(int i=0;i < expense.size();i++){
			expenseSum += expense.get(i).getSum();
		}
		Float setStateAtTheEndOfPeriod = (float) 0.0;
		Float setStartAccountState = (float) 0.0;

		setStartAccountState = startDailyAccountBalance.getPreviousState();
		if(endDailyAccountBalance.getTrafficToBenefit() == 0 && endDailyAccountBalance.getTrafficToTheBurden() == 0)
			setStateAtTheEndOfPeriod = endDailyAccountBalance.getPreviousState();
		else
			setStateAtTheEndOfPeriod = endDailyAccountBalance.getNewState();
		
		ArrayList<AnalyticsOfStatementsXml> lista = new ArrayList<>();
		for(int i = 0; i < all.size();i++){
			AnalyticsOfStatements a = all.get(i);
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
			lista.add(statementsXml);
		}		
		
	}

	private void saveTransfersToXml(AccountStatementXml xml) throws JAXBException {
		
		File file = new File("statements\\"+xml.getAccountNumber() +".xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(AccountStatementXml.class);
		
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(xml, file);
		jaxbMarshaller.marshal(xml, System.out);
	}	
	
}
