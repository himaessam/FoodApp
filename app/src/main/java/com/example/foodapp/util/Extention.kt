package com.example.foodapp.util

import android.widget.Toast
import androidx.fragment.app.Fragment



fun Fragment.showToast (massage : Any?) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_LONG).show()
}