package bank.suspension;

import java.util.List;

public interface SuspensionService {
	List<Suspension> findAll();

	public Suspension save(Suspension suspension);

	public Suspension findOne(Long id);
	
	public void delete(Long id);
	
	public List<Suspension> search(Suspension suspension);
}
