package fr.formation.cocktailbar.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.cocktailbar.entity.Ingredient;

public interface IngredientDao extends JpaRepository<Ingredient, Integer>{

	List<Ingredient> findAllByCocktailId(final Integer cocktailId);
	
	/**
	 * Annotation @Transactionnal is required when database is modified.
	 * 
	 * @param id
	 */
	@Transactional
	void deleteAllByCocktailId(Integer id);
}
