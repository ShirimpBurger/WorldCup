package com.hbs.worldcup.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hbs.domain.model.core.ActivityInitializer
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseActivity
import com.hbs.worldcup.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {
    override fun getActivityInitializer() = ActivityInitializer(R.layout.main_activity, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val fragmentContainerView = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        val navHostFragment = fragmentContainerView as? NavHostFragment?
        val navController = navHostFragment!!.findNavController()
        binding.bottomNavigation.setupWithNavController(navController)
    }
}