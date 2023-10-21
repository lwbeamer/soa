package itmo.corp.authservice.repository;

import itmo.corp.authservice.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}