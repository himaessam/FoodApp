package com.example.foodapp.ui.categorie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.model.PMeal
import com.example.foodapp.remot.Repo
import com.example.foodapp.util.Resorce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCategorie @Inject constructor(private val repo: Repo) : ViewModel() {

    private var _categoryMealsListLiveData = MutableLiveData<Resorce<List<PMeal>?>>()
    val categoryListLiveData get() = _categoryMealsListLiveData
    private var saveStateCategory: Resorce<List<PMeal>?>?=null

    fun getCategorie(categorie: String){
        saveStateCategory?.let {
            categoryListLiveData.postValue(it)
            return
        }
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repo.getMealsByCategory(categorie)
                if (response.meals.isNotEmpty()) {
                    _categoryMealsListLiveData.postValue(Resorce.Success(response.meals))
                    saveStateCategory = Resorce.Success(response.meals)
                }else {
                    _categoryMealsListLiveData.postValue(Resorce.Error(response.toString()))
                }
            } catch (e: Exception) {
                _categoryMealsListLiveData.postValue(Resorce.Error("An error occurred: ${e.message}"))
            }
        }
    }
}