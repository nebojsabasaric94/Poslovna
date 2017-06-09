package bank.user;

import java.util.List;

public interface UserService {
	
	public List<User> findAll();
	
	public User findByUsername(String username);
	
	public User save(User user);

}
