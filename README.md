# COOKIDEA
* Techcareer Java Spring Boot Bootcamp Graduation Project.
* The project is a REST API that serves as a social media platform that recipes shared.
* In the Project:
  * Users have to create accounts. (Register)
  * Login is also implemented.
  * Registered users can share, update, delete, like, and comment on recipes.
* The front-end of the project is under development. See: https://github.com/YagmurAkbaba/cooking-App
# Technologies Used
* Java - Spring Boot
* MySql
* IntelliJ
* Postman
# Project Details
* Layered architecture implemented.
* Simple exception handling implemented.
* Certain cases controlled such as user and/or recipe existence for update, delete, like, and comment operations.
## Responses
* ResponseEntity of response dtos, null and boolean values returned as a response.
* HTTP Status Codes: 
  * 200 (OK) if successful
  * 201 (Created) if successful registration
  * 409 (Conflict) if registration attempt with an existing username
  * 401 (Unauthorized) if unsuccessful login
  * 422 (Unprocessable Entity) if controlls of certain cases not applicable
  * 500 (Internal Server Error) if any exception occurs


# REQUESTS
## AUTH REQUESTS
### Successful Registration
![auth-successful-register](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/689716db-c6ad-4d31-9149-94e3ffef2b3b)
### Register request with an existing username
![auth-register-request-existing-username](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/85f33bb8-3274-4cf0-8b6f-cacb19a3befd)
### Successful login
![auth-login](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/7cba71b5-a971-4061-a91e-1e8e738f0a98)
### Login request with wrong password
![auth-login-wrong-password](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/a70a27ad-fe41-4066-9c04-24b87cf7b3b4)
### Login request with non-existing/wrong username
![auth-login-wrong-username](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/af44606b-42b9-4907-ae80-1cdb75c46251)
## USER REQUESTS
### Successful user creation
![user-create-request-successful](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/c59c436d-2c01-4636-9af6-28a0b2a3cd99)
### User creation request with missing field(s) (password)
![user-create-request-missing-info](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/f5d10293-3533-44a0-bfd6-8894011fa517)
### Successful user update
![user-update](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/2a675991-57c2-44c0-a5d0-1e10ca693aa7)
### Update user request with nonexisting userId
![update-nonexisting-user](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/e5d0c0d1-481a-4f2d-ab25-a4b24141498a)
### Retrive all users
![user-get-all](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/f3f34252-1d3b-48ad-94f0-5671c3a3a196)
### Retrive a user by userId
![user-get-by-ıd](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/5553a764-6f0f-4955-bb8a-25ea06972ea4)
### Get user request with nonexisting userId
![user-get-by-nonexisting-id](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/f5bd0b06-0378-47f8-a4af-c695251c58f1)
### Successful user deletion
![delete-user](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/1a2e0073-cd1f-4faf-aa71-02e7a4ed2117)
### Delete user request with nonexisting userId
![delete-nonexisting-user](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/fda5f8f4-c90e-4f2d-9722-b90244027ac0)
## RECİPE REQUESTS
### Successful recipe creation
![recipe-create](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/32636919-2f38-423e-874a-adf8b44e3d6d)
### Recipe creation request with missing field(s) (userId)
![recipe-create-request-without-userId](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/a66d4386-d17c-4c34-8a71-33834dd21962)
### Recipe creation request with non-existing userId
![recipe-create-req-nonexisting-user](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/70997590-9a80-4939-95b6-34b1ee898065)
### Successful recipe update
![recipe-update](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/eee7a79e-c232-4f5e-8c85-b9411ab8c26a)
### Update recipe request with nonexisting recipeId
![recipe-update-req-nonexisting-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/9ce766d3-36f8-4843-a99d-798e58bbdb0b)
### Retrive all recipes
![recipe-get-all-recipes](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/e7a59fc6-979f-4137-bfc6-639728967ea2)
### Retrive all recipes of a specific user
![recipe-get-all-recipes-of-user](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/d35e6d5a-c480-4ba1-b113-268e66b5e9e5)
### Get all recipes request with nonexisting userId
![recipe-get-all-recipes-nonexisting-user](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/eb4bd673-2314-481b-85a2-a8bc8949d8b2)
### Retrive a recipe by recipeId
![recipe-get-by-id](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/8d129e1b-1932-44a1-9ef1-dfc67ca19ec3)
### Get recipe request with nonexisting recipeId
![recipe-get-req-nonexisting-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/95d20f97-0fe2-4d1f-8826-227be5c16b9a)
### Successful recipe deletion
![recipe-delete](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/727873b1-f65f-4575-9888-c6cb67b7df0d)
### Delete recipe request with nonexisting recipeId
![recipe-delete-nonexisting-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/b2d0d900-6053-45dc-a553-2ad5ccaa600f)
## LIKE REQUESTS
### Successful like creation
![like-create](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/c1ea0386-1b21-4377-89c4-07aa22ef82dd)
### Like creation request with missing field(s) (recipeId)
![like-create-missing-info](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/6e1b6b49-1ef4-44ca-bed1-501ea6159c54)
### Duplicate like creation request (the recipe already liked by the user)
![like-create-double](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/16c7f6d5-e846-4b0e-bfd0-5a1665f8f67e)
### Retrive all likes
![like-get-all](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/8333900e-32dc-433e-a73f-8f7888750fcc)
### Retrive all likes of a specific user
![like-get-user-likes](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/6695af5f-2457-4799-a6ca-adb709c8cda4)
### Get all likes request with nonexisting userId
![like-get-nonexisting-user-likes](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/8dc3f8cf-47d8-4a8f-bbc6-a5745ecb66b6)
### Retrive all likes of a specific recipe
![like-get-likes-of-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/f067ef1e-793b-43cc-8479-3a6af2a8faec)
### Get all likes request with nonexisting recipeId
![like-get-likes-nonexisting-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/6a8aabfe-472b-4f57-b026-fe5a143f220d)
### Get like of a user at a specific recipe
![get-like-of-user-at-a-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/139b552b-44b8-4b27-9c93-fc95fd061897)
### Get like request of a non-existing like (the recipe is not liked by the user)
![like-get-nonexisting-like](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/f9eca59d-60c3-44d7-a08c-701e83a6bd0c)
### Get like of a user at a specific recipe request with non-existing recipeId or likeId 
![get-likes-nonexisting-like-or-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/5cebfaf1-01ed-4dce-96e4-d15378cf4f9e)
### Get like of a user at a specific recipe request with non-existing recipeId and likeId 
![like-get-all-nonexisting-user-and-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/40670662-36ca-4b5b-8ee8-48f8d3945f13)
### Retrieve a like by likeId
![like-get-like-by-id](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/70cafd86-df1f-4974-b46d-f408542c84b5)
### Get like request with nonexisting likeId
![like-get-like-by-nonexisting-id](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/f86672ae-86ce-4bd7-8b44-29d3c211b17d)
### Successful like deletion
![like-delete](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/290d1714-5761-4821-a59c-98b62131e6bd)
### Delete like request with nonexisting likeId
![like-delete-nonexisting-like](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/8f2cd372-9a50-403e-8f87-35f7dad3f492)
## COMMENT REQUESTS
### Successful comment creation
![comment-create](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/bfaf5e36-c048-4208-a59a-f698261de89f)
### Comment creation request with missing field(s) (userId)
![comment-create-missing-info](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/150c9a2e-2ded-4667-bde2-629d2e0f5ea3)
### Successful comment update
![comment-update](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/c5506a7f-34b9-4ba4-8c87-dc0e720f4467)
### Update comment request with nonexisting commentId
![comment-update-nonexisting-comment](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/33f2de97-b1fd-4bfd-b59b-4e7a0cffe20d)
### Retrive all comments
![comment-get-all](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/5dca68cd-36e2-48c4-a1f7-0ead0086372d)
### Retrive all comments of a specific user
![comment-get-all-of-user](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/d64bacfd-7bc3-4d1c-9a69-d9fcad564dde)
### Get all comments request with nonexisting userId
![comment-get-all-nonexisting-user](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/c05defc4-7d99-4a9c-a0ac-81b72b3f32cd)
### Get comment request of a user who has no comments
![comment-get-all-user-of-no-comment](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/e4f80425-458e-4e9d-8497-d6c057ccda87)
### Retrive all comments of a specific recipe
![comment-get-all-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/9b2908ff-9f22-484e-bbf9-0abafda41d5c)
### Get all comments request with nonexisting recipeId
![comment-get-all-no-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/27448936-bcbc-4bba-ac07-d0e15f760463)
### Get comments of a user at a specific recipe
![comment-get-all-recipe-and-user](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/a4cea98e-1b93-4fb1-8f9a-ef799d1cba35)
### Get comment of a user at a specific recipe request with non-existing recipeId and/or commentId 
![comment-get-all-nonexisting-user-or-and-recipe](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/dc9a0c33-9e42-426f-9c47-8a5e22587404)
### Retrieve a comment by commentId
![comment-get-by-id](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/c784e79b-6e72-4bd5-ba48-24b4b13ecbeb)
### Get comment request with nonexisting commentId
![comment-get-by-nonexisting-id](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/2b23ea25-2050-40c8-89ce-8ec0f3cf92c0)
### Successful comment deletion
![comment-delete](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/21a59882-e881-41ff-b6d6-aec6bf8aaf36)
### Delete comment request with nonexisting commentId
![comment-delete-nonexisting](https://github.com/YagmurAkbaba/cookingApp/assets/81877857/41c83ccd-cad1-4830-9965-9b120a084160)
























