package com.example.springboottabelogkadai.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StoreRegisterForm {
	@NotBlank(message = "店舗名を入力してください。")
	private String name;
	
	private MultipartFile imageFile;
	
	@NotBlank(message = "説明を入力してください。")
	private String description;
	
	@NotNull(message = "価格帯を入力してください。")
	private String priceRange;
	
	@NotNull(message = "営業時間を入力してください。")
	private String businessHours;
	
	@NotNull(message = "郵便番号を入力してください。")
	private String postalCode;
	
	@NotNull(message = "住所を入力してください。")
	private String address;
	
	@NotNull(message = "電話番号を入力してください。")
	private String phoneNumber;
}
