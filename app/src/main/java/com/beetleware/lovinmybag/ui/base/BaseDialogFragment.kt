package com.beetleware.lovinmybag.ui.base

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beetleware.lovinmybag.R
import com.google.gson.Gson
import com.beetleware.lovinmybag.data.network.model.ErrorResponse
import com.beetleware.lovinmybag.common.Constants
import com.beetleware.lovinmybag.common.extensions.errorMsg
import com.beetleware.lovinmybag.common.utils.ImageManger
import com.beetleware.lovinmybag.data.network.model.ApiResponse
import java.io.IOException

abstract class BaseDialogFragment<VM : BaseViewModel,
        DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) : DialogFragment(), BaseView {

    lateinit var viewModel: VM
    open lateinit var mBinding: DB


    fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }


    private fun getViewM(): VM = ViewModelProviders.of(activity!!).get(mViewModelClass)

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        return super.onGetLayoutInflater(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        viewModel = getViewM()
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        initDataBinding(inflater, container)

        initViewModel(viewModel)
        initLifeCycleOwner()

        super.onCreateView(inflater, container, savedInstanceState)

        return mBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(null)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, null)
        observeLiveDatas()
        init(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    @CallSuper
    override fun observeLiveDatas() {

        viewModel.isUpBtnClicked.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                viewModel.isUpBtnClicked.value = false
                dismissAllowingStateLoss()
            }
        })

        viewModel.errorResponse.observe(this, Observer (::handleErrorResponse))
    }

    override fun onResume() {

        // Get existing layout params for the window
        val params = dialog?.window?.attributes
        // Assign window properties to fill the parent
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams

        super.onResume()
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
//                    Crashlytics.logException(response.exception)
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
                    val error = Gson().fromJson(
                        response.errorBody?.string(),
                        ErrorResponse::class.java
                    )
//                    viewModel.setUnAuthorizedMsg(error.message ?: "")
//                    viewModel.removeUser()
                }
                else -> {
                    try {
                        val error = Gson().fromJson<ErrorResponse>(
                            response.errorBody?.string(),
                            ErrorResponse::class.java
                        )
                           errorMsg(error.message!!)
                    } catch (e: Exception) {
                        errorMsg(R.string.msg_something_error)

                    }

                }
            }
        }
    }

    override fun hideKeyboard() {
        val view = dialog?.currentFocus
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