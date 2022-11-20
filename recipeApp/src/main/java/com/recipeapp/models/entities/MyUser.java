package com.recipeapp.models.entities;

import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "users")
public class MyUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  @NotBlank(message = "Felhasználónév megadása kötelező!")
  @Size(min = 3, message = "Minimum 3 karakter.")
  String username;
  @NotBlank(message = "Jelszó megadása kötelető!")
  @Size(min = 3, message = "A jelszó hossza legalább 3 karakter.")
  String password;
  @NotBlank(message = "E-mail cím megadása kötelező!")
  String email;
  @OneToMany(mappedBy = "ownerUser", cascade = CascadeType.ALL)
  private List<Recipe> recipes;
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "fav_recipe_id", referencedColumnName = "id")
//  private Recipe userFav;
  @Column(name = "fav_recipes")
  private String favRecipes;

  public MyUser() {
  }

  public MyUser(String username) {
    this.username = username;
  }

  public MyUser(Integer id) {
    this.id = id;
  }

  public MyUser(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public MyUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  public String getFavRecipes() {
    return favRecipes;
  }

  public void setFavRecipes(String favRecipes) {
    this.favRecipes = favRecipes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
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
