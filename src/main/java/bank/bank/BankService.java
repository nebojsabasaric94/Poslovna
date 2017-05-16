package bank.bank;

import java.util.List;

public interface BankService {
	List<Bank> findAll();

	Bank save(Bank bank);

	Bank findOne(Long id);
	
	public void delete(Long id);
}
