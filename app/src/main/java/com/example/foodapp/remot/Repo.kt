package com.example.foodapp.remot

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repo @Inject constructor(val api: ApiCalls) {
    suspend fun getRandom() =
        api.getRandomMeal()

    suspend fun getPopular(category: String) =
        api.getPopularMeals(category)

    suspend fun getCategorys(category: String) =
        api.getCategory(category)

    suspend fun getMealDetails(mealId: Int) =
        api.getMealDetails(mealId)

    suspend fun getAllCategories() =
        api.getAllCategories()

    suspend fun getMealsByCategory(category: String)=
        api.getMealsCategory(category)
}