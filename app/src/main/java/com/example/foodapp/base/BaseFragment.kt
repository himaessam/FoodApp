package com.example.foodapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.foodapp.model.Meal


abstract class BaseFragment <VB : ViewBinding>(private val bindingInflater : (inflater: LayoutInflater) -> VB ) : Fragment()
{
    private var _binding: VB? = null
    val binding :VB get() = _binding as VB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        return binding.root
    }
    open fun observe(){

    }
    open fun onClicks(){

    }

    open fun showLoading() {

    }

    open fun finishLoading() {

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}