package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exceptions.ReviewNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.ReviewMapper;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Review;
import be.bruxellesformation.bf_projet_final.model.form.review.AddReviewForm;
import be.bruxellesformation.bf_projet_final.model.form.review.ModifyReviewForm;
import be.bruxellesformation.bf_projet_final.repository.ReviewReposiroty;
import be.bruxellesformation.bf_projet_final.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewReposiroty repository;
    private final ReviewMapper mapper;

    public ReviewServiceImpl(ReviewReposiroty repository, ReviewMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ReviewDTO insert(AddReviewForm form) {
        Review r = mapper.formAddReviewFormToEntity(form);
        repository.save(r);
        return mapper.toDto(r);
    }

    @Override
    public ReviewDTO modifyOne(Long id, ModifyReviewForm form) {
        Review r = repository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        r.setPublishedDate(LocalDate.now());
        r.setNote(form.getNote());
        r.setIsAbout(form.getIsAbout());
        r.setContent(form.getContent());

        repository.save(r);

        return mapper.toDto(r);
    }

    @Override
    public ReviewDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ReviewNotFoundException(id)) ;
    }

    @Override
    public ReviewDTO displayReview(Long idReview, boolean b) {
        Review r = repository.findById(idReview).orElseThrow(() -> new ReviewNotFoundException(idReview));
        r.setDisplay(b);
        repository.save(r);
        return mapper.toDto(r);
    }

    @Override
    public List<ReviewDTO> getAll() {
        return null;
    }

    @Override
    public Page<ReviewDTO> getAllWithPagination(int page, int size) {
        return null;
    }

    @Override
    public ReviewDTO partialUpdate(Long id, Map<String, Object> values) {
        return null;
    }
}
