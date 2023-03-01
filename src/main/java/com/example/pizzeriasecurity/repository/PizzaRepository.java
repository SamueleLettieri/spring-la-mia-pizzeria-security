package com.example.pizzeriasecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pizzeriasecurity.model.Pizza;






public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

	public List<Pizza> findByNameLike(String keyword);
	
}
