package databin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
public class login {

	@Entity
	@Table(name = "LoginMasterEntity")
	public  class LoginMasterEntity extends CustomizableEntity{
		public LoginMasterEntity() {
			super("LoginMasterEntity");
		}
		@Id
		@Column(name = "Reference", unique=true)
		private String Reference;
		
		@Column(name = "Username")
		private String Username;
		@Column(name = "Password")
		private String Password;
		@Column(name = "Branch")
		private String Branch;
		@Column(name = "UsedByTestScenario")
		private String UsedByTestScenario;
		@Column(name = "UserRole")
		private String UserRole;
		public String getReference() {
			return Reference;
		}
		public void setReference(String reference) {
			Reference = reference;
		}
		public String getUsername() {
			return Username;
		}
		public void setUsername(String username) {
			Username = username;
		}
		public String getPassword() {
			return Password;
		}
		public void setPassword(String password) {
			Password = password;
		}
		public String getBranch() {
			return Branch;
		}
		public void setBranch(String branch) {
			Branch = branch;
		}
		public String getUsedByTestScenario() {
			return UsedByTestScenario;
		}
		public void setUsedByTestScenario(String usedByTestScenario) {
			UsedByTestScenario = usedByTestScenario;
		}
		public String getUserRole() {
			return UserRole;
		}
		public void setUserRole(String userRole) {
			UserRole = userRole;
		}
	}

	}

