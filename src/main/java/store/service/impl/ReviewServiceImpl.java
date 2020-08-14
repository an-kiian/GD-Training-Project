package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.ReviewDTO;
import store.mapper.EntityMapper;
import store.model.Product;
import store.model.Review;
import store.repository.ReviewRepository;
import store.service.ProductService;
import store.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private ProductService productService;
    private EntityMapper<Review, ReviewDTO> mapper = (EntityMapper<Review, ReviewDTO>) EntityMapper.getInstance();

    @Autowired
    ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    @Override
    public List<ReviewDTO> getReviews(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().map(review -> mapper.toDTO(review, ReviewDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        Product product = productService.checkProductAndUpdateRating(reviewDTO.getProductId(), reviewDTO.getRating());
        if (product == null)
            return null;
        Review review = mapper.toEntity(reviewDTO, Review.class);
        review.setProduct(product);
        return mapper.toDTO(reviewRepository.save(review), ReviewDTO.class);
    }
}
