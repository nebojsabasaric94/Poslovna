package bank.legalEntityAccount;

import java.util.List;

public interface LegalEntityAccountService {
	
	public List<LegalEntityAccount> findAll();
	
	public LegalEntityAccount findOne(Long id);
	
	public LegalEntityAccount save(LegalEntityAccount legalEntityAccount);
	
	public void delete(Long id);
	

}
