package be.bruxellesformation.bf_projet_final.service;

import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.review.AddReviewForm;
import be.bruxellesformation.bf_projet_final.model.form.review.ModifyReviewForm;

public interface ReviewService {

    ReviewDTO insert(AddReviewForm form);
    ReviewDTO modifyOne(Long id, ModifyReviewForm form);
    ReviewDTO getOne(Long id);
    ReviewDTO displayReview(Long idReview, boolean b);
}
