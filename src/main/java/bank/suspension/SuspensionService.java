package bank.suspension;

import java.util.List;

public interface SuspensionService {
	List<Suspension> findAll();

	Suspension save(Suspension suspension);

	Suspension findOne(Long id);
	
	public void delete(Long id);
}
