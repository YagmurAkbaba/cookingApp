package com.techcareer.graduationProject.cookingApp.repository;

import com.techcareer.graduationProject.cookingApp.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
