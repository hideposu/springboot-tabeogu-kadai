package com.example.springboottabelogkadai.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springboottabelogkadai.entity.User;
import com.example.springboottabelogkadai.repository.UserRepository;
import com.example.springboottabelogkadai.security.UserDetailsImpl;
import com.example.springboottabelogkadai.service.StripeService;
import com.example.springboottabelogkadai.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
	private StripeService stripeService;
	private UserRepository userRepository;
	private UserService userService;
	
	public SubscriptionController(StripeService stripeService, UserRepository userRepository, UserService userService) {
		this.stripeService = stripeService;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	@GetMapping
	public String index(Model model, HttpServletRequest httpServletRequest) {
		String sessionId = stripeService.careateStripeSession(httpServletRequest);
		
		model.addAttribute("sessionId", sessionId);
		
		return "subscription/index";
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "subscription/cancel";
	}
	
	@GetMapping("/success")
	public String create(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		userService.subscriptionCreate(user);
		
		redirectAttributes.addFlashAttribute("successMessage", "有料会員登録が完了しました。");
		
		return "auth/login";
	}
	
	@PostMapping("/delete")
	public String delete(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		userService.subscriptionCancel(user);
		
		redirectAttributes.addFlashAttribute("successMessage", "サブスクリプションを解約しました。");
	
		return "auth/login";
	}
}
