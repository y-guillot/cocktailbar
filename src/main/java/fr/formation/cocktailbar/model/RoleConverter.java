package fr.formation.cocktailbar.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import fr.formation.cocktailbar.dao.RoleDao;
import fr.formation.cocktailbar.entity.Role;

public class RoleConverter implements Converter<String, Role> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RoleConverter.class);
	
	@Autowired
	private RoleDao roleDao;
	
	
	@Override
	public Role convert(String source) {
	
		RoleConverter.LOGGER.debug(">>>>>>>>>> Converter Converter<String, Role> called.");
		return this.roleDao.findOne(Integer.parseInt(source));
	}
}
