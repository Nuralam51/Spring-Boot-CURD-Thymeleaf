package com.example.StudentDiary.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private String username;
	private String password;
	private String enabled;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(Users theUser) {
        this.username = theUser.getUsername();
        this.password = theUser.getPassword();
        this.enabled = theUser.getEnabled();
        this.authorities = Arrays.stream(theUser.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
		return true;
	}

}
