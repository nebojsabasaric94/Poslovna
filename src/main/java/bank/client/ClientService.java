package bank.client;

import java.util.List;

public interface ClientService {

	public List<Client> findAll();
	public List<Client> search(Client client);
}
