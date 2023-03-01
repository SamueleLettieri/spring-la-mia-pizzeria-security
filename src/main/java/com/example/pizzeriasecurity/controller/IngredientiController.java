package com.example.pizzeriasecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pizzeriasecurity.model.Ingredienti;
import com.example.pizzeriasecurity.repository.IngredientiRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

	private @Autowired IngredientiRepository ingredientiRepository;
	
	
	@GetMapping()
	public String index(Model model) {
		List<Ingredienti> i = ingredientiRepository.findAll();
		model.addAttribute("ingredienti", i);
		return "ingredienti/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Ingredienti ingredienti = new Ingredienti();
		
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti/create";
	}
	
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("ingredienti") Ingredienti formIngredienti,  BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "ingredienti/create";
		}
		
		ingredientiRepository.save(formIngredienti);
		return "redirect:/ingredienti";
	}
	
	@PostMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		ingredientiRepository.deleteById(id);
		
		
		
		return "redirect:/ingredienti";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
