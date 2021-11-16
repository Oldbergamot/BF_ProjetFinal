package be.bruxellesformation.bf_projet_final.mapper;

import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Review;
import be.bruxellesformation.bf_projet_final.model.form.review.AddReviewForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewMapper implements BaseMapper <ReviewDTO, AddReviewForm, Review> {

    public Review toEntity(ReviewDTO dto) {
        if(dto == null) return null;
        return Review.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .publishedDate(dto.getPublishedDate())
                .isAbout(dto.getIsAbout())
                .note(dto.getNote())
                .build();
    }

    public ReviewDTO toDto(Review entity) {
        if(entity == null) return null;
        return ReviewDTO.builder()
                .id(entity.getId())
                .note(entity.getNote())
                .content(entity.getContent())
                .isAbout(entity.getIsAbout())
                .publishedDate(entity.getPublishedDate())
                .build();
    }

    public Review fromFormToEntity(AddReviewForm form) {
        if(form == null)return  null;
        Review review = new Review();
        review.setContent(form.getContent());
        review.setIsAbout(form.getIsAbout());
        review.setNote(form.getNote());
        return review;
    }

    public Review formModifyReviewFormToEntity(AddReviewForm form) {
        if(form == null)return  null;
        Review review = new Review();
        review.setContent(form.getContent());
        review.setIsAbout(form.getIsAbout());
        review.setNote(form.getNote());
        return review;
    }


    public List<ReviewDTO> fromListEntityToDto(List<Review> reviews) {
        List<ReviewDTO> results = new ArrayList<>();
        for(Review r : reviews) {
            results.add(toDto(r));
        }
        return results;
    }
}
