package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Reviews;
import com.javid.clothingstore.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl {
    @Autowired
    private ReviewsRepository reviewsRepository;

    public Reviews saveReviews(Reviews reviews) {
        return reviewsRepository.save(reviews);
    }

    public List<Reviews> findByProductId(Long id) {
        return reviewsRepository.findByProductId(id);
    }
}
