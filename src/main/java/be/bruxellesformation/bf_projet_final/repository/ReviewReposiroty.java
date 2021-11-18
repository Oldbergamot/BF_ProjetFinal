package be.bruxellesformation.bf_projet_final.repository;

import be.bruxellesformation.bf_projet_final.model.entity.Book;
import be.bruxellesformation.bf_projet_final.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewReposiroty extends JpaRepository<Review, Long> {

    public List<Review> findReviewsByIsAbout(Book book);

    @Query("select r from Review r where r.isAbout = ?1 and r.display = true")
    public List<Review> findReviewsByIsAboutAndDisplay(Book book);

    @Query("select r from Review r where r.display = true")
    Page<Review> findAllByDisplay(PageRequest page);
}
