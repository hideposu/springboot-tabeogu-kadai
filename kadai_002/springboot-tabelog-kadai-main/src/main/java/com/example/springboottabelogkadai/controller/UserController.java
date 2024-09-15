package com.example.springboottabelogkadai.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springboottabelogkadai.entity.User;
import com.example.springboottabelogkadai.form.UserEditForm;
import com.example.springboottabelogkadai.repository.ReservationRepository;
import com.example.springboottabelogkadai.repository.UserRepository;
import com.example.springboottabelogkadai.security.UserDetailsImpl;
import com.example.springboottabelogkadai.service.FavoriteService;
import com.example.springboottabelogkadai.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserRepository userRepository;
	private final UserService userService;
	private final ReservationRepository reservationRepository;
	private final FavoriteService favoriteService;

	public UserController(UserRepository userRepository, UserService userService,
			ReservationRepository reservationRepository, FavoriteService favoriteService) {

		this.userRepository = userRepository;
		this.userService = userService;
		this.reservationRepository = reservationRepository;
		this.favoriteService = favoriteService;
	}

	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());

		model.addAttribute("user", user);

		return "user/index";
	}
	
	@GetMapping("/stripeEdit")
	public String stripeEdit() {

		return "user/stripeEdit";
	}

	@GetMapping("/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		UserEditForm userEditForm = new UserEditForm(user.getId(), user.getName(), user.getFurigana(),
				user.getBirthday(), user.getPhoneNumber(), user.getProfession(), user.getMail());

		model.addAttribute("userEditForm", userEditForm);

		return "user/edit";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute @Validated UserEditForm userEditForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		//メールアドレスが変更されており、かつ登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
		if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getMail())) {

			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "mail", "すでに登録済みのメールアドレスです。");
			bindingResult.addError(fieldError);

		}

		if (bindingResult.hasErrors()) {

			return "user/edit";

		}

		userService.update(userEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");

		return "redirect:/user";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("id") Integer id, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes, UserDetailsImpl userDetailsImpl) {
		
		User user = userDetailsImpl.getUser();
		//ユーザー及び予約情報の削除
		reservationRepository.deleteByUserId(id);
		userRepository.deleteById(id);
		favoriteService.allDeleteFav(user);

		//ログアウト処理
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		redirectAttributes.addFlashAttribute("successMessage", "アカウントとアカウントに紐付くデータを削除しました。");
		return "redirect:/";
	}

}