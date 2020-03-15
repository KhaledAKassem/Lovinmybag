package com.beetleware.lovinmybag.ui.fragments.login

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.common.utils.Validation
import com.beetleware.lovinmybag.databinding.FragmentLoginBinding
import com.beetleware.lovinmybag.ui.base.BaseFragment

class LoginFragment : LoginView, BaseFragment<LoginViewModel, FragmentLoginBinding>(LoginViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_login

    override fun initViewModel(viewModel: LoginViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun observeLiveDatas() {
        super.observeLiveDatas()
        viewModel.isDataValid.observe(this, Observer {
            if (it) {
                viewModel.isDataValid.value = false
                if(Validation.validateEmptyField(context!!,mBinding.etUsername )&& Validation.validateEmptyField(context!!,mBinding.etPassword)){
                    login(mBinding.etUsername.text.toString(), mBinding.etPassword.text.toString())
                }
            }
        })
    }

    override fun login(userName: String, password: String) {
        viewModel.login(userName,password).observe(this, Observer {
            if(it.isResponseSuccessful){
                viewModel.storeUser(it.responseBody!!.data!!)
                findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
            }
        })
    }

}