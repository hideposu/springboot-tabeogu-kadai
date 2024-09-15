package com.example.springboottabelogkadai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottabelogkadai.entity.Favorite;
import com.example.springboottabelogkadai.entity.Shop;
import com.example.springboottabelogkadai.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	Optional<Favorite> findByShopAndUser(Shop shop, User user);

	List<Favorite> findByUser(User user);

	void deleteAllByUser(User user); // 追加：特定のユーザーのお気に入りをすべて削除

	public Page<Favorite> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

	public Page<Favorite> findByUserOrderByCreatedAtAsc(User user, Pageable pageable);
}