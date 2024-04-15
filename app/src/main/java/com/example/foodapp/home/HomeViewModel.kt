package com.example.foodapp.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.model.Category
import com.example.foodapp.model.Meal
import com.example.foodapp.model.PMeal
import com.example.foodapp.remot.Repo
import com.example.foodapp.util.Resorce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    private var _randomMealLiveData = MutableLiveData<Resorce<Meal>>()
    private var saveStatePopularMeal: Resorce<List<PMeal>?>? = null
    private var savStateCategory :Resorce<List<Category>?>?=null

    private var _categorysLiveDate=MutableLiveData<Resorce<List<Category>?>>()
    private var _popularMealsLiveData = MutableLiveData<Resorce<List<PMeal>?>>()

    val categoryLiveDate get() = _categorysLiveDate

    val randomMealLiveData get() = _randomMealLiveData


    val popularMealsLiveData get() = _popularMealsLiveData




    fun getRandomMeal() {
        viewModelScope.launch(IO) {
            try {
                val response = repo.getRandom()
                if (response.meals.isNotEmpty()) {
                    _randomMealLiveData.postValue(Resorce.Success(response.meals[0]))
                } else {
                    _randomMealLiveData.postValue(Resorce.Error(response.toString()))
                }

            } catch (e: Exception) {
                _randomMealLiveData.postValue(Resorce.Error("An error occurred: ${e.message}"))
            }
        }
    }

    fun getPupularMeals(category: String) {
        saveStatePopularMeal?.let {
            popularMealsLiveData.postValue(it)
            return
        }

        viewModelScope.launch(IO) {
            try {
                val response = repo.getPopular(category)
                if (response.meals.isNotEmpty()) {
                    _popularMealsLiveData.postValue(Resorce.Success(response.meals))
                    saveStatePopularMeal = Resorce.Success(response.meals)

                } else {
                    _popularMealsLiveData.postValue(Resorce.Error(response.toString()))
                }
            } catch (e: Exception) {
                _popularMealsLiveData.postValue(Resorce.Error("An error occurred: ${e.message}"))
            }
        }
    }
    fun getCategory(category: String){
        savStateCategory?.let {
            categoryLiveDate.postValue(it)
            return
        }
        viewModelScope.launch (IO){
            try {
                val response =repo.getCategorys(category)
                if (response.categories.isNotEmpty()){
                    _categorysLiveDate.postValue(Resorce.Success(response.categories))
                savStateCategory = Resorce.Success(response.categories)
                }else{
                    _categorysLiveDate.postValue(Resorce.Error(response.toString()))
                }

            }catch (e:Exception){
                _categorysLiveDate.postValue(Resorce.Error("An error occurred:${e.message}"))
            }
        }
    }
}