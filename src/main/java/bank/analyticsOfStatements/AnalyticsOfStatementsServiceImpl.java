package bank.analyticsOfStatements;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.legalEntityAccount.LegalEntityAccount;

@Service
@Transactional
public class AnalyticsOfStatementsServiceImpl implements AnalyticsOfStatementsService {
	private final AnalyticsOfStatementsRepository repository;

	@Autowired
	public AnalyticsOfStatementsServiceImpl(final AnalyticsOfStatementsRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<AnalyticsOfStatements> findAll() {
		return (List<AnalyticsOfStatements>) repository.findAll();
	}

	@Override
	public AnalyticsOfStatements save(AnalyticsOfStatements analyticsOfStatements) {
		return repository.save(analyticsOfStatements);
	}

	@Override
	public AnalyticsOfStatements findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<AnalyticsOfStatements> search(AnalyticsOfStatements analyticsOfStatements) {
		String dateOfReceipt = "";
		if (analyticsOfStatements.getDateOfReceipt() != null)
			dateOfReceipt = new Date(analyticsOfStatements.getDateOfReceipt().getTime()).toString();

		String currencyDate = "";
		if (analyticsOfStatements.getCurrencyDate() != null)
			currencyDate = new Date(analyticsOfStatements.getCurrencyDate().getTime()).toString();

		String modelAssigments = "%";
		if (analyticsOfStatements.getModelAssigments() != null)
			modelAssigments = "" + analyticsOfStatements.getModelAssigments();

		String modelApproval = "%";
		if (analyticsOfStatements.getModelApproval() != null)
			modelApproval = "" + analyticsOfStatements.getModelApproval();

		String sum = "%";
		if (analyticsOfStatements.getSum() != null) {
			sum = "" + analyticsOfStatements.getSum();
			String[] splitted = sum.split("\\.");
			if (splitted[1].equals("0"))
				sum = splitted[0];
		}

		String typeOfMistake = "%";
		if (analyticsOfStatements.getTypeOfMistake() != null)
			typeOfMistake = "" + analyticsOfStatements.getTypeOfMistake();

		String dailyAccountBalance = "%";
		if (analyticsOfStatements.getDailyAccountBalance().getId() != null)
			dailyAccountBalance = "" + analyticsOfStatements.getDailyAccountBalance().getId();

		String paymentType = "%";
		if (analyticsOfStatements.getPaymentType().getId() != null)
			paymentType = "" + analyticsOfStatements.getPaymentType().getId();

		String place = "%";
		if (analyticsOfStatements.getPlace().getId() != null)
			place = "" + analyticsOfStatements.getPlace().getId();

		String paymentCurrency = "%";
		if (analyticsOfStatements.getPaymentCurrency().getId() != null)
			paymentCurrency = "" + analyticsOfStatements.getPaymentCurrency().getId();

		return repository.search(analyticsOfStatements.getDebtor_originator(),
				analyticsOfStatements.getPurposeOfPayment(), analyticsOfStatements.getCreditor_recipient(),
				dateOfReceipt, currencyDate, analyticsOfStatements.getDebtorAccount(), modelAssigments,
				analyticsOfStatements.getReferenceNumberAssigments(), analyticsOfStatements.getAccountCreditor(),
				modelApproval, analyticsOfStatements.getReferenceNumberCreditor(), analyticsOfStatements.isEmergency(),
				sum, typeOfMistake, analyticsOfStatements.getStatus(), dailyAccountBalance, paymentType, place,
				paymentCurrency);
	}

	@Override
	public ArrayList<AnalyticsOfStatements> findByDateAndAccount(LegalEntityAccount legalEntityAccount,
			java.util.Date trafficDate) {
		// TODO Auto-generated method stub
		return repository.findByDateAndAccount(legalEntityAccount.getBrojRacuna(), new Date(trafficDate.getTime()));
	}

	@Override
	public ArrayList<AnalyticsOfStatements> findIncomeForPeriod(Date startDate, Date endDate,
			LegalEntityAccount legalEntityAccount) {
		return repository.findIncomeForPeriod(startDate, endDate, legalEntityAccount.getBrojRacuna());
	}

	@Override
	public ArrayList<AnalyticsOfStatements> findExpenseForPeriod(Date startDate, Date endDate,
			LegalEntityAccount legalEntityAccount) {
		// TODO Auto-generated method stub
		return repository.findExpenseForPeriod(startDate, endDate, legalEntityAccount.getBrojRacuna());
	}

	@Override
	public ArrayList<AnalyticsOfStatements> findAllForPeriod(Date startDate, Date endDate,
			LegalEntityAccount legalEntityAccount) {
		// TODO Auto-generated method stub
		return repository.findAllForPeriod(startDate, endDate, legalEntityAccount.getBrojRacuna());

	}
}