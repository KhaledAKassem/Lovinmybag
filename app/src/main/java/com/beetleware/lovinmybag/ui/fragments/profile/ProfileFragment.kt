package com.beetleware.lovinmybag.ui.fragments.profile

import android.os.Bundle
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.databinding.FragmentProfileBinding
import com.beetleware.lovinmybag.ui.base.BaseFragment

class  ProfileFragment :ProfileView,BaseFragment<ProfileViewModel,FragmentProfileBinding>(ProfileViewModel::class.java) {
    override fun getLayoutRes() = R.layout.fragment_profile

    override fun initViewModel(viewModel: ProfileViewModel) {

    }

    override fun init(savedInstanceState: Bundle?) {

    }

}