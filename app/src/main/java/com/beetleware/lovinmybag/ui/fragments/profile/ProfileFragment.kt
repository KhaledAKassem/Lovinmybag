package com.beetleware.lovinmybag.ui.fragments.profile

import android.os.Bundle
import androidx.lifecycle.Observer
import com.beetleware.lovinmybag.R
import com.beetleware.lovinmybag.common.extensions.confirmMsg
import com.beetleware.lovinmybag.common.extensions.goToActivity
import com.beetleware.lovinmybag.common.extensions.loadImg
import com.beetleware.lovinmybag.databinding.FragmentProfileBinding
import com.beetleware.lovinmybag.ui.activities.auth.AuthActivity
import com.beetleware.lovinmybag.ui.base.BaseFragment

class  ProfileFragment :ProfileView,BaseFragment<ProfileViewModel,FragmentProfileBinding>(ProfileViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_profile

    override fun initViewModel(viewModel: ProfileViewModel) {
        mBinding.viewModel=viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        initLogout()
        initEmployeeStatics()
        viewModel.getProfile().observe(this, Observer {
            if(it.isResponseSuccessful){
                mBinding.imgUser.loadImg(it.responseBody!!.data!!.avatar,R.drawable.avatar_temp)
                mBinding.tvName.text="${it.responseBody!!.data!!.firstName +" "+it.responseBody!!.data!!.lastName} "
            }
        })
    }

    override fun initEmployeeStatics(){
        viewModel.getEmployeeStatics().observe(this, Observer {
            if (it.isResponseSuccessful){
                 mBinding.numCustomers.text = it.responseBody!!.data!!.pending.toString()
                 mBinding.numOfOffers.text = it.responseBody!!.data!!.accepted.toString()
                 mBinding.numOfSales.text = it.responseBody!!.data!!.finished.toString()
            }
        })
    }

    override fun initLogout(){
        mBinding.logoutContainer.setOnClickListener{
                    viewModel.removeUser()
                    goToActivity(AuthActivity::class.java)
                    activity?.finish()
        }
    }

}