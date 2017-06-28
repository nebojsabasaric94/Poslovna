package bank.legalEntityAccount;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LegalEntityAccountServiceImpl implements LegalEntityAccountService {

	private final LegalEntityAccountRepository repository;

	@Autowired
	public LegalEntityAccountServiceImpl(final LegalEntityAccountRepository repository) {
		this.repository = repository;
	}

	
	@Override
	public List<LegalEntityAccount> findAll() {
		return (List<LegalEntityAccount>) repository.findAll();
	}

	@Override
	public LegalEntityAccount findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public LegalEntityAccount save(LegalEntityAccount legalEntityAccount) {
		return repository.save(legalEntityAccount);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	public List<LegalEntityAccount> search(LegalEntityAccount legalEntityAccount) {
		String client_id = "%";
		if(legalEntityAccount.getClient() != null)
			client_id = "" + legalEntityAccount.getClient().getId();
		
		String bank_id = "%";
		if(legalEntityAccount.getBank() != null)
			bank_id = "" + legalEntityAccount.getBank().getId();
		
		String currency_id = "%";
		if(legalEntityAccount.getCurrency() != null)
			currency_id = ""+legalEntityAccount.getCurrency().getId();
		
		String datum_otvaranja="" ;
		if(legalEntityAccount.getDatumOtvaranja() != null){
			datum_otvaranja =new Date(legalEntityAccount.getDatumOtvaranja().getTime()).toString();
		}
		return repository.search(legalEntityAccount.getBrojRacuna(), datum_otvaranja, legalEntityAccount.getVazeci(), client_id, bank_id, currency_id);

	}


	@Override
	public LegalEntityAccount findByAccountNumber(String creditorAccountNumber) {
		// TODO Auto-generated method stub
		return repository.findByAccountNumber(creditorAccountNumber);
	}


	@Override
	public ArrayList<LegalEntityAccount> findByBank(Long id) {
		// TODO Auto-generated method stub
		return repository.findByBank(id);
	}

}
