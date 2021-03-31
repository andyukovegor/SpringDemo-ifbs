package my.self.demo.data.user;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

import my.self.demo.domain.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	long countByEmail(String email);

	Optional<User> findByEmail(String email);
	Optional<User> findByEmailAndEnabledTrue(String email);
}
