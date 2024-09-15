package com.example.springboottabelogkadai.form;

import org.hibernate.validator.constraints.Length;

import com.example.springboottabelogkadai.validation.Password;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordChangeForm {
	@NotBlank(message = "パスワードを入力してください。")
	@Length(min = 8, message = "パスワードは8文字以上で入力してください。")
	@Password //カスタムバリデーション
	private String password;
	
	@NotBlank(message = "パスワード（確認用）を入力してください。")
	@Password //カスタムバリデーション
	private String passwordConfirmation;
}