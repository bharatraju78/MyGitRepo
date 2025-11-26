package com.vam.cco.model;

import java.util.Date;

public class UserModel {

	 private Long id;

	    private String username;

	    private String password;

	    private boolean enabled;
	    
	    private String emailId;
	    
	    private String role;
	    
	    private String vamId;
	    
		private String name;
		
		private Date doj;
		
		private String grade;
		
		private String designation;
		
		private String ec;
		
		private String status;
		

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

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getVamId() {
			return vamId;
		}

		public void setVamId(String vamId) {
			this.vamId = vamId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getDoj() {
			return doj;
		}

		public void setDoj(Date doj) {
			this.doj = doj;
		}

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getEc() {
			return ec;
		}

		public void setEc(String ec) {
			this.ec = ec;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	    
		@Override
		public String toString() {
			return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", emailId=" + emailId
					+ ", enabled=" + enabled + ", role=" + role + ", vamId=" + vamId + ", name=" + name + ", doj=" + doj
					+ ", grade=" + grade + ", designation=" + designation + ", ec=" + ec + ", status=" + status
					+ "]";
		}
	    
}
