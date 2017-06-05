package bank.interbankTransfer;

import java.sql.Date;
import java.util.List;

import bank.bank.Bank;

public interface InterbankTransferService {
	public List<InterbankTransfer> findAll();

	public InterbankTransfer save(InterbankTransfer interbankTransfer);

	public InterbankTransfer findOne(Long id);
	
	public void delete(Long id);
	public List<InterbankTransfer> search(InterbankTransfer interbankTransfer);

	public InterbankTransfer findByDateAndBanks(Date currencyDate, Bank debtorBank, Bank creditorBank);
}
