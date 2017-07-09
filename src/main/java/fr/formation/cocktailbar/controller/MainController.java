package fr.formation.cocktailbar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.cocktailbar.model.MenuItem;

@Controller
public class MainController {
	
	@Autowired
	private MessageSource messageSource;  // doit etre identique à l'identifiant du bean dans applicationContext.xml

	@RequestMapping(path="/", method=RequestMethod.GET)  // on aurait pu mettre @RequestMapping("/")
	public ModelAndView index() {
		
		ModelAndView mav = new ModelAndView();
		mav.getModel().put("menu", this.buildMenu());
		mav.getModel().put("title", "helloWorld Spring MVC"); // fournit à la vue (JSP) le paramètre.
		mav.setViewName("index"); // fournira au resolveur le nom du fichier désiré sans l'extension car on pourrait changer de resolveur et passer sur du JSF !!!
		return mav;
	}

	private List<MenuItem> buildMenu() {
		
		final List<MenuItem> menu = new ArrayList<>();
		final String items = getMessage("menu");
		for(final String item : items.split(" *, *")) {
			final String title = this.getMessage(item + ".title");
			final String url = this.getMessage(item + ".url");
			menu.add(new MenuItem(title, url));
		}
		return menu;
	}

	private String getMessage(String key) {
		return this.messageSource.getMessage(key, null, Locale.getDefault());
	}
}
