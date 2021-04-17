package my.self.demo.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationForm {

	@Email
	private String email;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Size(min = 5)
	private String password;
	
	@NotNull(message = "Password not match")
	private String passwordConfim;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfim() {
		return passwordConfim;
	}
	public void setPasswordConfim(String passwordConfim) {
		this.passwordConfim = passwordConfim;
		
		if (!this.password.equals(this.passwordConfim)) {
			this.passwordConfim = null;
		}
	}
	
}
