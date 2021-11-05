package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.mapper.ReviewMapper;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.review.AddReviewForm;
import be.bruxellesformation.bf_projet_final.model.form.review.ModifyReviewForm;
import be.bruxellesformation.bf_projet_final.repository.ReviewReposiroty;
import be.bruxellesformation.bf_projet_final.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {

//    private final ReviewReposiroty repository;
//    private final ReviewMapper mapper;


    @Override
    public ReviewDTO insert(AddReviewForm form) {
        return null;
    }

    @Override
    public ReviewDTO modifyOne(Long id, ModifyReviewForm form) {
        return null;
    }

    @Override
    public ReviewDTO getOne(Long id) {
        return null;
    }

    @Override
    public ReviewDTO displayReview(Long idReview, boolean b) {
        return null;
    }
}
