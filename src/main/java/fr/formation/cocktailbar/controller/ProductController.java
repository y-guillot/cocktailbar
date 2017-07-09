package fr.formation.cocktailbar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.cocktailbar.dao.ProductDao;
import fr.formation.cocktailbar.entity.Product;

@Controller
@RequestMapping("/product")  // prefix pour toutes les requetes
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired // Spring Injection de beans par l'instanciation dynamique de l'interface du DAO.
	private ProductDao productDao;
	
	@RequestMapping({"", "/"}) // deal with both routes
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("product/list");
		mav.getModel().put("productList", this.productDao.findAll());
		return mav;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam Integer id){
		this.productDao.delete(id);
		return this.index();
	}
	
	@RequestMapping("/add")
	public String showAdd() {
		return "product/edit";
	}
	
	@RequestMapping("/edit")
	public String showEdit(
			final Model model,
			@RequestParam final Integer id) {

		model.addAttribute("product", this.productDao.findOne(id));
		return "product/edit";
	}
	
	@RequestMapping(path="/add", method=RequestMethod.POST)
	public ModelAndView add (
			final Model model,
			@RequestParam final String name,
			@RequestParam("stock")  final Integer productCount,
			@RequestParam(required=false) final Integer id) {

		final Product product = new Product();
		if (id != null) {
			product.setId(id);
		}
		product.setName(name);
		product.setStock(productCount);
		try {
			this.productDao.save(product);
			model.addAttribute("message", id != null ? "Le produit a bien été mis à jour." : "Le produit a bien été crée.");
			model.addAttribute("error", false);
		} catch (DataAccessException e){
			ProductController.LOGGER.error("Fail to add a nex product", e);
			model.addAttribute("message", "Une erreur a empéchée la création du produit.");
			model.addAttribute("error", true);
		}
		//return "product/list";
		return this.index();
	}
//	public String add(final Model model, final HttpServletRequest request) {
//		final string name = request.getParameter("name...");
//		model.addAttribute("", )
//		return "product/add";
//	}
//	public ModelAndView add() {
//		ModelAndView mav = new ModelAndView("product/add");
//		return mav;
//	}
}
