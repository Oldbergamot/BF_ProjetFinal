package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewReposiroty extends JpaRepository<Review, Long> {
}
