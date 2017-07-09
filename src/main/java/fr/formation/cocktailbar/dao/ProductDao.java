package fr.formation.cocktailbar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.formation.cocktailbar.entity.Product;


//@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{

	//@Transactional
	//@Query()  
	List<Product> findAllByNameContains(final String search); // la declaration de la methode génèrera par spring à la volée la requete AMAZING!!!
	
}
