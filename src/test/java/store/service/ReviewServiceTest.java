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
import store.repository.ReviewRepository;
import store.service.impl.ReviewServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
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
    private ProductService productService;
    private EntityMapper<Review, ReviewDTO> mapper = (EntityMapper<Review, ReviewDTO>) EntityMapper.getInstance();
    private static final double RATING = 5;
    private static final Long ID = 1L;
    private static final String TEXT = "Test Review";
    private ReviewDTO reviewDto;
    private Review review;

    @Before
    public void setUp() {
        reviewDto = new ReviewDTO(ID, RATING, TEXT);
        review = new Review();
        review.setId(ID);
        review.setText(TEXT);
        review.setRating(RATING);
        review.setProduct(new Product());
    }


    @Test
    public void testGetReviews() {
        //given
        List<Review> reviews = Collections.singletonList(review);
        //when
        Mockito.when(reviewRepository.findByProductId(Mockito.anyLong())).thenReturn(reviews);
        //then
        List<ReviewDTO> resultReviewDTO = service.getReviews(ID);
        verify(reviewRepository).findByProductId(ID);
        assertEquals(resultReviewDTO, reviews.stream().map(review -> mapper.toDTO(review, ReviewDTO.class)).collect(Collectors.toList()));
    }

    @Test
    public void testAdd() {
        //when
        Mockito.when(productService.checkProductAndUpdateRating(Mockito.anyLong(), Mockito.anyDouble())).thenReturn(new Product());
        Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(review);
        //then
        ReviewDTO resultReviewDTO = service.addReview(reviewDto);
        assertEquals(resultReviewDTO, mapper.toDTO(review, ReviewDTO.class));
    }

    @Test
    public void testAddForIncorrectId() {
        //when
        Mockito.when(productService.checkProductAndUpdateRating(Mockito.anyLong(), Mockito.anyDouble())).thenReturn(null);
        Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(null);
        //then
        ReviewDTO resultReviewDTO = service.addReview(reviewDto);
        assertNull(resultReviewDTO);
    }
}
