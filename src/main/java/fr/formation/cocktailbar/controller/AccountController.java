package fr.formation.cocktailbar.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.cocktailbar.dao.AccountDao;
import fr.formation.cocktailbar.dao.RoleDao;
import fr.formation.cocktailbar.entity.Account;
import fr.formation.cocktailbar.entity.Role;

@Controller
@RequestMapping("/account")
@SessionAttributes({ "accountList", "account", "roleList" })
public class AccountController {

	private static final String VIEW_NAME = "account/manage";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccountController.class);

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private RoleDao roleDao;

	@ModelAttribute
	public Set<Account> accountList() {
		return new HashSet<>();
	}
	
	@ModelAttribute
	public Set<Role> roleList() {
		AccountController.LOGGER.debug(">>>>>>>>>>>>>> RoleList called");
		return new HashSet<>();
	}

	@ModelAttribute
	public Account account() {
		return new Account();
	}

	@RequestMapping({ "", "/" })
	public ModelAndView index(
			final @ModelAttribute Set<Account> accountList,
			final Model model) {
		
		AccountController.LOGGER.info("Initialized account management page.");
		final ModelAndView mav = new ModelAndView(VIEW_NAME);
		accountList.addAll(this.accountDao.findAll());
		final Set<Role> roleList = new HashSet<>();
		roleList.addAll(this.roleDao.findAll());
		model.addAttribute("roleList", roleList);
		return mav;
	}

	@RequestMapping("/delete")
	public String delete(
			@RequestParam final Integer id,
			final @ModelAttribute Set<Account> accountList) {
		
		this.accountDao.delete(id);
		accountList.remove(new Account(id));
		return AccountController.VIEW_NAME;
	}

	@RequestMapping("/edit")
	public String showEdit(
			final Model model, @RequestParam final Integer id,
			final @ModelAttribute Set<Account> accountList) {
		
		model.addAttribute("account", this.accountDao.findOne(id));
		return AccountController.VIEW_NAME;
	}

	// TODO : BindingResult injecté automatiquement par Spring même sans @Valid à condition
	// que la JSP utilise le binding de Spring MVC form:form ...
	// WARNING : BindingResult doit etre placé JUSTE APRES la classe Testée (ici Account)
	// WARNING : l'instance du modele Attribute Account doit rester le même 
	// @Valid parmet de ne pas intercepter en erreur Account et laisse Au BindingResult le remplissage
	@PostMapping("/edit")
	public String edit(
			@ModelAttribute Set<Account> accountList,
			@ModelAttribute @Valid Account account,
			final BindingResult bindings,
			final Model model) {
		
		if (bindings.hasErrors()) {
			// model.addAttribute("account", new Account());  // TODO Surtout pas ici (cf Warning)
			return AccountController.VIEW_NAME;
		}
		
		// TODO: Ajouter l'associtation au rôle dans la JSP !
		//account.setRole(this.roleDao.findAll().get(0));
		final boolean isNew = account.getId() == null;
		this.accountDao.save(account);
		
		if (isNew) {
			accountList.add(account);
		} else {
			accountList.remove(account);
			accountList.add(account);
		}
		model.addAttribute("account", new Account());
		
		return AccountController.VIEW_NAME;
	}
}
