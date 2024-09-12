package com.example.springboottabelogkadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottabelogkadai.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository< VerificationToken, Integer> {
	public VerificationToken findByToken(String token);
}
