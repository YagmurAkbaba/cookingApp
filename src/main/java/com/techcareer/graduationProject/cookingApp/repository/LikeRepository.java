package com.techcareer.graduationProject.cookingApp.repository;

import com.techcareer.graduationProject.cookingApp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser_UserIdAndRecipe_RecipeId(Long userId, Long recipeId);

    List<Like> findByUser_UserId(Long userId);

    List<Like> findByRecipe_RecipeId(Long recipeId);
}
