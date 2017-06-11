package bank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import bank.user.User;
import bank.user.UserService;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		
		return new CustomUserDetails(user);
	}

}
