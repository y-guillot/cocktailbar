package fr.formation.cocktailbar.entity;

public class Ingredient {
	
	private Integer id;
	private Cocktail cocktail;
	private Product product;
	private Float quantity;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public Cocktail getCocktail() {
		return cocktail;
	}
	public void setCocktail(Cocktail cocktail) {
		this.cocktail = cocktail;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}
