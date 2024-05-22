package com.example.foodapp.ui.categorie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodapp.Adapter.AdapterFcategory
import com.example.foodapp.R
import com.example.foodapp.base.BaseFragment
import com.example.foodapp.databinding.FragmentCategoriesBinding
import com.example.foodapp.model.Category
import com.example.foodapp.ui.home.HomeViewModel
import com.example.foodapp.util.Resorce
import com.example.foodapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate)  {
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapterFCategories = AdapterFcategory()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getRandomMeal()
        showLoading()
        observe()
        onClicks()
    }
    override fun onClicks() {
        super.onClicks()
        binding.apply {
            adapterFCategories.setOnClick {
                findNavController()
                    .navigate(
                        CategoriesFragmentDirections.actionCategoriesFragmentToMealsFragment(it)
                    )
            }
        }
    }

    override fun observe() {
        super.observe()
        homeViewModel.categoryLiveDate.observe(viewLifecycleOwner) {
            when (it) {
                is Resorce.Success -> {
                    finishLoading()
                    it.data?.let {
                        setAllCategoriesView(it)
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
            rvCategory.visibility = View.INVISIBLE
        }
    }

    override fun finishLoading() {
        super.finishLoading()
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            rvCategory.visibility = View.VISIBLE

        }
    }
    private fun setAllCategoriesView(response: List<Category>) {
        adapterFCategories.categoryList = response
        binding.rvCategory.adapter = adapterFCategories
    }


}