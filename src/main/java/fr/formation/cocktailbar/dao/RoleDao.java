package fr.formation.cocktailbar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.cocktailbar.entity.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

}