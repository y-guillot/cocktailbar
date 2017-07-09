package fr.formation.cocktailbar.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.cocktailbar.dao.CocktailDao;
import fr.formation.cocktailbar.dao.IngredientDao;
import fr.formation.cocktailbar.dao.ProductDao;
import fr.formation.cocktailbar.entity.Cocktail;
import fr.formation.cocktailbar.entity.Ingredient;
import fr.formation.cocktailbar.entity.Product;

@Controller
@RequestMapping("/ingredient")  // prefix pour toutes les requetes
@SessionAttributes({"ingredientList", "productList", "cocktail"})  // l'ajout dans le modèle de la propriété correspondante sera persisté en session !!!
public class IngredientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IngredientController.class);
	
	@Autowired // Spring Injection de beans par l'instanciation dynamique de l'interface du DAO.
	private IngredientDao ingredientDao;
	@Autowired
	private CocktailDao cocktailDao;
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping({"/{id}"}) // Path variable
	public ModelAndView index(@PathVariable final Integer id) {
		
		// Recup infos modele from DB
		final ModelAndView mav = new ModelAndView("ingredient/edit");
		mav.getModel().put("cocktail", this.cocktailDao.findOne(id));
		
		// Récup des ingredients en DB puis chargement du modele
		final List<Ingredient> ingredientList = new ArrayList<>();
		ingredientList.addAll(this.ingredientDao.findAllByCocktailId(id));
		mav.getModel().put("ingredientList", ingredientList);
		
		// Obtenir la liste de tous les produits auxquels on soustrait ceux deja attribués au cocktail
		final List<Product> productList = new ArrayList<>();
		productList.addAll(this.productDao.findAll());
		//productList.removeAll(ingredientList); // autre possibilité avec une surcharge de la methode equals à implémenter.
		for (final Ingredient ingredient : ingredientList) {
			productList.remove(ingredient.getProduct());
		}
		// chargement de la liste de sproduits dans le modele
		mav.getModel().put("productList", productList);
		
		return mav;
	}
	
	@RequestMapping(path="/add", method=RequestMethod.POST )
	public String addProduct(
			final Model model,
			@RequestParam final Integer productId,
			@RequestParam final Float quantity) {
		
		// construction d'un nouvel ingrédient
		final Ingredient ingredient = new Ingredient();
		IngredientController.LOGGER.debug("Recuperation du cocktail depuis model : {}", model.asMap().get("cocktail"));
		ingredient.setCocktail((Cocktail) model.asMap().get("cocktail"));
		ingredient.setProduct(this.productDao.findOne(productId));
		ingredient.setQuantity(quantity);
		// ajjout de l'ingrédient dans la liste stockée en session
		@SuppressWarnings("unchecked")
		final List<Ingredient> ingredientList = (List<Ingredient>) model.asMap().get("ingredientList");
		ingredientList.add(ingredient);
		// retirr le produit ajouté de la liste des produits disponibles
		@SuppressWarnings("unchecked")
		final List<Product> productList = (List<Product>) model.asMap().get("productList");
		productList.remove(ingredient.getProduct());
		return "ingredient/edit";
	}
	
	@RequestMapping("/delete/{productId}")
	public String removeProduct(
			@ModelAttribute final List<Ingredient> ingredientList,
			@ModelAttribute final List<Product> productList,			
			@PathVariable Integer productId){
		
		int index = -1;
		for (final Ingredient ingredient : ingredientList){
			if (ingredient.getProduct().getId().equals(productId)) {
				index = ingredientList.indexOf(ingredient);
				break;
			}
		}
		if (index >= 0){
			final Ingredient ingredient = ingredientList.remove(index);
			productList.add(ingredient.getProduct());
			//this.ingredientDao.delete(ingredient);
		}
		
		return "ingredient/edit";
	}
	
	@RequestMapping("/save")
	public String save(
			@ModelAttribute final List<Ingredient> ingredientList,
			@ModelAttribute final Cocktail cocktail) {
		this.ingredientDao.deleteAllByCocktailId(cocktail.getId());
		this.ingredientDao.save(ingredientList);
		return "redirect:/cocktail/"; // véritable redirection URL
	}
	
	@RequestMapping("/view/{id}")
	public ModelAndView view(@PathVariable final Integer id) {
		final ModelAndView mav = new ModelAndView("ingredient/list");
		mav.addObject("ingredientList", this.ingredientDao.findAllByCocktailId(id));
		return mav;
	}


}
