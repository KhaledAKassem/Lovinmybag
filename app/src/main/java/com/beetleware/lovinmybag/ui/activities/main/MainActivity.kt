package com.beetleware.lovinmybag.ui.activities.main

import android.content.Intent
import android.os.Bundle
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.databinding.ActivityMainBinding
import com.beetleware.lovinmybag.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MainView, BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class.java) {



    override fun getLayoutRes()= R.layout.activity_main


    override fun observeLiveDatas() {

    }

    override fun initViewModel(viewModel: MainViewModel) {
          mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {

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

}