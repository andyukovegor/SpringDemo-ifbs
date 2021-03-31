package my.self.demo.domain.user;


import java.util.List;

import my.self.demo.domain.model.User;

public interface UserService {

	List<User> getList();

	boolean isUserWithEmailExist(String email);
}
