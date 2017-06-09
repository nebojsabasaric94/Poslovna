package bank.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repository;
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<User> findAll() {
		return (List<User>) repository.findAll();
	}

	@Override
	public User findByUsername(String username) {

		Query query = manager.createQuery("SELECT u FROM User u WHERE u.username=?1");
		query.setParameter(1, username);
		User user = (User) query.getSingleResult();
		return user;
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

}
