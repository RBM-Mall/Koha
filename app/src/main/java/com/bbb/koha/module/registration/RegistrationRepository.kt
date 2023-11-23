package com.bbb.koha.module.registration

import com.bbb.koha.login.model.ValidateUserRequestModel
import com.bbb.koha.network.RetrofitInstance


/**
 *Created by Gaurav Kumar on 7/28/2022
 * QUYTECH
 */
class RegistrationRepository {
    suspend fun validateUser(validateUserRequestModel: ValidateUserRequestModel) = RetrofitInstance.apiService?.validateUser(validateUserRequestModel)
    suspend fun getUserDetail(patronId: Int?) = RetrofitInstance.apiService?.getUserDetail(patronId)
    suspend fun register(body:Map<String,String>) = RetrofitInstance.apiService?.register(body)
    suspend fun getLibraries() = RetrofitInstance.apiService?.getLibraries()
}