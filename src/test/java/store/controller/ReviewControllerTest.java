package store.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import store.dto.ReviewDTO;
import store.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTest {
    @Mock
    private ReviewService reviewService;
    @InjectMocks
    private ReviewController reviewController;
    private ReviewDTO review = new ReviewDTO(1L, 5, "Test Review");

    @Test
    public void testGetProducts() {
        //given
        List<ReviewDTO> reviews = new ArrayList<>();
        reviews.add(review);
        //when
        Mockito.when(reviewService.getReviews(Mockito.anyLong())).thenReturn(reviews);
        //then
        List<ReviewDTO> resultList = reviewController.getReviews(1L);
        assertEquals(resultList, reviews);
    }

    @Test
    public void testAddProduct() {
        //when
        Mockito.when(reviewService.addReview(Mockito.any(ReviewDTO.class))).thenReturn(review);
        //then
        ReviewDTO resultReview = reviewController.addReview(review);
        assertEquals(resultReview, review);
    }

}
