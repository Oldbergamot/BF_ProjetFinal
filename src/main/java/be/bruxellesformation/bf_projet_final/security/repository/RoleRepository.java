package be.bruxellesformation.bf_projet_final.security.repository;

import be.bruxellesformation.bf_projet_final.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
