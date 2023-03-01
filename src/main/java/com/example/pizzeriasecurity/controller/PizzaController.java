package com.example.pizzeriasecurity.controller;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pizzeriasecurity.model.Ingredienti;
import com.example.pizzeriasecurity.model.Pizza;
import com.example.pizzeriasecurity.repository.IngredientiRepository;
import com.example.pizzeriasecurity.repository.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

	private @Autowired PizzaRepository pizzaRepository;
	
	private @Autowired IngredientiRepository ingredientiRepository;
	
	@GetMapping
	public String index(@RequestParam(name="keyword", required = false) String keyword, Model model) {
		List<Pizza> elencoPizze;
		
		if (keyword!=null && !keyword.isEmpty()) {
			elencoPizze = pizzaRepository.findByNameLike("%"+keyword+"%");
		} else {
			elencoPizze = pizzaRepository.findAll();
		}

		model.addAttribute("elencoPizze", elencoPizze);
		return"pizze/index";
	}
	
	
		
	@GetMapping("pizze/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		Optional<Pizza> pizza = pizzaRepository.findById(id);
		if(pizza.isEmpty()) {
			return "redirect:/pizze/error";
		}
		model.addAttribute("pizza", pizza.get());
		return "pizze/detail";
	}
	/*
	 * Pizza pizza = pizzaRepository.getReferenceById(id);
		model.addAttribute("pizza", pizza);
		return "pizze/detail";
	 * */
	
	@GetMapping("pizze/create")
	public String create(Model model) {
		Pizza pizza = new Pizza();
		
		List<Ingredienti> ingredientiList = ingredientiRepository.findAll();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredientiList", ingredientiList);
		return "pizze/create";
	}
	
	
	@PostMapping("pizze/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model modele) {
		if (bindingResult.hasErrors()) {
			return "pizze/create";
		}
		pizzaRepository.save(formPizza);
		return"redirect:/";
	}
	
	@GetMapping("pizze/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Pizza> pizza = pizzaRepository.findById(id);
		if(pizza.isEmpty()) {
			return "redirect:/pizze/error";
		}
		
		List<Ingredienti> ingredientiList = ingredientiRepository.findAll();
		
		model.addAttribute("ingredientiList", ingredientiList);
		model.addAttribute("pizza", pizza.get());
		return "pizze/edit";
	}

	@PostMapping("pizze/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "pizze/edit";
		}
		
		pizzaRepository.save(formPizza);
		return"redirect:/";
	}
	
	
	@PostMapping("pizze/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		pizzaRepository.deleteById(id);
		
		return "redirect:/";
		
	}
	
}
