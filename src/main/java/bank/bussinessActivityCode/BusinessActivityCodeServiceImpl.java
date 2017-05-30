package bank.bussinessActivityCode;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BusinessActivityCodeServiceImpl implements BusinessActivityCodeService{

	@Autowired
	private BusinessActivityCodeRepository repository;

	@Override
	public List<BusinessActivityCode> findAll() {
		// TODO Auto-generated method stub
		return (List<BusinessActivityCode>) repository.findAll();
	}

	@Override
	public List<BusinessActivityCode> search(BusinessActivityCode businessActivityCode) {
		// TODO Auto-generated method stub
		return repository.search(businessActivityCode.getCode(), businessActivityCode.getName());
	}

	@Override
	public BusinessActivityCode findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public BusinessActivityCode save(BusinessActivityCode businessActivityCode) {
		return repository.save(businessActivityCode);
	}
}
