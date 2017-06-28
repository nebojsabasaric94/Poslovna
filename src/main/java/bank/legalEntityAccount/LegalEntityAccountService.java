package bank.legalEntityAccount;

import java.util.ArrayList;
import java.util.List;

public interface LegalEntityAccountService {
	
	public List<LegalEntityAccount> findAll();
	
	public LegalEntityAccount findOne(Long id);
	
	public LegalEntityAccount save(LegalEntityAccount legalEntityAccount);
	
	public void delete(Long id);
	
	public List<LegalEntityAccount> search(LegalEntityAccount legalEntityAccount);

	public LegalEntityAccount findByAccountNumber(String creditorAccountNumber);

	public ArrayList<LegalEntityAccount> findByBank(Long id);

}
