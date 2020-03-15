package com.beetleware.lovinmybag.ui.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.databinding.ActivityMainBinding
import com.beetleware.lovinmybag.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MainView, BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun getLayoutRes()= R.layout.activity_main

    override fun observeLiveDatas() {

    }

    override fun initViewModel(viewModel: MainViewModel) {
          mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        initBottomNavigation()
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        navHostFragment.childFragmentManager.fragments[0]
            .onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        navHostFragment.childFragmentManager.fragments[0]
            .onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun initBottomNavigation() {
        val navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupWithNavController(mBinding.bottomNav, navController)
        mBinding.fab.setOnClickListener {
            if (navController.currentDestination?.id != R.id.homeFragment)
                navController.navigate(R.id.homeFragment)
        }
    }
    override fun onSupportNavigateUp() =
        findNavController(R.id.navHostFragment).navigateUp()


}