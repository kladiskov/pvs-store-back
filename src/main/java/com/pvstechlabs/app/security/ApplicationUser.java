package com.pvstechlabs.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pvstechlabs.app.data.entities.Credential;
import com.pvstechlabs.app.data.entities.User;

public class ApplicationUser implements UserDetails {

	private Long id;

	private String username;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public ApplicationUser(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static ApplicationUser create(Credential credential) {
		User user = credential.getUser();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(credential.getRole());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(grantedAuthority);
		return new ApplicationUser(user.getUserId(), credential.getUsername(), user.getEmailId(),
				credential.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Returns {@code true} if the supplied object is a {@code ApplicationUser}
	 * instance with the same {@code username} value.
	 * <p>
	 * In other words, the objects are equal if they have the same username,
	 * representing the same principal.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof ApplicationUser) {
			return username.equals(((ApplicationUser) other).username);
		}
		return false;
	}

	/**
	 * Returns the hashcode of the {@code username}.
	 */
	@Override
	public int hashCode() {
		return username.hashCode();
	}

	public Long getUserId() {
		return id;
	}

}
