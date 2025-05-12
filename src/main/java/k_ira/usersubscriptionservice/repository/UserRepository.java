package k_ira.usersubscriptionservice.repository;

import k_ira.usersubscriptionservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
