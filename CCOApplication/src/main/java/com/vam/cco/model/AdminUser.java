package com.vam.cco.model;

import java.io.Serializable;

public class AdminUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8805263487974691577L;
	
	private Long id;
	private String username;
	private String password;
	private String roleName;
	private Long roleId;
	
	public AdminUser() {
	}
	
	public AdminUser(Long id, String username, String password, String roleName, Long roleId) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roleName = roleName;
		this.roleId = roleId;
	}
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", username=" + username + ", password=" + password + ", roleName=" + roleName
				+ ", roleId=" + roleId + "]";
	}
	
	
}
