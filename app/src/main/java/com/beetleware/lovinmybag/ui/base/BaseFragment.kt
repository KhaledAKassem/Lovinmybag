package com.beetleware.lovinmybag.ui.base


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beetleware.lovinmybag.R
import com.google.gson.Gson
import com.beetleware.lovinmybag.common.Constants
import com.beetleware.lovinmybag.common.extensions.errorMsg
import com.beetleware.lovinmybag.common.utils.ImageManger
import com.beetleware.lovinmybag.data.network.model.ApiResponse
import com.beetleware.lovinmybag.data.network.model.ErrorResponse
import java.io.IOException

abstract class BaseFragment<VM : BaseViewModel,
        DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) : Fragment(),
    BaseView {

    lateinit var viewModel: VM
    open lateinit var mBinding: DB

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    private fun getViewM(): VM = ViewModelProviders.of(activity!!).get(mViewModelClass)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

//        menu.findItem(R.id.notification_item).isVisible = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        container?.let {
            initDataBinding(inflater, it)
        }
        initViewModel(viewModel)
        initLifeCycleOwner()
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveDatas()
        init(savedInstanceState)
    }

    @CallSuper
    override fun observeLiveDatas() {
        viewModel.errorResponse.observe(this, Observer(::handleErrorResponse))
    }

    override fun initLifeCycleOwner() {
        mBinding.lifecycleOwner = this
    }


    /**
     *  You need to override this method.
     *  And you need to set viewModel to mBinding: mBinding.viewModel = viewModel
     *
     *  @param viewModel the instance of ViewModel that is related to the  activity
     */
    abstract fun initViewModel(viewModel: VM)



    private fun handleErrorResponse(response: ApiResponse<*>) {
        if (response.exception != null) {

            if (response.isCanceled) return

            hideKeyboard()

            when (response.exception) {
                is IOException -> errorMsg(R.string.msg_error_connection)
                else -> {
                    errorMsg(R.string.msg_something_error)
                }
            }

        } else if (!response.isResponseSuccessful) {
            hideKeyboard()

            when (response.responseCode) {
                302, 500, 503 -> errorMsg(R.string.msg_server_error)
                404 -> {
                    try {
                        val error = Gson().fromJson<ErrorResponse>(
                            response.errorBody?.string(),
                            ErrorResponse::class.java
                        )
                        errorMsg(error.message!!)
                    } catch (e: Exception) {
                        errorMsg(R.string.msg_server_error)
//                        Crashlytics.logException(e)
                    }
                }
                504 -> errorMsg(R.string.no_internet)
                401 -> {
                    val error = Gson().fromJson<ErrorResponse>(
                        response.errorBody?.string(),
                        ErrorResponse::class.java
                    )
                }
                422 -> {
                    try {
                        val error = Gson().fromJson<ErrorResponse>(
                            response.errorBody?.string(),
                            ErrorResponse::class.java
                        )
                        errorMsg(error.errors.values.first { true }[0])
                    } catch (e: Exception) {
                        errorMsg(R.string.msg_something_error)
                    }
                }
                444 -> {
                    try {
                        val error = Gson().fromJson<ErrorResponse>(
                            response.errorBody?.string(),
                            ErrorResponse::class.java
                        )
                        errorMsg(error.message!!)
                    } catch (e: Exception) {
                        val x=e
                        x
                        errorMsg(R.string.msg_something_error)
                    }
                }
                else -> {
                    try {
                        val error = Gson().fromJson<ErrorResponse>(
                            response.errorBody?.string(),
                            ErrorResponse::class.java
                        )
                        errorMsg(error.errors.values.first { true }[0])
                    } catch (e: Exception) {
                        val x=e
                        x
                        errorMsg(R.string.msg_something_error)
                    }

                }
            }
        }
    }

    override fun hideKeyboard() {
        val view = activity?.currentFocus

        if (view != null) {
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        activity?.startActivityForResult(intent, Constants.GALLERY_REQUEST_CODE)
    }

    override fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageManger.save_image_in_provider(context))
        activity?.startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE)
    }


}