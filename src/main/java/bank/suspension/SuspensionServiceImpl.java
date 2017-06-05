package bank.suspension;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class SuspensionServiceImpl implements SuspensionService {
	private final SuspensionRepository repository;

	@Autowired
	public SuspensionServiceImpl(final SuspensionRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Suspension> findAll() {
		return (List<Suspension>) repository.findAll();
	}

	@Override
	public Suspension save(Suspension suspension) {
		
		return repository.save(suspension);
	}

	@Override
	public Suspension findOne(Long id) {
		return repository.findOne(id);
	}
	
	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public List<Suspension> search(Suspension suspension) {
		String account_id = "%";
		if(suspension.getLegalEntityAccount().getId() != null)
			account_id = "" + suspension.getLegalEntityAccount().getId();
		
		String date ="" ;
		if(suspension.getDate() != null){
			date =new Date(suspension.getDate().getTime()).toString();
		}
		return repository.search(account_id, suspension.getTransferToAccount(), date);
	}
}