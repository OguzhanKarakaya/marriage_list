package com.main.marriage_list.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.main.marriage_list.R
import com.main.marriage_list.databinding.FragmentMainFlowBinding

class MainFlowFragment: Fragment() {

    private lateinit var binding: FragmentMainFlowBinding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainFlowBinding.inflate(inflater, container, false)

        binding.bottomNavigation.selectedItemId = R.id.homePageFragment

        val navHost = childFragmentManager.findFragmentById(R.id.mainHostFragment) as NavHostFragment
        navController = navHost.findNavController()

        setupBottomNavigation()
        navController.addOnDestinationChangedListener{_, destination, _->
            if (destination.id == R.id.homePageFragment)
                binding.bottomNavigation.visibility = View.VISIBLE
            else
                binding.bottomNavigation.visibility = View.GONE
        }

        return binding.root
    }

    private fun setupBottomNavigation() = binding.bottomNavigation.run {
        setupWithNavController(navController)
    }
}