package bank.interbankTransfer;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class InterbankTransferServiceImpl implements InterbankTransferService {
	private final InterbankTransferRepository repository;

	@Autowired
	public InterbankTransferServiceImpl(final InterbankTransferRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<InterbankTransfer> findAll() {
		return (List<InterbankTransfer>) repository.findAll();
	}

	@Override
	public InterbankTransfer save(InterbankTransfer interbankTransfer) {
		return repository.save(interbankTransfer);
	}

	@Override
	public InterbankTransfer findOne(Long id) {
		return repository.findOne(id);
	}
	
	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}