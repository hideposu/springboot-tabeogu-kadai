package com.example.springboottabelogkadai.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboottabelogkadai.entity.Reservation;
import com.example.springboottabelogkadai.entity.Shop;
import com.example.springboottabelogkadai.entity.User;
import com.example.springboottabelogkadai.form.ReservationEditForm;
import com.example.springboottabelogkadai.form.ReservationRegisterForm;
import com.example.springboottabelogkadai.repository.ReservationRepository;
import com.example.springboottabelogkadai.repository.ShopRepository;
import com.example.springboottabelogkadai.repository.UserRepository;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final ShopRepository shopRepository;
	private final UserRepository userRepository;

	public ReservationService(ReservationRepository reservationRepository, ShopRepository shopRepository,
			UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.shopRepository = shopRepository;
		this.userRepository = userRepository;

	}

	@Transactional
	public void create(ReservationRegisterForm reservationRegisterForm) {
		Reservation reservation = new Reservation();
		Shop shop = shopRepository.getReferenceById(reservationRegisterForm.getShopId());
		User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
		LocalDate reservationDate = LocalDate.parse(reservationRegisterForm.getReservationDate());
		LocalTime reservationTime = LocalTime.parse(reservationRegisterForm.getReservationTime());

		reservation.setShop(shop);
		reservation.setUser(user);
		reservation.setReservationDate(reservationRegisterForm.getReservationDate());
		reservation.setReservationTime(reservationRegisterForm.getReservationTime());
		reservation.setReservationCount(reservationRegisterForm.getReservationCount());

		reservationRepository.save(reservation);
	}

	@Transactional
	public void update(ReservationEditForm reservationEditForm) {
		if (reservationEditForm.getShopId() == null) {
			throw new IllegalArgumentException("Shop ID must not be null");
		}
		if (reservationEditForm.getUserId() == null) {
			throw new IllegalArgumentException("User ID must not be null");
		}
		Reservation reservation = reservationRepository.getReferenceById(reservationEditForm.getId());
		Shop shop = shopRepository.getReferenceById(reservationEditForm.getShopId());
		User user = userRepository.getReferenceById(reservationEditForm.getUserId());
		LocalDate reservationDate = LocalDate.parse(reservationEditForm.getReservationDate());
		LocalTime reservationTime = LocalTime.parse(reservationEditForm.getReservationTime());

		reservation.setShop(shop);
		reservation.setUser(user);
		reservation.setReservationDate(reservationEditForm.getReservationDate());
		reservation.setReservationTime(reservationEditForm.getReservationTime());
		reservation.setReservationCount(reservationEditForm.getReservationCount());

		reservationRepository.save(reservation);
	}
}