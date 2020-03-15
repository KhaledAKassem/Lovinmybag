package com.beetleware.lovinmybag.ui.fragments.login

import android.app.Application
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.beetleware.lovinmybag.common.extensions.forEach
import com.beetleware.lovinmybag.data.network.model.ApiResponse
import com.beetleware.lovinmybag.data.network.model.GeneralResponse
import com.beetleware.lovinmybag.data.network.model.LoginResponse
import com.beetleware.lovinmybag.data.network.model.VerificationResponse
import com.beetleware.lovinmybag.ui.base.BaseViewModel
import com.beetleware.lovinmybag.ui.customs.CustomEditText
import com.beetleware.lovinmybag.ui.customs.CustomMaterialEditText

class LoginViewModel (app:Application): BaseViewModel(app) {

    val isDataValid by lazy {
        MutableLiveData<Boolean>()
    }

    fun validateInputData(view : View){
        var isInputDataValid = true
        (view.rootView.rootView as ViewGroup).forEach {
            if (it is CustomMaterialEditText && !it.validateContent())
                isInputDataValid = false
            else if (it is CustomEditText && !it.validateContent())
                isInputDataValid = false
        }
        isDataValid.postValue(isInputDataValid)
    }

    fun login(userName:String,password:String): MutableLiveData<ApiResponse<GeneralResponse<LoginResponse>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.login(userName,password)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun storeUser(loginResponse: LoginResponse) = appRepositoryHelper.storeUser(loginResponse)

}