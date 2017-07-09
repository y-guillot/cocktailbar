package fr.formation.cocktailbar.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.cocktailbar.entity.Ingredient;

//@Repository
public interface IngredientDao extends JpaRepository<Ingredient, Integer>{

	List<Ingredient> findAllByCocktailId(final Integer cocktailId);
	
	@Transactional
	void deleteAllByCocktailId(Integer id);
}
