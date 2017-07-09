package fr.formation.cocktailbar.entity;

public class Account {

	private Integer id;
	private String username;
	private String password;
	private Role role;
	private Integer enabled;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}

