package bank.bussinessActivityCode;

import java.util.List;

import bank.bank.Bank;

public interface BusinessActivityCodeService {

	public List<BusinessActivityCode> findAll();

	public List<BusinessActivityCode> search(BusinessActivityCode businessActivityCode);
	
	public BusinessActivityCode findOne(Long id);
	
	public BusinessActivityCode save(BusinessActivityCode businessActivityCode);
}
