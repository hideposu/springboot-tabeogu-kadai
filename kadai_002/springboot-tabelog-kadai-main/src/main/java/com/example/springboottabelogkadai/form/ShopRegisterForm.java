package com.example.springboottabelogkadai.form;

import org.springframework.web.multipart.MultipartFile;

import com.example.springboottabelogkadai.validation.PhoneNumber;
import com.example.springboottabelogkadai.validation.PostalCode;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShopRegisterForm {
	@NotBlank(message = "店舗名を入力してください。")
	private String name;

	private MultipartFile imageFile;

	@NotNull(message = "カテゴリーを選択してください。")
	private Integer categoriesId;

	@NotBlank(message = "説明を入力してください。")
	private String description;

	@NotBlank(message = "郵便番号を入力してください。")
	@PostalCode //カスタムバリデーション
	private String postalCode;

	@NotBlank(message = "住所を入力してください。")
	private String address;

	@NotBlank(message = "電話番号を入力してください。")
	@PhoneNumber //カスタムバリデーション
	private String phoneNumber;

	@NotBlank(message = "開店時間を入力してください。")
	private String openTime;

	@NotBlank(message = "閉店時間を入力してください。")
	private String closeTime;

	private String regularHoliday;

	@NotNull(message = "価格帯を入力してください。")
	@Min(value = 10, message = "価格帯は10円以上に設定してください。")
	private Integer price;

	@NotNull(message = "座席数を入力してください。")
	private Integer seats;

	private String shopSite;
}