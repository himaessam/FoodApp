package com.example.foodapp.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupNavController()
    }
    private fun setupNavController() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btnNav)
        val navController = findNavController(R.id.fragmentView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    if (navController.currentDestination?.id != R.id.homeFragment) {
                        navController.navigate(R.id.homeFragment)
                        return@setOnItemSelectedListener true
                    }
                }
                R.id.categoriesFragment -> {
                    navController.navigate(R.id.categoriesFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.favoritFragment -> {
                    navController.navigate(R.id.favoritFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

}