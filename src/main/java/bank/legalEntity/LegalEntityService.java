package bank.legalEntity;

import java.util.List;

import bank.bank.Bank;

public interface LegalEntityService {

	public List<LegalEntity> findAll();
	public List<LegalEntity> search(LegalEntity legalEntity);
	public LegalEntity findOne(Long id);
	public void delete(Long id);
	public LegalEntity save(LegalEntity legalEntity);
}
