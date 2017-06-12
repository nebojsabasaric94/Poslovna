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

	@Override
	public List<LegalEntity> search(LegalEntity legalEntity) {
		String businessActivityCode = "%";
		if(legalEntity.getBusinessActivityCode() != null)
			businessActivityCode = ""+legalEntity.getBusinessActivityCode().getId();
		String place_id = "%";
		if(legalEntity.getResidence() != null)
			place_id = "" + legalEntity.getResidence().getId();
		return repository.search(legalEntity.getNaziv_klijenta(), legalEntity.getSkraceni_naziv_klijenta(), legalEntity.getFax(),
				legalEntity.getMaticni_broj(), legalEntity.getPib(), legalEntity.getNaziv_organa(), businessActivityCode, legalEntity.getNadlezni_poreski_organ_za_klijenta(),
				legalEntity.getFirstName(), legalEntity.getLastName(), legalEntity.getAddress(), legalEntity.getEmail(), legalEntity.getPhone(), legalEntity.getAddressForStatements(),
				legalEntity.getJmbg(), place_id, legalEntity.getTypeOfClient(),legalEntity.getEmailStatements());
	}

	@Override
	public LegalEntity findOne(Long id) {
		LegalEntity e = repository.findOne(id);
		System.out.println(e.toString());
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
		
	}

	@Override
	public LegalEntity save(LegalEntity legalEntity) {
		return repository.save(legalEntity);
	}
}
