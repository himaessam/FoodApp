package com.example.foodapp.common.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.foodapp.base.BaseFragment
import com.example.foodapp.common.DetailsViewModel
import com.example.foodapp.common.bottomSheet.BottomSheetFragmentArgs
import com.example.foodapp.databinding.FragmentDetailsBinding
import com.example.foodapp.model.Meal
import com.example.foodapp.util.Resorce
import com.example.foodapp.util.showToast


class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myId = BottomSheetFragmentArgs.fromBundle(requireArguments()).id
        detailsViewModel.getMealDetails(myId)
        observe()
        onClicks()
        showLoading()
    }

    override fun onClicks() {
        super.onClicks()
        binding.apply {

        }
    }
    override fun observe() {
        detailsViewModel.detailsLiveData.observe(viewLifecycleOwner){
            when (it) {
                is Resorce.Success -> {
                    finishLoading()
                    it.data?.let {
                        setViwe(it)

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
    }

    override fun finishLoading() {
        super.finishLoading()
    }

    private fun setViwe(it: Meal){


    }
}