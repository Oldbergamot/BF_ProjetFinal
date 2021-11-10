package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
