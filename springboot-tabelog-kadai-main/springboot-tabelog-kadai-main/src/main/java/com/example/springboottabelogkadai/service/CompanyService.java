package com.example.springboottabelogkadai.service;

import org.springframework.stereotype.Service;

import com.example.springboottabelogkadai.entity.Company;
import com.example.springboottabelogkadai.form.CompanyEditForm;
import com.example.springboottabelogkadai.repository.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyService {
	private final CompanyRepository companyRepository;
	
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	@Transactional
	public void update(CompanyEditForm companyEditForm) {
		Company company = companyRepository.getReferenceById(companyEditForm.getId());
		
		company.setName(companyEditForm.getName());
		company.setPostalCode(companyEditForm.getPostalCode());
		company.setLocation(companyEditForm.getLocation());
		company.setRepresentative(companyEditForm.getRepresentative());
		company.setEstablishment(companyEditForm.getEstablishment());
		company.setCapital(companyEditForm.getCapital());
		company.setContent(companyEditForm.getContent());
		
		companyRepository.save(company);
		
	}
}