package my.self.demo.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;

public class User implements Serializable {

	private static final long serialVersionUID = 6216344084865363418L;
	
	@Id
	private Long id;
	private String email;
	private String name;
	private String password;
	private boolean enabled;
	private String token;
	private String roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public Set<Role> getUserRoles() {
		Set<Role> userRoles = new HashSet<>();
		
		if (roles != null && roles.length() > 0) {
			
			String[] rolesArr = roles.split(",");
			for (String role : rolesArr) {
				userRoles.add(Role.valueOf(role));
			}
		}
		
		return userRoles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + "]";
    }

	public String getHighLevelRole() {

	    List<String> allRoles = new ArrayList<>();

	    for (Role role : this.getUserRoles()) {
            allRoles.add(role.toString());
        }

	    if (allRoles.contains(Role.ADMIN.toString())) {
	        return Role.ADMIN.toString();
	    } else if(allRoles.contains(Role.MANAGER.toString())) {
	        return Role.MANAGER.toString();
	    } else {
	        return Role.USER.toString();
	    }

	}

	public List<String> getRolesList() {
	    List<String> list = new ArrayList<>();
	    
	    this.getUserRoles().toArray();

	    for (Role role : this.getUserRoles()) {
            list.add(role.toString());
        }

	    return list;
	}
	
	public void addRole(Role role) {
		Set<Role> roleSet = this.getUserRoles();
		roleSet.add(role);
		
		this.roles = convertRoleSetToString(roleSet);
	}

	public void removeRole(Role role) {
		Set<Role> roleSet = this.getUserRoles();
		roleSet.remove(role);
		
		this.roles = convertRoleSetToString(roleSet);
	}

	private String convertRoleSetToString(Set<Role> roleSet) {
		List<String> roleArr = new ArrayList<>(roleSet.size());
		roleSet.forEach(c -> roleArr.add(c.toString()));
		
		return String.join(",", roleArr);
	}
	
    public String getFullName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (!(obj instanceof User))
        return false;

        User user = (User)obj;

        if (user.hashCode() == this.hashCode())
            return true;

        return false;
    }

}
