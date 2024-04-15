package com.example.foodapp.common.bottomSheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.foodapp.base.BaseBottomSheet
import com.example.foodapp.common.DetailsViewModel
import com.example.foodapp.databinding.FragmentBottomSheetBinding
import com.example.foodapp.model.Meal
import com.example.foodapp.util.Resorce
import com.example.foodapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment :
    BaseBottomSheet<FragmentBottomSheetBinding>(FragmentBottomSheetBinding::inflate) {
    private val bottomSheetViweModel: DetailsViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myId = BottomSheetFragmentArgs.fromBundle(requireArguments()).id
        bottomSheetViweModel.getMealDetails(myId)
        observe()
    }
//    override fun onClicks() {
//        binding.apply {
//            tvReadMore.setOnClickListener {
//                findNavController().navigate()
//            }
//        }
//    }
    override fun observe() {
        bottomSheetViweModel.detailsLiveData.observe(viewLifecycleOwner){
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
        binding.apply {
            progresBar.visibility = View.VISIBLE
            imgBtmSheet.visibility = View.INVISIBLE
            tvCategory.visibility = View.INVISIBLE
            tvArea.visibility = View.INVISIBLE
            tvMealName.visibility = View.INVISIBLE
            tvReadMore.visibility = View.INVISIBLE
        }
    }
    override fun finishLoading() {
        binding.apply {
            progresBar.visibility = View.INVISIBLE
            imgBtmSheet.visibility = View.VISIBLE
            tvCategory.visibility = View.VISIBLE
            tvArea.visibility = View.VISIBLE
            tvMealName.visibility = View.VISIBLE
            tvReadMore.visibility = View.VISIBLE
        }
    }

    private fun setViwe(meal: Meal) {
        binding.apply {
            Glide.with(binding.root.context)
                .load(meal.strMealThumb)
                .into(binding.imgBtmSheet)
            tvArea.text = meal.strArea
            tvMealName.text = meal.strMeal
        }
    }
}
