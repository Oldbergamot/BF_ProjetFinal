package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
