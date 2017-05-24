package bank.itemTransfer;

import java.util.List;

public interface ItemTransferService {
	List<ItemTransfer> findAll();

	ItemTransfer save(ItemTransfer ItemTransfer);

	ItemTransfer findOne(Long id);
	
	public void delete(Long id);
	
	public List<ItemTransfer> search(ItemTransfer itemTransfer);
}
