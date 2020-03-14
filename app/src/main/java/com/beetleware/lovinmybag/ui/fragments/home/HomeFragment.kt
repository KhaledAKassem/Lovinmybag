package com.beetleware.lovinmybag.ui.fragments.home

import android.os.Bundle
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.databinding.FragmentHomeBinding
import com.beetleware.lovinmybag.ui.base.BaseFragment

class HomeFragment : HomeView, BaseFragment<HomeViewModel, FragmentHomeBinding>(HomeViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_home

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun initViewModel(viewModel: HomeViewModel) {
      mBinding.viewModel=viewModel
     }
}