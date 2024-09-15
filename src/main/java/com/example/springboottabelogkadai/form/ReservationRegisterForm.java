package com.example.springboottabelogkadai.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
	private Integer storeId;
	
	private Integer userId;
	
	private String reservationDate;
	
	private String reservationTime;
	
	private Integer numberOfPeople;
}
