package com.example.pizzeriasecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizze/errore")
public class ErroreControllo {

	@GetMapping()
	public String error() {
		return "error";
	}
	
}
