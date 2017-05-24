package bank.interbankTransfer;

import java.sql.Date;
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

	@Override
	public List<InterbankTransfer> search(InterbankTransfer interbankTransfer) {
		
		String date = "";
		if(interbankTransfer.getDate() != null)
			date = new Date(interbankTransfer.getDate().getTime()).toString();
		
		String sum = "";
		if(interbankTransfer.getSum() != null){
			sum = ""+interbankTransfer.getSum();
			String []splitted = sum.split("\\.");
			if(splitted[1].equals("0"))
				sum = splitted[0];
		}
		
		String bank = "%";
		if(interbankTransfer.getBank().getId() != null)
			bank = "" + interbankTransfer.getBank().getId();
		
		String senderBank = "%";
		if(interbankTransfer.getSenderBank().getId() != null)
			senderBank = ""+interbankTransfer.getSenderBank().getId();
		return repository.search(interbankTransfer.getTypeOfMessage(), date, sum, bank, senderBank);
	}
}