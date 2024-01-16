package com.techcareer.graduationProject.cookingApp.repository;

import com.techcareer.graduationProject.cookingApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUser_UserIdAndRecipe_RecipeId(Long userId, Long recipeId);

    List<Comment> findByUser_UserId(Long userId);

    List<Comment> findByRecipe_RecipeId(Long recipeId);
}
