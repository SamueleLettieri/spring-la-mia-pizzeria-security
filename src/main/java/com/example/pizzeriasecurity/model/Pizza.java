package com.example.pizzeriasecurity.model;


import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="pizzas")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Il nome non puo essere vuoto")
	@NotEmpty(message = "Il nome non puo essere vuoto")
	@Column(nullable = false)
	private String name;
	
	@NotNull(message = "La descrizione non puo essere vuota")
	@NotEmpty(message = "La descrizione non puo essere vuota")
	@Column(nullable = false)
	private String description;
	
	@NotNull(message = "La foto non puo essere vuota")
	@NotEmpty(message = "La foto non puo essere vuota")
	@Column(nullable = false)
	private String photo;
	
	@DecimalMin("0.01")
	@NotNull(message = "Il prezzo non puo essere vuoto")
	@Column(nullable = false)
	private double  price;
	
	@OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE)
	private List<OfferteSpeciali> offerteSpecialis;
	
	@ManyToMany
//	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private List<Ingredienti> ingredientis;
	
	public List<Ingredienti> getIngredientis() {
		return ingredientis;
	}

	public void setIngredientis(List<Ingredienti> ingredientis) {
		this.ingredientis = ingredientis;
	}
	
	public List<OfferteSpeciali> getOfferteSpecialis() {
		return offerteSpecialis;
	}

	public void setOfferteSpecialis(List<OfferteSpeciali> offerteSpecialis) {
		this.offerteSpecialis = offerteSpecialis;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public double  getPrice() {
		return price;
	}
	
	public void setPrice(double  price) {
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
