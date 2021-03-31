package my.self.demo.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import my.self.demo.domain.model.Role;
import my.self.demo.domain.model.User;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 7660176098938580296L;
	private User user;
	private List<GrantedAuthority> roles = new ArrayList<>();
	
	public UserDetailsImpl(User user) {
		this.user = user;
		
		for(Role role: user.getUserRoles()) {
			GrantedAuthority auth = new SimpleGrantedAuthority(role.toString());
			roles.add(auth);
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}
