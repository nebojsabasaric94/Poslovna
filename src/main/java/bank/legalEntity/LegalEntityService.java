package bank.legalEntity;

import java.util.List;

public interface LegalEntityService {

	public List<LegalEntity> findAll();
	public List<LegalEntity> search(LegalEntity legalEntity);
	public LegalEntity findOne(Long id);
	public void delete(Long id);
}
