package com.example.springboottabelogkadai.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotBlank(message = "予約日を選択してください。")
	private String fromReservationDate;
	
	@NotNull(message = "予約時間を選択してください。")
	private String reservationTime;
	
	@NotNull(message = "予約人数を入力してください。")
	@Min(value = 1, message = "予約人数は1人以上に設定してください。")
	private Integer numberOfPeople;
	
	// 予約日を取得する
	public LocalDate getReservationDate() {
		String[] reservationDate = getFromReservationDate().split("  ");
		return LocalDate.parse(reservationDate[0]);
	}
		 
	public static Integer fromString(String value) {
		String[] timeParts = value.split(":");
		return Integer.parseInt(timeParts[0]);
	}
}