package com.beetleware.lovinmybag.ui.activities.splash

import android.os.Bundle
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.common.extensions.goToActivity
import com.beetleware.lovinmybag.databinding.ActivitySplashBinding
import com.beetleware.lovinmybag.ui.activities.main.MainActivity
import com.beetleware.lovinmybag.ui.base.BaseActivity

class SplashActivity : SplashView, BaseActivity<SplashViewModel, ActivitySplashBinding>(
    SplashViewModel::class.java){

    override fun getLayoutRes()= R.layout.activity_splash

    override fun init(savedInstanceState: Bundle?) {
        goToActivity(MainActivity::class.java)
        finish()
    }

    override fun observeLiveDatas() {

    }

    override fun initViewModel(viewModel: SplashViewModel) {
            mBinding.viewModel=viewModel
    }


}