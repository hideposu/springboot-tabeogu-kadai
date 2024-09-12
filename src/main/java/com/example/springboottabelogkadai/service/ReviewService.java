package com.example.springboottabelogkadai.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboottabelogkadai.entity.Review;
import com.example.springboottabelogkadai.entity.Store;
import com.example.springboottabelogkadai.entity.User;
import com.example.springboottabelogkadai.form.ReviewEditForm;
import com.example.springboottabelogkadai.form.ReviewForm;
import com.example.springboottabelogkadai.repository.ReviewRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	// 登録用
	@Transactional
	public void create(Store store, User user, ReviewForm reviewForm) {
		Review review = new Review();

		review.setStore(store);
		review.setUser(user);
		review.setScore(reviewForm.getScore());
		review.setContent(reviewForm.getContent());
		
		reviewRepository.save(review);
	}
	
	// 更新用
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
		
		review.setScore(reviewEditForm.getScore());
		review.setContent(reviewEditForm.getContent());
		
		reviewRepository.save(review);
	}
	
	public boolean reviewJudge(Store store, User user) {
		List<Review> reviewList =  reviewRepository.findByUserAndStore(user, store);
        return reviewList.size()>0;
	}
}