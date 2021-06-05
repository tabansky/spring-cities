package com.example.springfirstapp.config;

import com.example.springfirstapp.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {
  private String email;
  private String password;
  private String role;
  private Collection<? extends GrantedAuthority> grantedAuthorities;

  public static CustomUserDetails fromUserEntityToCustomUserDetails(User user) {
    CustomUserDetails customUserDetails = new CustomUserDetails();
    customUserDetails.email = user.getEmail();
    customUserDetails.password = user.getPassword();
    customUserDetails.role = user.getRole().getName();
    customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));

    return customUserDetails;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
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
