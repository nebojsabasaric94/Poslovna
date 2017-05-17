package bank.legalEntity;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LegalEntityServiceImpl implements LegalEntityService{

	@Autowired
	private LegalEntityRepository repository;
}
