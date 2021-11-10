package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.Book;
import be.bruxellesformation.bf_projet_final.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewReposiroty extends JpaRepository<Review, Long> {

    public List<Review> findReviewsByIsAbout(Book book);
}
