package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.Book;
import be.bruxellesformation.bf_projet_final.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewReposiroty extends JpaRepository<Review, Long> {

    public List<Review> findReviewsByIsAbout(Book book);
}
