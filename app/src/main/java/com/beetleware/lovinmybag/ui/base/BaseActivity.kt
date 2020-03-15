package com.beetleware.lovinmybag.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.beetleware.lovinmybag.common.Constants


abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>
    (private val mViewModelClass: Class<VM>) : AppCompatActivity(), BaseView {

    val mBinding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initViewModel(viewModel)
        initLifeCycleOwner()
        observeLiveDatas()
        init(savedInstanceState)
    }

    /**
     *  You need to override this method.
     *  And you need to set viewModel to mBinding: mBinding.viewModel = viewModel
     *
     *  @param viewModel the instance of ViewModel that is related to the  activity
     */
    abstract fun initViewModel(viewModel: VM)


    override fun initLifeCycleOwner() {
        mBinding.lifecycleOwner = this
    }


}





