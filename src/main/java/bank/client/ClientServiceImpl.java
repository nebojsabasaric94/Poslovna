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

	@Override
	public List<Client> search(Client client) {
		String place_id = "%";
		if(client.getResidence() != null)
			place_id = ""+client.getResidence().getId();
		// TODO Auto-generated method stub
		return repository.search(client.getFirstName(), client.getLastName(), client.getAddress(),client.getEmail(), client.getPhone(), client.getAddressForStatements(), client.getJmbg(), place_id, client.getTypeOfClient(), client.isEmailStatements());
	}
}
