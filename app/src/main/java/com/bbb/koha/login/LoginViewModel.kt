package com.bbb.koha.login

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbb.koha.R
import com.bbb.koha.login.model.UserDetailResponseModel
import com.bbb.koha.login.model.ValidateUserRequestModel
import com.bbb.koha.login.model.ValidateUserResponseModel
import com.bbb.koha.network.Resource
import com.bbb.koha.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(var app: Application) : ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private var mContext: Context = app.applicationContext
    private val repository = LoginRepository()
    private var mValidateUserResponseModel = MutableLiveData<Resource<ValidateUserResponseModel>>()
    var validateUserResponseModel: LiveData<Resource<ValidateUserResponseModel>> = mValidateUserResponseModel
    private var mUserDetailResponseModel = MutableLiveData<Resource<UserDetailResponseModel>>()
    var userDetailResponseModel: LiveData<Resource<UserDetailResponseModel>> = mUserDetailResponseModel


    fun validateUser(validateUserRequestModel: ValidateUserRequestModel) {
        if (Utils.hasInternetConnection(mContext)) {
            viewModelScope.launch {
                val response = repository.validateUser(validateUserRequestModel)
                mValidateUserResponseModel.value = response?.let { handleValidateUserResponse(it) }
            }
        } else mValidateUserResponseModel.value =
            Resource.Error(app.resources.getString(R.string.no_internet))
    }

    private fun handleValidateUserResponse(response: Response<ValidateUserResponseModel>): Resource<ValidateUserResponseModel>? {
        //if (response.isSuccessful) {
            response.body()?.let {
                return when (response.code()) {
                    201 -> Resource.Success("Success",it)
                    else -> Resource.Error(it.error)
                }
            }
       // }
        return Resource.Error(response.message())
    }

    fun getUserDetail(patronId: Int?) {
        if (Utils.hasInternetConnection(mContext)) {
            viewModelScope.launch {
                val response = repository.getUserDetail(patronId)
                mUserDetailResponseModel.value = response?.let { handleUserDetailResponse(it) }
            }
        } else mUserDetailResponseModel.value = Resource.Error(app.resources.getString(R.string.no_internet))
    }

    private fun handleUserDetailResponse(response: Response<UserDetailResponseModel>): Resource<UserDetailResponseModel>? {
        //if (response.isSuccessful) {
            response.body()?.let {
                return when (response.code()) {
                    200 -> Resource.Success("Success",it)
                    else -> Resource.Error(it.error)
                }
            }
        //}
        return Resource.Error(response.message())
    }

    /*fun register(body:Map<String,String>) {
        if (Utils.hasInternetConnection(mContext)) {
            viewModelScope.launch {
                val response = repository.register(body)
                mLoginResponseModel.value = response?.let { handleLoginResponse(it) }
            }
        } else mLoginResponseModel.value =
            Resource.Error(app.resources.getString(R.string.no_internet))
    }

    private fun handleLoginResponse(response: Response<List<RegisterResponseModel>>): Resource<RegisterResponseModel>? {
        if (response.isSuccessful) {
            response.body()?.let {
                return when (response.code()) {
                    200 -> Resource.Success(it[0].Response)
                    else -> Resource.Error(it[0].Response)
                }
            }
        }
        return Resource.Error(response.message())
    }*/
}