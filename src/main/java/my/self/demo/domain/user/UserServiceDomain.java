package my.self.demo.domain.user;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.self.demo.data.user.UserRepository;
import my.self.demo.domain.model.User;

@Service
public class UserServiceDomain implements UserService {

	@Autowired
	UserRepository userService;
	
	@Override
	public List<User> getList() {
		List<User> users = new ArrayList<>();
		userService.findAll().forEach(users::add);
		
		return users;
	}

	@Override
	public boolean isUserWithEmailExist(String email) {
		return userService.countByEmail(email) != 0 ? true : false;
	}

}