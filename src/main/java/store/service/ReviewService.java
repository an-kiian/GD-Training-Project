package store.service;

import store.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    ReviewDTO addReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getReviews(Long idProduct);
}
