package com.employee.repo;

import org.springframework.data.repository.CrudRepository;
import com.employee.models.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
}
