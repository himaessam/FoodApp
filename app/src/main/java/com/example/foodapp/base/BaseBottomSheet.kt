package com.example.foodapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet <VB : ViewBinding>(private val bindingInflater : (inflater: LayoutInflater) -> VB ) : BottomSheetDialogFragment()
{
    private var _binding: VB? = null
    val binding :VB get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    open fun observe(){
    }
    open fun onClicks(){
    }

    open fun showLoading(){

    }

    open fun finishLoading(){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}