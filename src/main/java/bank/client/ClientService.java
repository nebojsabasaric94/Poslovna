package bank.client;

import java.util.List;

import bank.bank.Bank;

public interface ClientService {

	public List<Client> findAll();

	public List<Client> search(Client client);
	
	public Client findOne(Long id);
	
	public Client save(Client bank);
}
