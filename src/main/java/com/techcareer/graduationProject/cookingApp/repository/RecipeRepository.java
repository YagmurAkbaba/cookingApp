package com.techcareer.graduationProject.cookingApp.repository;

import com.techcareer.graduationProject.cookingApp.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // how to name is quite important - naming it findByUserId did not work
    List<Recipe> findByUser_UserId(Long userId);
}
