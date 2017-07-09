package fr.formation.cocktailbar.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.formation.cocktailbar.dao.RoleDao;
import fr.formation.cocktailbar.entity.Role;

@Controller
@RequestMapping("/role")
@SessionAttributes({ "roleList" })
public class RoleController {

	@Autowired
	private RoleDao roleDao;

	@ModelAttribute
	public Set<Role> roleList() {
		return new HashSet<>();
	}

	@RequestMapping({ "", "/" })
	public ModelAndView index(@ModelAttribute final Set<Role> roleList) {
		final ModelAndView mav = new ModelAndView("role/manage");
		roleList.addAll(this.roleDao.findAll());
		return mav;
	}
	
	@RequestMapping("/edit")
	public String showEdit(final Model model, @RequestParam final Integer id) {
		model.addAttribute(this.roleDao.findOne(id));
		return "role/manage";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute final Set<Role> roleList,
			@RequestParam final Integer id) {
		this.roleDao.delete(id);
		roleList.remove(new Role(id));
		return "role/manage";
	}

	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String save(@RequestParam final String name,
			@RequestParam(required=false) final Integer id,
			@ModelAttribute final Set<Role> roleList) {
		final Role role = new Role();
		if (id != null) {
			role.setId(id);
		}
		role.setName(name);
		this.roleDao.save(role);
		if (roleList.contains(role)) {
			roleList.remove(role);
		}
		roleList.add(role);
		return "role/manage";
	}
}