package com.vam.cco.model;

public class RoleModel implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6894286502656243952L;
	
	private Long id;
	private String name;
	
	public RoleModel() {
		super();
	}
	
	public RoleModel(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override	
	public String toString() {
		return "RoleModel [id=" + id + ", name=" + name + "]";
	}
}
