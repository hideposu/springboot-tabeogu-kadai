package com.example.springboottabelogkadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottabelogkadai.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
}