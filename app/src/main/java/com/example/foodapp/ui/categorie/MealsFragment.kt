package com.example.foodapp.ui.categorie

import android.os.Bundle

import android.view.View

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodapp.Adapter.AdapterMeals

import com.example.foodapp.base.BaseFragment
import com.example.foodapp.databinding.FragmentMealsBinding
import com.example.foodapp.model.PMeal
import com.example.foodapp.util.Resorce
import com.example.foodapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsFragment : BaseFragment<FragmentMealsBinding>(FragmentMealsBinding::inflate)  {
    private val categoryViewModel : ViewModelCategorie by viewModels()
    private val adapterMealsList = AdapterMeals()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        val categoryName = MealsFragmentArgs.fromBundle(requireArguments()).id
        categoryViewModel.getCategorie(categoryName)
        showLoading()
        onClicks()
        observe()
    }

    override fun onClicks() {
        super.onClicks()
        binding.apply {
            adapterMealsList.setOnClick {
                findNavController()
                    .navigate(
                        MealsFragmentDirections.actionMealsFragmentToDetailsFragment(it)
                    )
            }
        }

}

    override fun observe() {
        super.observe()
        categoryViewModel.categoryListLiveData.observe(viewLifecycleOwner){
            when (it) {
                is Resorce.Success -> {
                    finishLoading()
                    it.data?.let {
                        setContentInViews(it)
                    }
                }

                is Resorce.Error -> {
                    it.message?.let { message ->
                        showToast(message)
                    }
                }
            }
        }

    }

    override fun showLoading() {
        super.showLoading()
        binding.apply {
            progresBar.visibility = View.VISIBLE
            rvMeals.visibility = View.INVISIBLE
            tvCategoryName.visibility = View.INVISIBLE
        }
    }

    override fun finishLoading() {
        super.finishLoading()
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            rvMeals.visibility = View.VISIBLE
            tvCategoryName.visibility = View.VISIBLE
        }
    }

    private fun setContentInViews(response: List<PMeal>) {
        adapterMealsList.mealList = response
        binding.rvMeals.adapter = adapterMealsList
        val categoryName = MealsFragmentArgs.fromBundle(requireArguments()).id
        val mealsCounter = categoryName +" : "+ response.size.toString()
        binding.tvCategoryName.text = mealsCounter
    }

}