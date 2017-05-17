package bank.bussinessActivityCode;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BusinessActivityCodeServiceImpl implements BusinessActivityCodeService{

	@Autowired
	private BusinessActivityCodeRepository repository;
}
