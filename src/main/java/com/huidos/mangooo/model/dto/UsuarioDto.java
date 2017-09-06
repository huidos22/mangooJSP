package com.huidos.mangooo.model.dto;

public class UsuarioDto {
	// form:hidden - hidden value
		private Integer id;

		// form:input - textbox
		private String userName;
		
		// form:input - textbox
		private String fullName;

		// form:input - textbox
		private String email;

		// form:textarea - textarea
		private String address;

		// form:input - password
		private String password;

		// form:input - password
		private String confirmPassword;

		// form:checkbox - single checkbox
		private boolean newsletter;
		
		// form:radiobutton - radio button
		private String sex;

		// form:radiobuttons - radio button
		private Integer number;


		//Check if this is for New of Update
		public boolean isNew() {
			return (this.id == null);
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getFullName() {
			return fullName;
		}


		public void setFullName(String fullName) {
			this.fullName = fullName;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getConfirmPassword() {
			return confirmPassword;
		}


		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}


		public boolean isNewsletter() {
			return newsletter;
		}


		public void setNewsletter(boolean newsletter) {
			this.newsletter = newsletter;
		}


		public String getSex() {
			return sex;
		}


		public void setSex(String sex) {
			this.sex = sex;
		}


		public Integer getNumber() {
			return number;
		}


		public void setNumber(Integer number) {
			this.number = number;
		}

	
}
