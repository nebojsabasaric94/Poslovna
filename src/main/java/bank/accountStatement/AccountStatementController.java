package bank.accountStatement;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
		ArrayList<AnalyticsOfStatements> incomeList = new ArrayList<AnalyticsOfStatements>();
		
		for(int i = 0; i < income.size();i++){
			if(income.get(i).getPaymentType().getNameOfPaymentType().equals("Nalog za prenos"))//ako je nalog za prenos i ako nisu iz iste banke primaocu ne lezu pare, ne treba da ukljucim taj nalog
			{
				String debBank = income.get(i).getDebtorAccount().substring(0, 3);
				if(debBank.equals(legalEntityAccount.getBrojRacuna().substring(0,3)))//ako nisu iz iste banke 
					incomeList.add(income.get(i));
			} else {
				incomeList.add(income.get(i));
			}
		}

		ArrayList<AnalyticsOfStatements> expense = analyticsOfStatementsService.findExpenseForPeriod(startDate, endDate, legalEntityAccount);
		DailyAccountBalance startDailyAccountBalance = dailyAccountBalanceService.findAccountStateAt(legalEntityAccount, startDate);
		DailyAccountBalance endDailyAccountBalance = dailyAccountBalanceService. findAccountStateAt(legalEntityAccount, endDate);
		ArrayList<AnalyticsOfStatements> all = analyticsOfStatementsService.findAllForPeriod(startDate, endDate, legalEntityAccount);
		ArrayList<AnalyticsOfStatements> allList = new ArrayList<AnalyticsOfStatements>();
		
		for(int i = 0; i < all.size();i++){
			if(all.get(i).getPaymentType().getNameOfPaymentType().equals("Nalog za prenos"))//ako je nalog za prenos i ako nisu iz iste banke primaocu ne lezu pare, ne treba da ukljucim taj nalog
			{
				if(all.get(i).getAccountCreditor().equals(legalEntityAccount.getBrojRacuna())){
					String debBank = all.get(i).getDebtorAccount().substring(0, 3);
					if(debBank.equals(legalEntityAccount.getBrojRacuna().substring(0,3)))//ako su iz iste banke 
						allList.add(all.get(i));
				} else {
					allList.add(all.get(i));
				}
			} else {
				allList.add(all.get(i));
			}
		}		
		Float incomeSum = (float) 0.0;
		for(int i=0;i < incomeList.size();i++){
			incomeSum += incomeList.get(i).getSum();
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
		for(int i = 0; i < allList.size();i++){
			AnalyticsOfStatements a = allList.get(i);
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
	
	JasperPrint jasperPrint = new JasperPrint();
	
	
	
	
	@PostMapping("/pdf/{startDate}/{endDate}")
	public void exportToPdf(HttpServletResponse response, @PathVariable("startDate")Date startDate,@PathVariable("endDate")Date endDate,@RequestBody LegalEntityAccount account) throws JAXBException, JRException, FileNotFoundException{
		LegalEntityAccount legalEntityAccount = legalEntityAccountService.findByAccountNumber(account.getBrojRacuna());
		String clientName = "";
		if(legalEntityAccount.getClient().getTypeOfClient().equals("Fizicko lice")){
			clientName = legalEntityAccount.getClient().getFirstName() + " " + legalEntityAccount.getClient().getLastName();
		}
		else{
			clientName = ((LegalEntity) legalEntityAccount.getClient()).getNaziv_klijenta();
		}	
		
		ArrayList<AnalyticsOfStatements> income = analyticsOfStatementsService.findIncomeForPeriod(startDate,endDate,legalEntityAccount);
		ArrayList<AnalyticsOfStatements> incomeList = new ArrayList<AnalyticsOfStatements>();
		for(int i = 0; i < income.size();i++){//brisem ako je primalac i ako je nalog za prenos
			if(income.get(i).getPaymentType().getNameOfPaymentType().equals("Nalog za prenos"))//ako je nalog za prenos i ako nisu iz iste banke primaocu ne lezu pare, ne treba da ukljucim taj nalog
			{
				String debBank = income.get(i).getDebtorAccount().substring(0, 3);
				if(debBank.equals(legalEntityAccount.getBrojRacuna().substring(0,3)))//ako nisu iz iste banke 
					incomeList.add(income.get(i));
			} else {
				incomeList.add(income.get(i));
			}
		}

		ArrayList<AnalyticsOfStatements> expense = analyticsOfStatementsService.findExpenseForPeriod(startDate, endDate, legalEntityAccount);
		DailyAccountBalance startDailyAccountBalance = dailyAccountBalanceService.findAccountStateAt(legalEntityAccount, startDate);
		DailyAccountBalance endDailyAccountBalance = dailyAccountBalanceService. findAccountStateAt(legalEntityAccount, endDate);
		ArrayList<AnalyticsOfStatements> all = analyticsOfStatementsService.findAllForPeriod(startDate, endDate, legalEntityAccount);
		ArrayList<AnalyticsOfStatements> allList = new ArrayList<AnalyticsOfStatements>();
		for(int i = 0; i < all.size();i++){
			if(all.get(i).getPaymentType().getNameOfPaymentType().equals("Nalog za prenos"))//ako je nalog za prenos i ako nisu iz iste banke primaocu ne lezu pare, ne treba da ukljucim taj nalog
			{
				if(all.get(i).getAccountCreditor().equals(legalEntityAccount.getBrojRacuna())){
					String debBank = all.get(i).getDebtorAccount().substring(0, 3);
					if(debBank.equals(legalEntityAccount.getBrojRacuna().substring(0,3)))//ako su iz iste banke 
						allList.add(all.get(i));
				} else {
					allList.add(all.get(i));
				}
			} else {
				allList.add(all.get(i));
			}
		}		
		Float incomeSum = (float) 0.0;
		for(int i=0;i < incomeList.size();i++){
			incomeSum += incomeList.get(i).getSum();
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
		for(int i = 0; i < allList.size();i++){
			AnalyticsOfStatements a = allList.get(i);
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
		
		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(lista);


		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("ItemDataSource", itemsJRBean);
		parameters.put("banka", legalEntityAccount.getBank().getName());
		parameters.put("klijent", clientName);
		parameters.put("Ukupan prihod", incomeSum);
		parameters.put("Ukupan rashod", expenseSum);
		parameters.put("Stanje na pocetku", setStartAccountState);
		parameters.put("Stanje na kraju", setStateAtTheEndOfPeriod);
		parameters.put("od", startDate);
		parameters.put("do", endDate);
		
		
		
		jasperPrint = JasperFillManager.fillReport("excerpt.jasper", parameters, new JREmptyDataSource());
	    File file = new File("D://"+clientName+".pdf");
	    OutputStream outputStream = new FileOutputStream(file);
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	    
	    
	    
	    try {           
			
		     //JasperExportManager.exportReportToPdfFile(jasperPrint, "D://Test.pdf");
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			    try {
			        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			    } catch (JRException e1) {
			        e1.printStackTrace();
			    }
			    byte[] data = out.toByteArray();
			    response.setContentType("application/pdf");
			    response.setContentLength(data.length);
			    response.getOutputStream().write(data);
		        response.getOutputStream().flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	    
	    
		
	}
	
	@GetMapping("/getpdf")
	private void getPdff(HttpServletResponse response){
		
	    
	    
	    
	    try {       
	    	File file = new File("D://dadada.pdf");
			OutputStream outputStream = new FileOutputStream(file);
		    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			
		     //JasperExportManager.exportReportToPdfFile(jasperPrint, "D://Test.pdf");
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			    try {
			        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			    } catch (JRException e1) {
			        e1.printStackTrace();
			    }
			    byte[] data = out.toByteArray();
			    response.setContentType("application/pdf");
			    response.setContentLength(data.length);
			    response.getOutputStream().write(data);
		        response.getOutputStream().flush();
		}
		catch(Exception e) {
			e.printStackTrace();
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
