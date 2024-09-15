package com.example.springboottabelogkadai.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springboottabelogkadai.entity.Reservation;
import com.example.springboottabelogkadai.entity.Store;
import com.example.springboottabelogkadai.entity.User;
import com.example.springboottabelogkadai.form.ReservationInputForm;
import com.example.springboottabelogkadai.form.ReservationRegisterForm;
import com.example.springboottabelogkadai.repository.ReservationRepository;
import com.example.springboottabelogkadai.repository.StoreRepository;
import com.example.springboottabelogkadai.security.UserDetailsImpl;
import com.example.springboottabelogkadai.service.ReservationService;

@Controller
public class ReservationController {
	private final ReservationRepository reservationRepository;
	private final StoreRepository storeRepository;
	private final ReservationService reservationService;
	
	public ReservationController(ReservationRepository reservationRepository, StoreRepository storeRepository, ReservationService reservationService) {
		this.reservationRepository = reservationRepository;
		this.storeRepository = storeRepository;
		this.reservationService = reservationService;
	}
	
	@GetMapping("/reservations")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort ="id", direction = Direction.ASC) Pageable pageable, Model model) {
		User user = userDetailsImpl.getUser();
		Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);
		
		model.addAttribute("reservationPage", reservationPage);
		
		return "reservations/index";
	}
	
	@GetMapping("/stores/{id}/reservations/input")
	public String input(@PathVariable(name = "id") Integer id,
			@ModelAttribute @Validated ReservationInputForm reservationInputForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model)
	{
		Store store = storeRepository.getReferenceById(id);
	
	if (bindingResult.hasErrors()) {            
		 model.addAttribute("store", store);            
		 model.addAttribute("errorMessage", "予約内容に不備があります。"); 
	     return "stores/show";
	}
	
	redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);           
	
	return "redirect:/stores/{id}/reservations/confirm";
    }
	
	@GetMapping("/stores/{id}/reservations/confirm")
	public String confirm(@PathVariable(name = "id") Integer id,
	                      @ModelAttribute ReservationInputForm reservationInputForm,
	                      @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,                          
	                      Model model) 
	{        
	    Store store = storeRepository.getReferenceById(id);
	    User user = userDetailsImpl.getUser();
	    
	    ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(store.getId(), user.getId(),reservationInputForm.getReservationDate().toString(), reservationInputForm.getReservationTime(), reservationInputForm.getNumberOfPeople());
	
	    model.addAttribute("store", store);  
	    model.addAttribute("reservationRegisterForm", reservationRegisterForm);       
	
	    return "reservations/confirm";
	}
	
	@PostMapping("/stores/{id}/reservations/create")
	public String create(@ModelAttribute ReservationRegisterForm reservationRegisterForm) {
		reservationService.create(reservationRegisterForm);
		
		return "redirect:/reservations?reserved";
	}
}
