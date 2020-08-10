package store.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.ReviewDTO;
import store.mapper.EntityMapper;
import store.model.Product;
import store.model.Review;
import store.repository.ProductRepository;
import store.repository.ReviewRepository;
import store.service.ProductService;
import store.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private EntityMapper<Review, ReviewDTO> mapper = (EntityMapper<Review, ReviewDTO>) EntityMapper.getInstance();

    @Autowired
    ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ReviewDTO> getReviews(Long productId) {
        LOGGER.info("ReviewServiceImpl.getReviews:getReviews by product id: " + productId);
        List<Review> reviews = reviewRepository.findByProductId(productId);
        if (reviews == null) {
            LOGGER.error("ReviewServiceImpl.getReviews:there aren't reviews for product with id  " + productId);
            return null;
        }
        return reviews.stream().map(review -> mapper.toDTO(review, ReviewDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        LOGGER.info("ReviewServiceImpl.addReview:add new review: " + reviewDTO);
        Product product = productRepository.findById(reviewDTO.getProductId());
        if (product == null) {
            LOGGER.error("ReviewServiceImpl.addReview:there aren't product with id " + reviewDTO.getProductId());
            return null;
        }
        Review review = mapper.toEntity(reviewDTO, Review.class);
        review.setProduct(product);
        return mapper.toDTO(reviewRepository.save(review), ReviewDTO.class);
    }
}
