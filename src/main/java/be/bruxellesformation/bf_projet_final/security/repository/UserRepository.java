package be.bruxellesformation.bf_projet_final.security.repository;

import be.bruxellesformation.bf_projet_final.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
