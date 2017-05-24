package bank.bussinessActivityCode;

import java.util.List;

public interface BusinessActivityCodeService {

	public List<BusinessActivityCode> findAll();

	public List<BusinessActivityCode> search(BusinessActivityCode businessActivityCode);
	
	public BusinessActivityCode findOne(Long id);
}
