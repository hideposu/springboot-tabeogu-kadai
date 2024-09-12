package com.example.springboottabelogkadai.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboottabelogkadai.entity.Favorite;
import com.example.springboottabelogkadai.entity.Store;
import com.example.springboottabelogkadai.entity.User;
import com.example.springboottabelogkadai.repository.FavoriteRepository;

@Service
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;
	
	public FavoriteService(FavoriteRepository favoriteRepository) {
		this.favoriteRepository = favoriteRepository;
	}
	
	// お気に入り登録・追加
	@Transactional
	public void subscribe(Store store, User user) {
		Favorite favorite = new Favorite();
		
		favorite.setStore(store);
		favorite.setUser(user);
		
		favoriteRepository.save(favorite);
	}
	
	public boolean favoriteJudge(Store store, User user) {
		Favorite favorite = favoriteRepository.findByStoreAndUser(store, user);
		return favorite != null;
	}
}
