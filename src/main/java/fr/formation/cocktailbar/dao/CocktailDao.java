package fr.formation.cocktailbar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.cocktailbar.entity.Cocktail;

public interface CocktailDao extends JpaRepository<Cocktail, Integer> {

	List<Cocktail> findAllByNameContains(final String search);
}
