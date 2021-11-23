package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.review.AddReviewForm;
import be.bruxellesformation.bf_projet_final.model.form.review.ModifyReviewForm;
import be.bruxellesformation.bf_projet_final.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReviewDTO>insert(@Valid @RequestBody AddReviewForm form) {
        return ResponseEntity.ok(service.insert(form));
    }

    @PutMapping("/{review}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReviewDTO>modifyOne(@PathVariable(value = "review") Long id, @RequestBody @Valid ModifyReviewForm form) {
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/{review}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ReviewDTO>getOne(@PathVariable(value = "review") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{review}/display={display}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<ReviewDTO>displayReview(@PathVariable(value = "review") Long id, @PathVariable(value = "display") boolean b) {
        return ResponseEntity.ok(service.displayReview(id, b));
    }

    @DeleteMapping("/{review}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ReviewDTO>deleteReview(@PathVariable(value = "review") Long id) {
        return ResponseEntity.ok(service.deleteReview(id));
    }
}
