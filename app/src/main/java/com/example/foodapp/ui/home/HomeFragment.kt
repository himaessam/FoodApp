package com.example.foodapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.foodapp.Adapter.AdapterCategory
import com.example.foodapp.Adapter.Adaptertab
import com.example.foodapp.base.BaseFragment
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.model.Category
import com.example.foodapp.model.CategoryMeal
import com.example.foodapp.model.PMeal
import com.example.foodapp.util.Resorce
import com.example.foodapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapterPopularMeals = Adaptertab()
    private val adapterCategories = AdapterCategory()
    private val categoryList = arrayListOf(
        "Beef", "Chicken", "Dessert", "Lamb", "Miscellaneous",
        "Pasta", "Seafood", "Side", "Starter",
        "Vegan", "Vegetarian", "Breakfast", "Goat"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getRandomMeal()
        homeViewModel.getPupularMeals(categoryList.random())
        homeViewModel.getCategory(categoryList.random())
        homeViewModel.getAllCategories()
        observe()
        onClicks()
    }
    private var ide: Int? = null
    override fun onClicks() {
        binding.apply {
            imgRandomMeal.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(ide!!)
                )
            }
            imgRandomMeal.setOnLongClickListener{
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToBottomSheetFragment2(ide!!)
                )
                true
            }
            adapterPopularMeals.setOnLongClick {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToBottomSheetFragment2(it)
                )
            }
            adapterPopularMeals.setOnLongClick {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToBottomSheetFragment2(it)
                )
            }
            adapterPopularMeals.setOnClick {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                )
            }
            adapterCategories.setOnClic {
                findNavController()
                    .navigate(
                        HomeFragmentDirections.actionHomeFragmentToMealsFragment(it)
                    )
            }
        }
    }

    override fun observe() {
        homeViewModel.randomMealLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resorce.Success -> {
                    finishLoading()
                    it.data?.let {
                        setRandomMealView(it)

                    }
                }

                is Resorce.Error -> {
                    it.message?.let { message ->
                        finishLoading()
                        showToast(message)

                    }
                }
            }
        }
        homeViewModel.popularMealsLiveData.observe(viewLifecycleOwner) { it ->
            when (it) {
                is Resorce.Success -> {
                    finishLoading()
                    it.data?.let {
                        setPopularMeal(it)
                    }
                }

                is Resorce.Error -> {
                    it.message?.let { message ->
                        finishLoading()
                        showToast(message)

                    }
                }
            }
        }
        homeViewModel.categoryLiveDate.observe(viewLifecycleOwner) {
            when (it) {
                is Resorce.Success -> {
                    finishLoading()
                    it.data?.let {
                        setCategory(it)
                    }
                }

                is Resorce.Error -> {
                    it.message?.let { message ->
                        finishLoading()
                        showToast(message)

                    }
                }
            }
        }
    }

    override fun showLoading() {
        super.showLoading()
        binding.apply {
            rvCategory.visibility = View.INVISIBLE
            imgRandomMeal.visibility = View.INVISIBLE
        }
    }

    override fun finishLoading() {
        super.finishLoading()
        binding.apply {
            rvCategory.visibility = View.VISIBLE
            imgRandomMeal.visibility = View.VISIBLE

        }
    }


    private fun setRandomMealView(response: CategoryMeal) {
        Glide.with(binding.root.context)
            .load(response.strMealThumb)
            .into(binding.imgRandomMeal)
        ide = response.idMeal.toInt()
    }

    private fun setPopularMeal(response: List<PMeal>) {
        adapterPopularMeals.mealList = response
        binding.recyclPoplur.adapter = adapterPopularMeals
    }

    private fun setCategory(response: List<Category>) {
        adapterCategories.categoryList = response
        binding.rvCategory.adapter = adapterCategories

    }
}
