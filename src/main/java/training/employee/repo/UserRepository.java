package training.employee.repo;

import org.springframework.data.repository.CrudRepository;
import training.employee.models.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
}
