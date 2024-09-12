package com.example.springboottabelogkadai.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottabelogkadai.entity.Review;
import com.example.springboottabelogkadai.entity.Store;
import com.example.springboottabelogkadai.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	public Page<Review> findByStoreOrderByCreatedAtDesc(Store store, Pageable pageable);
	
	public long countByStore(Store store);
	
	public List<Review> findByUserAndStore(User user, Store store);
	
	public List<Review> findTop6ByStoreOrderByCreatedAtDesc(Store store);
	
	public Page<Review> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
