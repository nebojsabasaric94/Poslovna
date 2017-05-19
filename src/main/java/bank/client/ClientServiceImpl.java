package bank.client;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository repository;

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return (List<Client>) repository.findAll();
	}
}
