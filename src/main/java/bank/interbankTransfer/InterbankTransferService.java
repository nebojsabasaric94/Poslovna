package bank.interbankTransfer;

import java.util.List;

public interface InterbankTransferService {
	public List<InterbankTransfer> findAll();

	public InterbankTransfer save(InterbankTransfer interbankTransfer);

	public InterbankTransfer findOne(Long id);
	
	public void delete(Long id);
	public List<InterbankTransfer> search(InterbankTransfer interbankTransfer);
}
