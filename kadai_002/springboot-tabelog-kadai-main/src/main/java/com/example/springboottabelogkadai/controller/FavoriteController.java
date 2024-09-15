package com.example.springboottabelogkadai.controller;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springboottabelogkadai.entity.Categories;
import com.example.springboottabelogkadai.entity.Favorite;
import com.example.springboottabelogkadai.entity.Shop;
import com.example.springboottabelogkadai.entity.User;
import com.example.springboottabelogkadai.repository.CategoryRepository;
import com.example.springboottabelogkadai.repository.FavoriteRepository;
import com.example.springboottabelogkadai.repository.ShopRepository;
import com.example.springboottabelogkadai.security.UserDetailsImpl;
import com.example.springboottabelogkadai.service.FavoriteService;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {
	private final ShopRepository shopRepository;
	private final CategoryRepository categoryRepository;
	private final FavoriteRepository favoriteRepository;
	private final FavoriteService favoriteService;

	public FavoriteController(ShopRepository shopRepository, CategoryRepository categoryRepository,
			FavoriteRepository favoriteRepository, FavoriteService favoriteService) {
		this.shopRepository = shopRepository;
		this.categoryRepository = categoryRepository;
		this.favoriteRepository = favoriteRepository;
		this.favoriteService = favoriteService;
	}

	@GetMapping
	public String favorite(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@RequestParam(name = "order", required = false) String order,
			@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
			Model model) {

		User user = userDetailsImpl.getUser();
		Page<Favorite> favoritePage;
		List<Categories> category = categoryRepository.findAll();

		boolean isOrder = Objects.nonNull(order) && order.equals("createdAtAsc");

		if (isOrder) {
			favoritePage = favoriteRepository.findByUserOrderByCreatedAtAsc(user, pageable);
		} else {
			favoritePage = favoriteRepository.findByUserOrderByCreatedAtDesc(user, pageable);
		}
		model.addAttribute("favoritePage", favoritePage);
		model.addAttribute("categories", category);
		model.addAttribute("order", order);

		return "user/favorite";
	}

	//お気に入り登録
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<String> addFavorite(@RequestParam("shopId") Integer shopId,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userDetailsImpl.getUser();
		Optional<Shop> shopOpt = shopRepository.findById(shopId);
	    
		if (shopOpt.isPresent()) {

			favoriteService.addFav(shopOpt.get(), user);
	        return ResponseEntity.ok().build();
	    
		} else {
	        return ResponseEntity.status(404).body("ショップが見つかりません");
		}
	}

	//お気に入り削除
	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<String> deleteFav(@RequestParam("shopId") Integer shopId,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userDetailsImpl.getUser();
		Optional<Shop> shopOpt = shopRepository.findById(shopId);

	    if (shopOpt.isPresent()) {
	        
	    	favoriteService.deleteFav(shopOpt.get(), user);
	        return ResponseEntity.ok().build();

	    } else {
	        return ResponseEntity.status(404).body("ショップが見つかりません");
	    }
	}
}