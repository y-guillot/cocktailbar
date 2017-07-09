package fr.formation.cocktailbar.entity;

public class Cocktail {

	private Integer id;
	private String name;
	private Float price;
	private Boolean withAlcohol;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Boolean getWithAlcohol() {
		return withAlcohol;
	}
	
	public void setWithAlcohol(Boolean withAlcohol) {
		this.withAlcohol = withAlcohol;
	}
}
