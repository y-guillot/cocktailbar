package fr.formation.cocktailbar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.cocktailbar.entity.Account;

public interface AccountDao extends JpaRepository<Account, Integer> {

}