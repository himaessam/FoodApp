package com.example.foodapp.remot

import com.example.foodapp.model.ModelAllCategories
import com.example.foodapp.model.ModelMeal
import com.example.foodapp.model.ModelPopularMeal
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {

    @GET("random.php")
    suspend fun getRandomMeal(): ModelMeal

    @GET("filter.php")
    suspend fun getPopularMeals(@Query("c") category: String): ModelPopularMeal

    @GET("categories.php")
    suspend fun getCategory(@Query("k")category: String):ModelAllCategories

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i")mealId:Int):ModelMeal
}