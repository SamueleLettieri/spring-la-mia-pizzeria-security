package com.example.pizzeriasecurity.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzeriasecurity.model.Pizza;
import com.example.pizzeriasecurity.repository.PizzaRepository;


@RestController
@CrossOrigin
@RequestMapping("/api/pizze")
public class PizzaRestController {

	private @Autowired PizzaRepository pizzaRepository;
	
	@GetMapping()
	public List<Pizza> index(@RequestParam(name="pizza", required = false) String pizza) {
		
		if (pizza!=null && !pizza.isEmpty()) {
			return pizzaRepository.findByNameLike("%"+pizza+"%");
		} else {
			return pizzaRepository.findAll();
		}
	 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> detail(@PathVariable("id") Integer id){
		Optional<Pizza> pizza = pizzaRepository.findById(id);
		
		if (pizza.isPresent()) {
			return new ResponseEntity<Pizza>(pizza.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
		}
	} 
	
	@PostMapping("/create")
	public Pizza create(@RequestBody Pizza pizza) {
		return pizzaRepository.save(pizza);
	}
	
	
	@PutMapping("update/{id}")
	public Pizza update(@RequestBody Pizza pizza, @PathVariable("id") Integer id) {
		
		pizza.setId(id);
		
		return pizzaRepository.save(pizza);
	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable("id") Integer id){
		pizzaRepository.deleteById(id);
	}
	
}

