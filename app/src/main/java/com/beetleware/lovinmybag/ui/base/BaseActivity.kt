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
import com.beetleware.lovinmybag.common.extensions.appComponent
import com.beetleware.lovinmybag.common.extensions.get
import com.beetleware.lovinmybag.common.extensions.put
import com.beetleware.lovinmybag.common.utils.ImageManger
import com.beetleware.lovinmybag.common.utils.Localization


abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>
    (private val mViewModelClass: Class<VM>) : AppCompatActivity(), BaseView {

    val mBinding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        setInitialLanguage()

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

    override fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * check for the stored language in shared pref :
     * - if NOT_DEFINED_LANG : no stored language and set app local to "ar"
     * - else update the app locale to match the stored language.
     */
    private fun setInitialLanguage() {
        var currentLang = appComponent().getSharedPreference().get(
            Constants.CURRENT_LANGUAGE_KEY, Constants.NOT_DEFINED_LANG
        )

        if (currentLang == Constants.NOT_DEFINED_LANG) {
            currentLang = Constants.ARABIC
            appComponent().getSharedPreference().put(Constants.CURRENT_LANGUAGE_KEY, currentLang)
        }

        Localization.setLanguage(this, currentLang)
    }


    override fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.GALLERY_REQUEST_CODE)
    }

    override fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageManger.save_image_in_provider(this))
        startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE)
    }

}





