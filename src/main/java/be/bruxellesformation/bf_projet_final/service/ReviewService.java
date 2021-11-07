package be.bruxellesformation.bf_projet_final.service;

import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.review.AddReviewForm;
import be.bruxellesformation.bf_projet_final.model.form.review.ModifyReviewForm;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    ReviewDTO insert(AddReviewForm form);
    ReviewDTO modifyOne(Long id, ModifyReviewForm form);
    ReviewDTO getOne(Long id);
    ReviewDTO displayReview(Long idReview, boolean b);

    List<ReviewDTO> getAll();
    Page<ReviewDTO> getAllWithPagination(int page, int size);
    ReviewDTO partialUpdate(Long id, Map<String, Object> values);
}
