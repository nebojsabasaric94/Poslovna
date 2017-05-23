package bank.legalEntityAccount;

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

}
