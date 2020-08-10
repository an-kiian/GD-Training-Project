package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.dto.ReviewDTO;
import store.service.ReviewService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@ControllerAdvice
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("store/review/{id_product}")
    public List<ReviewDTO> getReviews(@PathVariable Long id_product) {
        return reviewService.getReviews(id_product);
    }

    @PostMapping("/store/review")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDTO addReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }
}

