package fr.formation.cocktailbar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.cocktailbar.entity.Cocktail;


//@Repository
public interface CocktailDao extends JpaRepository<Cocktail, Integer> {

	//@Transactional
	//@Query()  
	List<Cocktail> findAllByNameContains(final String search); // la declaration de la methode génèrera par spring à la volée la requete AMAZING!!!
	
}
