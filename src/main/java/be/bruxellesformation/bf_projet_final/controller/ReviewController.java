package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.review.AddReviewForm;
import be.bruxellesformation.bf_projet_final.model.form.review.ModifyReviewForm;
import be.bruxellesformation.bf_projet_final.service.ReviewService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ReviewDTO>insert(@Valid @RequestBody AddReviewForm form) {
        return ResponseEntity.ok(service.insert(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO>modifyOne(@PathVariable Long id, @RequestBody @Valid ModifyReviewForm form) {
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO>getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{id}_{b}")
    public ResponseEntity<ReviewDTO>displayReview(@PathVariable Long id, @PathVariable boolean b) {
        return ResponseEntity.ok(service.displayReview(id, b));
    }
}
