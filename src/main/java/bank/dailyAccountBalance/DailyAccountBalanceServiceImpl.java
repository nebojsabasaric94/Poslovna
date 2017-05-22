package bank.dailyAccountBalance;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
		if(accountBalance.getTrafficDate() != null)
			date = new Date(accountBalance.getTrafficDate().getTime()).toString();
		
		String account_id = "%";
		if(accountBalance.getLegalEntityAccount().getId() != null)
			account_id = ""+accountBalance.getLegalEntityAccount().getId();
		
		String previuousState =  "";
		if(accountBalance.getPreviousState() != null){
			previuousState = ""+accountBalance.getPreviousState();
			String []splitted = previuousState.split("\\.");
			if(splitted[1].equals("0"))
				previuousState = splitted[0];
		}
		String trafficToBenefit =  "";
		if(accountBalance.getTrafficToBenefit() != null){
			trafficToBenefit = ""+accountBalance.getTrafficToBenefit();
			String[] splitted = trafficToBenefit.split("\\.");
			if(splitted[1].equals("0"))
				trafficToBenefit = splitted[0];
		}
		String trafficToBurden =  "";
		if(accountBalance.getTrafficToTheBurden() !=  null){
			trafficToBurden = ""+accountBalance.getTrafficToTheBurden();
			String []splitted = trafficToBurden.split("\\.");
			if(splitted[1].equals("0"))
				trafficToBurden = splitted[0];
		}
		String newState =  "";
		if(accountBalance.getNewState() != null){
			newState = ""+accountBalance.getNewState();
			String []splitted = newState.split("\\.");
			if(splitted[1].equals("0"))
				newState = splitted[0];
		}		
		
		return repository.search(date, previuousState, trafficToBenefit, trafficToBurden, newState, account_id);
	}
}