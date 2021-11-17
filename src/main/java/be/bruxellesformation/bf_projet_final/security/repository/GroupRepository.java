package be.bruxellesformation.bf_projet_final.security.repository;

import be.bruxellesformation.bf_projet_final.security.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
