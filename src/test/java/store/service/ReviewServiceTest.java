package store.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import store.dto.ReviewDTO;
import store.mapper.EntityMapper;
import store.model.Product;
import store.model.Review;
import store.repository.ProductRepository;
import store.repository.ReviewRepository;
import store.service.impl.ReviewServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest {
    @InjectMocks
    private ReviewServiceImpl service;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ProductRepository productRepository;
    private EntityMapper<Review, ReviewDTO> mapper = (EntityMapper<Review, ReviewDTO>) EntityMapper.getInstance();
    private ReviewDTO REVIEW_DTO = new ReviewDTO(1L, 5, "Test Review");
    private Review REVIEW = new Review();

    @Before
    public void setUp() {
        REVIEW.setId(1L);
        REVIEW.setText("Test Review");
        REVIEW.setRating(5);
        REVIEW.setProduct(new Product());
    }


    @Test
    public void testGetReviews() {
        //given
        List<Review> reviews = new ArrayList<>();
        reviews.add(REVIEW);
        //when
        Mockito.when(reviewRepository.findByProductId(Mockito.anyLong())).thenReturn(reviews);
        //then
        List<ReviewDTO> resultReviewDTO = service.getReviews(1L);
        verify(reviewRepository).findByProductId(1L);
        assertEquals(resultReviewDTO, reviews.stream().map(review -> mapper.toDTO(review, ReviewDTO.class)).collect(Collectors.toList()));
    }

    @Test
    public void testAdd() {
        //when
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(new Product());
        Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(REVIEW);
        //then
        ReviewDTO resultReviewDTO = service.addReview(REVIEW_DTO);
        assertEquals(resultReviewDTO, mapper.toDTO(REVIEW, ReviewDTO.class));
    }

    @Test
    public void testAddForIncorrectId() {
        //when
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(new Product());
        Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(null);
        //then
        ReviewDTO resultReviewDTO = service.addReview(REVIEW_DTO);
        assertNull(resultReviewDTO);
    }
}
