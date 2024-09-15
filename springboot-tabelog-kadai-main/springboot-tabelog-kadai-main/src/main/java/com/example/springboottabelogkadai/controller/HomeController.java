package com.example.springboottabelogkadai.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springboottabelogkadai.entity.Categories;
import com.example.springboottabelogkadai.entity.Shop;
import com.example.springboottabelogkadai.repository.CategoryRepository;
import com.example.springboottabelogkadai.repository.ShopRepository;

@Controller
public class HomeController {
	private final ShopRepository shopRepository;
	private final CategoryRepository categoryRepository;

	public HomeController(ShopRepository shopRepository, CategoryRepository categoryRepository) {
		this.shopRepository = shopRepository;
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Shop> topShops = shopRepository.findTop10ByOrderByPriceAsc();
		List<Categories> category = categoryRepository.findAll();

		
		model.addAttribute("topShops", topShops);
		model.addAttribute("category", category);

		return "index";
	}

}