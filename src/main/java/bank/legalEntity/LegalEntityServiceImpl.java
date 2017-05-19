package bank.legalEntity;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LegalEntityServiceImpl implements LegalEntityService{

	@Autowired
	private LegalEntityRepository repository;

	@Override
	public List<LegalEntity> findAll() {
		// TODO Auto-generated method stub
		return (List<LegalEntity>) repository.findAll();
	}
}
