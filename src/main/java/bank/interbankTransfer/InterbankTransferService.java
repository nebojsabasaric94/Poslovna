package bank.interbankTransfer;

import java.util.List;

public interface InterbankTransferService {
	List<InterbankTransfer> findAll();

	InterbankTransfer save(InterbankTransfer interbankTransfer);

	InterbankTransfer findOne(Long id);
	
	public void delete(Long id);
}
