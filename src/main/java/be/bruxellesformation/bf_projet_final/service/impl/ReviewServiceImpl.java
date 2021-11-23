package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.AuthorisationException;
import be.bruxellesformation.bf_projet_final.exception.model.ReviewNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.ReviewMapper;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Book;
import be.bruxellesformation.bf_projet_final.model.entity.Publisher;
import be.bruxellesformation.bf_projet_final.model.entity.Review;
import be.bruxellesformation.bf_projet_final.model.form.review.AddReviewForm;
import be.bruxellesformation.bf_projet_final.model.form.review.ModifyReviewForm;
import be.bruxellesformation.bf_projet_final.repository.ReviewReposiroty;
import be.bruxellesformation.bf_projet_final.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewReposiroty repository;
    private final ReviewMapper mapper;

    public ReviewServiceImpl(ReviewReposiroty repository, ReviewMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public ReviewDTO insert(AddReviewForm form) {
        Review r = mapper.fromFormToEntity(form);
        repository.save(r);
        return mapper.toDto(r);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public ReviewDTO modifyOne(Long id, ModifyReviewForm form) {
        Review r = repository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        if(!r.isDisplay() && !isRoleAdmin()) throw new AuthorisationException(id);
        r.setPublishedDate(LocalDate.now());
        r.setNote(form.getNote());
        r.setIsAbout(form.getIsAbout());
        r.setContent(form.getContent());

        repository.save(r);

        return mapper.toDto(r);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public ReviewDTO getOne(Long id) {
        Review r = repository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
         if(!r.isDisplay() && !isRoleAdmin()) throw new AuthorisationException(id);

        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ReviewDTO displayReview(Long idReview, boolean b) {
        Review r = repository.findById(idReview).orElseThrow(() -> new ReviewNotFoundException(idReview));
        r.setDisplay(b);
        repository.save(r);
        return mapper.toDto(r);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ReviewDTO> getAll() {

        if(isRoleUser()) return repository.findAll()
                .stream()
                .filter(Review::isDisplay)
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<ReviewDTO> getAllWithPagination(int page, int size) {

        Page<Review> result;
        if(isRoleUser()) result = repository.findAllByDisplay(PageRequest.of(page, size));
        else result = repository.findAll(PageRequest.of(page, size));

        return result.map(mapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public ReviewDTO partialUpdate(Long id, Map<String, Object> values) {
        Review r = repository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));

        for(String s : values.keySet()) {
            switch (s) {
                case "note" :
                    short note = (short) values.get(s);
                    if(note<0) throw new IllegalArgumentException();
                    r.setNote(note);
                    repository.save(r);
                    break;
                case "content":
                    String content = (String) values.get(s);
                    if(content.isBlank()) throw new IllegalArgumentException();
                    r.setContent(content);
                    repository.save(r);
                    break;
                case "isAbout" :
                    Book isAbout = (Book) values.get(s);
                    if(isAbout == null) throw new IllegalArgumentException();
                    r.setIsAbout(isAbout);
                    repository.save(r);
                    break;
            }
        }
        r.setPublishedDate(LocalDate.now());
        return mapper.toDto(r);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ReviewDTO deleteReview(Long id) {
        Review toRemove = repository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
        repository.delete(toRemove);
        return mapper.toDto(toRemove);
    }

    private boolean isRoleAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.equals("ROLE_ADMIN"));
    }

    private boolean isRoleUser() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.equals("ROLE_USER"));
    }
}
