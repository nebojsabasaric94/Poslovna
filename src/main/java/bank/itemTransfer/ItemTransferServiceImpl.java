package bank.itemTransfer;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ItemTransferServiceImpl implements ItemTransferService {
	private final ItemTransferRepository repository;

	@Autowired
	public ItemTransferServiceImpl(final ItemTransferRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ItemTransfer> findAll() {
		return (List<ItemTransfer>) repository.findAll();
	}

	@Override
	public ItemTransfer save(ItemTransfer ItemTransfer) {
		return repository.save(ItemTransfer);
	}

	@Override
	public ItemTransfer findOne(Long id) {
		return repository.findOne(id);
	}
	
	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}