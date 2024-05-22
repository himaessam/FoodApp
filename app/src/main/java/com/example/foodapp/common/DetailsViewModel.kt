package com.example.foodapp.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.model.CategoryMeal
import com.example.foodapp.remot.Repo
import com.example.foodapp.util.Resorce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repo: Repo) : ViewModel() {

    private var _detailsLiveData = MutableLiveData<Resorce<CategoryMeal>>()
    val detailsLiveData get() = _detailsLiveData

    private var saveStateCategory: Resorce<CategoryMeal>? = null

    fun getMealDetails(mealId: Int) {
        saveStateCategory?.let {
            detailsLiveData.postValue(it)
            return
        }
        viewModelScope.launch(IO) {
            try {
                val response = repo.getMealDetails(mealId)
                if (response.meals.isNotEmpty()) {
                    _detailsLiveData.postValue(Resorce.Success(response.meals[0]))
                    saveStateCategory = Resorce.Success(response.meals[0])
                } else {
                    _detailsLiveData.postValue(Resorce.Error(response.toString()))
                }
            } catch (e: Exception) {
                _detailsLiveData.postValue(Resorce.Error("An error occurred: ${e.message}"))
            }
        }
    }
}