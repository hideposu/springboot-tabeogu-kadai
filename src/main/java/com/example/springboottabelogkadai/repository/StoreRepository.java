package com.example.springboottabelogkadai.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottabelogkadai.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
	public Page<Store> findByNameLike(String keyword, Pageable pageable);
	
	public Page<Store> findByAddressLike(String area, Pageable pageable);
	public List<Store> findTop10ByOrderByCreatedAtDesc();
}
