package bank.legalEntityAccount;

import java.util.List;

public interface LegalEntityAccountService {

	public List<LegalEntityAccount> findAll();
	public List<LegalEntityAccount> search(LegalEntityAccount legalEntityAccount);
}
