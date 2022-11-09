package com.recipeapp.repositories;

import com.recipeapp.models.entities.MyUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Integer> {

  List<MyUser> findAll();

  MyUser findByUsername(String username);

  Optional<MyUser> findMyUsersByUsername(String username);

  MyUser findById(int id);
}
