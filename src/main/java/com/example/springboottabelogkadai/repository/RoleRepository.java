package com.example.springboottabelogkadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottabelogkadai.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByName(String name);
}
