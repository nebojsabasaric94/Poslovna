package bank.legalEntity;

import java.util.List;

public interface LegalEntityService {

	public List<LegalEntity> findAll();
	public List<LegalEntity> search(LegalEntity legalEntity);
}
