package bank.dailyAccountBalance;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.analyticsOfStatements.AnalyticsOfStatements;
import bank.legalEntityAccount.LegalEntityAccount;

@Service
@Transactional
public class DailyAccountBalanceServiceImpl implements DailyAccountBalanceService {

	private final DailyAccountBalanceRepository repository;

	@Autowired
	public DailyAccountBalanceServiceImpl(final DailyAccountBalanceRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<DailyAccountBalance> findAll() {
		return (List<DailyAccountBalance>) repository.findAll();
	}

	@Override
	public DailyAccountBalance save(DailyAccountBalance dailyAccountBalance) {
		return repository.save(dailyAccountBalance);
	}

	@Override
	public DailyAccountBalance findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public List<DailyAccountBalance> search(DailyAccountBalance accountBalance) {
		String date = "";
		if (accountBalance.getTrafficDate() != null)
			date = new Date(accountBalance.getTrafficDate().getTime()).toString();

		String account_id = "%";
		if (accountBalance.getLegalEntityAccount().getId() != null)
			account_id = "" + accountBalance.getLegalEntityAccount().getId();

		String previuousState = "";
		if (accountBalance.getPreviousState() != null) {
			previuousState = "" + accountBalance.getPreviousState();
			String[] splitted = previuousState.split("\\.");
			if (splitted[1].equals("0"))
				previuousState = splitted[0];
		}
		String trafficToBenefit = "";
		if (accountBalance.getTrafficToBenefit() != null) {
			trafficToBenefit = "" + accountBalance.getTrafficToBenefit();
			String[] splitted = trafficToBenefit.split("\\.");
			if (splitted[1].equals("0"))
				trafficToBenefit = splitted[0];
		}
		String trafficToBurden = "";
		if (accountBalance.getTrafficToTheBurden() != null) {
			trafficToBurden = "" + accountBalance.getTrafficToTheBurden();
			String[] splitted = trafficToBurden.split("\\.");
			if (splitted[1].equals("0"))
				trafficToBurden = splitted[0];
		}
		String newState = "";
		if (accountBalance.getNewState() != null) {
			newState = "" + accountBalance.getNewState();
			String[] splitted = newState.split("\\.");
			if (splitted[1].equals("0"))
				newState = splitted[0];
		}

		return repository.search(date, previuousState, trafficToBenefit, trafficToBurden, newState, account_id);
	}

	@Override
	public DailyAccountBalance findByAccountNumberAndDate(LegalEntityAccount creditorAccount, java.util.Date date) {
		String date1 = new Date(date.getTime()).toString();
		DailyAccountBalance result = repository.findByAccountNumberAndDate(creditorAccount.getBrojRacuna(), date1);
		ArrayList<DailyAccountBalance> balances = null;
		if (result == null) {
			balances = repository.findByAccountNumber(creditorAccount.getBrojRacuna());
			// pronalazim najblizi jer nemam u bazi svaki dan

			if (balances.size() > 0) {// ako je lista prazna, nema dnevnih
										// stanja, racun je na nuli
				DailyAccountBalance max = balances.get(0);
				for (DailyAccountBalance d : balances) {// pronalazim najblizi
														// datun
					if (d.getTrafficDate().after(max.getTrafficDate()) && d.getTrafficDate().before(date))
						max = d;
				}
				if(! max.getTrafficDate().before(date)){//zbog prvog elementa 
					result = new DailyAccountBalance();
					result.setLegalEntityAccount(creditorAccount);
					result.setNewState((float) 0.0);
					result.setPreviousState((float) 0.0);
					result.setTrafficToBenefit((float) 0.0);
					result.setTrafficToTheBurden((float) 0.0);
					result.setTrafficDate(date);
					result.setAnalyticsOfStatements(new ArrayList<AnalyticsOfStatements>());
					return result;					
				}				
				result = new DailyAccountBalance();
				result.setLegalEntityAccount(creditorAccount);
				result.setPreviousState(max.getNewState());
				result.setNewState((float) 0.0);
				result.setTrafficToBenefit((float) 0.0);
				result.setTrafficToTheBurden((float) 0.0);
				result.setTrafficDate(date);
				result.setAnalyticsOfStatements(new ArrayList<AnalyticsOfStatements>());
				return repository.save(result);

			} else {
				result = new DailyAccountBalance();
				result.setLegalEntityAccount(creditorAccount);
				result.setNewState((float) 0.0);
				result.setPreviousState((float) 0.0);
				result.setTrafficToBenefit((float) 0.0);
				result.setTrafficToTheBurden((float) 0.0);
				result.setTrafficDate(date);
				result.setAnalyticsOfStatements(new ArrayList<AnalyticsOfStatements>());
				return repository.save(result);
			}
		}
		return result;
	}

	@Override
	public DailyAccountBalance findAccountStateAt(LegalEntityAccount creditorAccount, java.util.Date date) {
		String date1 = new Date(date.getTime()).toString();
		DailyAccountBalance result = repository.findByAccountNumberAndDate(creditorAccount.getBrojRacuna(), date1);
		ArrayList<DailyAccountBalance> balances = null;
		if (result == null) {
			balances = repository.findByAccountNumber(creditorAccount.getBrojRacuna());
			// pronalazim najblizi jer nemam u bazi svaki dan

			if (balances.size() > 0) {// ako je lista prazna, nema dnevnih
										// stanja, racun je na nuli
				DailyAccountBalance max = balances.get(0);
				for (DailyAccountBalance d : balances) {// pronalazim najblizi
														// datun
					if (d.getTrafficDate().after(max.getTrafficDate()) && d.getTrafficDate().before(date))
						max = d;
				}
				if(! max.getTrafficDate().before(date)){
					result = new DailyAccountBalance();
					result.setLegalEntityAccount(creditorAccount);
					result.setNewState((float) 0.0);
					result.setPreviousState((float) 0.0);
					result.setTrafficToBenefit((float) 0.0);
					result.setTrafficToTheBurden((float) 0.0);
					result.setTrafficDate(date);
					result.setAnalyticsOfStatements(new ArrayList<AnalyticsOfStatements>());
					return result;					
				}
				result = new DailyAccountBalance();
				result.setLegalEntityAccount(creditorAccount);
				result.setPreviousState(max.getNewState());
				result.setNewState((float) 0.0);
				result.setTrafficToBenefit((float) 0.0);
				result.setTrafficToTheBurden((float) 0.0);
				result.setTrafficDate(date);
				result.setAnalyticsOfStatements(new ArrayList<AnalyticsOfStatements>());
				return result;

			} else {
				result = new DailyAccountBalance();
				result.setLegalEntityAccount(creditorAccount);
				result.setNewState((float) 0.0);
				result.setPreviousState((float) 0.0);
				result.setTrafficToBenefit((float) 0.0);
				result.setTrafficToTheBurden((float) 0.0);
				result.setTrafficDate(date);
				result.setAnalyticsOfStatements(new ArrayList<AnalyticsOfStatements>());
				return result;
			}
		}
		return result;
	}
	
	
	
	@Override
	public List<DailyAccountBalance> findBalances(LegalEntityAccount account, java.util.Date start,
			java.util.Date end) {
		// TODO Auto-generated method stub
		return repository.findBalances(account.getBrojRacuna(), start, end);
	}

}