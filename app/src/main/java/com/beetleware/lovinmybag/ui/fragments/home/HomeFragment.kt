package com.beetleware.lovinmybag.ui.fragments.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.databinding.FragmentHomeBinding
import com.beetleware.lovinmybag.ui.base.BaseFragment

class HomeFragment : HomeView, BaseFragment<HomeViewModel, FragmentHomeBinding>(HomeViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_home

    override fun initViewModel(viewModel: HomeViewModel) {
      mBinding.viewModel=viewModel
     }

    override fun init(savedInstanceState: Bundle?) {
        initViews()
    }

    override fun initViews(){
        viewModel.getProductCount().observe(this, Observer {
            if(it.isResponseSuccessful){
                mBinding.numProducts.text=it.responseBody!!.data.toString()
            }
        })
        viewModel.getSoldItems().observe(this, Observer {
            if(it.isResponseSuccessful){
                mBinding.numOfSolidItems.text= it.responseBody!!.data.toString()
            }
        })
    }
}