package com.beetleware.lovinmybag.ui.fragments.login

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.databinding.FragmentLoginBinding
import com.beetleware.lovinmybag.ui.base.BaseFragment

class LoginFragment : LoginView, BaseFragment<LoginViewModel, FragmentLoginBinding>(LoginViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_login

    override fun initViewModel(viewModel: LoginViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
      initViews()
    }

    override fun initViews(){

    }

}