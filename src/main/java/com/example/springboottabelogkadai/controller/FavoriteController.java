package com.example.springboottabelogkadai.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springboottabelogkadai.entity.Favorite;
import com.example.springboottabelogkadai.entity.Store;
import com.example.springboottabelogkadai.entity.User;
import com.example.springboottabelogkadai.repository.FavoriteRepository;
import com.example.springboottabelogkadai.repository.StoreRepository;
import com.example.springboottabelogkadai.security.UserDetailsImpl;
import com.example.springboottabelogkadai.service.FavoriteService;

@Controller
public class FavoriteController {
	private final StoreRepository storeRepository;
	private final FavoriteService favoriteService;
	private final FavoriteRepository favoriteRepository;
	
	public FavoriteController(StoreRepository storeRepository, FavoriteService favoriteService, FavoriteRepository favoriteRepository) {
		this.storeRepository = storeRepository;
		this.favoriteService = favoriteService;
		this.favoriteRepository = favoriteRepository;
	}
	
	@GetMapping("/favorites")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable, Model model) {
		User user = userDetailsImpl.getUser();
		Page<Favorite> favoritePage = favoriteRepository.findByUserOrderByCreatedAtDesc(user, pageable);
		
		model.addAttribute("favoritePage", favoritePage);
		
		return "favorites/index";
	}
	
	@PostMapping("/stores/{id}/favorites/subscribe")
	public String subscribe(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes, Model model) {
		Store store = storeRepository.getReferenceById(id);
		User user = userDetailsImpl.getUser();
		favoriteService.subscribe(store, user);
		redirectAttributes.addFlashAttribute("successMessage", "お気に入りに登録しました。");
		
		return "redirect:/stores/{id}";
	}
	
	@PostMapping("/stores/{id}/favorites/{favoriteId}/delete")
	public String delete(@PathVariable(name = "favoriteId") Integer id, RedirectAttributes redirectAttributes) {
		favoriteRepository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("successMessage", "お気に入り登録を解除しました。");
		
		return "redirect:/stores/{id}";
	}
}
