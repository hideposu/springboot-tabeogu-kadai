package com.example.springboottabelogkadai.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springboottabelogkadai.entity.Review;
import com.example.springboottabelogkadai.entity.Store;
import com.example.springboottabelogkadai.entity.User;
import com.example.springboottabelogkadai.form.ReviewEditForm;
import com.example.springboottabelogkadai.form.ReviewForm;
import com.example.springboottabelogkadai.repository.ReviewRepository;
import com.example.springboottabelogkadai.repository.StoreRepository;
import com.example.springboottabelogkadai.security.UserDetailsImpl;
import com.example.springboottabelogkadai.service.ReviewService;

@Controller
@RequestMapping("/stores/{storeId}/reviews")
public class ReviewController {
	private final ReviewRepository reviewRepository;
	private final StoreRepository storeRepository;
	private final ReviewService reviewService;
	
	public ReviewController(ReviewRepository reviewRepository, StoreRepository storeRepository, ReviewService reviewService) {
		this.reviewRepository = reviewRepository;
		this.storeRepository = storeRepository;
		this.reviewService = reviewService;
	}
	
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,  @PageableDefault(page = 0, size = 10, sort = "id")Pageable pageable, Model model) {
		User user = userDetailsImpl.getUser();
	      Page<Review> reviewPage = reviewRepository.findByUserOrderByCreatedAtDesc(user, pageable);
	      
	      model.addAttribute("reviewPage", reviewPage); 
	      
	      return "reviews/index";
	  }
	
	@GetMapping("/register")
	public String register(@PathVariable(name = "storeId") Integer storeId, Model model) {
		  Store store = storeRepository.getReferenceById(storeId);
		  
		  model.addAttribute("store", store);
		  model.addAttribute("reviewForm", new ReviewForm());
		  
		  return "reviews/register";
	  }
	
	@PostMapping("/create")
	  public String create(@PathVariable(name = "storeId") Integer storeId,
			               @AuthenticationPrincipal UserDetailsImpl userdetailsImpl,
			               @ModelAttribute @Validated ReviewForm reviewForm,
			               BindingResult bindingResult,
			               RedirectAttributes redirectAttributes,
			               Model model) {
		  Store store = storeRepository.getReferenceById(storeId);
		  User user = userdetailsImpl.getUser();
		  
		  if(bindingResult.hasErrors()) {
			  model.addAttribute("store", store);
			  return "reviews/register";
		  }
		  
		  reviewService.create(store, user, reviewForm);
		  redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
		  
		  return "redirect:/stores/{storeId}";
	  }
	  
	  @GetMapping("/{id}/edit")
	  public String edit(@PathVariable(name = "id") Integer id, 
			             @PathVariable(name = "storeId") Integer storeId,
			             Model model) {
		  Review review = reviewRepository.getReferenceById(id);
		  Store store = storeRepository.getReferenceById(storeId);
		  ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getScore(), review.getContent());
		  
		  model.addAttribute("review", review);
		  model.addAttribute("store", store);
		  model.addAttribute("reviewEditForm", reviewEditForm);
		  
		  return "reviews/edit";
		  
	  }
	  
	  @PostMapping("/{id}/update")
	  public String update(@PathVariable(name = "storeId") Integer storeId,
			               @PathVariable(name = "id") Integer id,
			               @ModelAttribute @Validated ReviewEditForm reviewEditForm,
			               BindingResult bindingResult,
			               RedirectAttributes redirectAttributes,
			               Model model) {
		  
		  Store store = storeRepository.getReferenceById(storeId);
		  Review review = reviewRepository.getReferenceById(id);
		  
		  if(bindingResult.hasErrors()) {
			  model.addAttribute("store", store);
			  model.addAttribute("review", review);
			  return "reviews/edit";
		  }
		  
		  reviewService.update(reviewEditForm);
		  redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");
		  
		  return "redirect:/stores/{storeId}";
	  }
	  
	  @PostMapping("/{id}/delete")
	  public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		  reviewRepository.deleteById(id);
		  
		  redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
		  
		  return "redirect:/stores/{storeId}";
		  
		  }
}
