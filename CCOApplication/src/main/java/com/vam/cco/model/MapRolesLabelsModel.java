package com.vam.cco.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.vam.cco.dao.entity.Labels;

public class MapRolesLabelsModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3677504753811607075L;

	private List<ProjectRoleModel> projectRoles;
	private Map<Integer, List<Labels>> roleLabelsMap;
	List<Labels> labels;
	
	public List<ProjectRoleModel> getProjectRoles() {
		return projectRoles;
	}
	public void setProjectRoles(List<ProjectRoleModel> projectRoles) {
		this.projectRoles = projectRoles;
	}
	public Map<Integer, List<Labels>> getRoleLabelsMap() {
		return roleLabelsMap;
	}
	public void setRoleLabelsMap(Map<Integer, List<Labels>> roleLabelsMap) {
		this.roleLabelsMap = roleLabelsMap;
	}
	public List<Labels> getLabels() {
		return labels;
	}
	public void setLabels(List<Labels> labels) {
		this.labels = labels;
	}
	@Override
	public String toString() {
		return "MapRolesLabelsModel [projectRoles=" + projectRoles + ", roleLabelsMap=" + roleLabelsMap + ", labels="
				+ labels + "]";
	}
	
}
