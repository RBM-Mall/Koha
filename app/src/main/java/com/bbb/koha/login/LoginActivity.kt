package com.bbb.koha.login

import android.Manifest
import android.annotation.SuppressLint
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bbb.koha.R
import com.bbb.koha.app.MVVMBindingActivity
import com.bbb.koha.dashboard.MainActivity
import com.bbb.koha.databinding.ActivityLoginBinding
import com.bbb.koha.login.model.ValidateUserRequestModel
import com.bbb.koha.module.forgot.ForgotFragment
import com.bbb.koha.module.registration.RegistrationLibraryActivity
import com.bbb.koha.module.registration.RegistrationPersonalActivity
import com.bbb.koha.network.Resource
import com.bbb.koha.network.ViewModelFactoryClass
import com.bbb.koha.utils.ProgressDialog
import com.google.gson.Gson


class LoginActivity : MVVMBindingActivity<ActivityLoginBinding>() {
    private lateinit var viewModel:LoginViewModel
    private var isPasswordVisible = false
    private val permissionList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    lateinit var bottomSheetFragment: ForgotFragment
    override fun initializeView() {


        viewModel = ViewModelProvider(
            this,
            ViewModelFactoryClass(application)
        )[LoginViewModel::class.java]
        binding?.run {
            tvForgotPassword.setOnClickListener {
                bottomSheetFragment = ForgotFragment()
                bottomSheetFragment.show(supportFragmentManager, "BSDialogFragment")
            }
            tvRegister.setOnClickListener(this@LoginActivity)
            btnLogin.setOnClickListener(this@LoginActivity)
            ivShowHidePassword.setOnClickListener(this@LoginActivity)
        }

        setObserver()
    }



    private fun validate() {
        binding?.run {
             if(TextUtils.isEmpty(etUsername.text.toString())){
                Toast.makeText(this@LoginActivity,getString(R.string.please_enter_username),Toast.LENGTH_LONG).show()
            }else if(TextUtils.isEmpty(etPassword.text.toString())){
                Toast.makeText(this@LoginActivity,getString(R.string.please_enter_password),Toast.LENGTH_LONG).show()
            }else {
                 var validateUser = ValidateUserRequestModel(etUsername.text.toString(),etPassword.text.toString())
                 viewModel.validateUser(validateUser)
            }
        }
    }

    private fun setObserver() {
        viewModel.validateUserResponseModel.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    ProgressDialog.hideProgressBar()
                    getUserDetail(response.data?.patronId)
                }
                is Resource.Loading -> {
                    ProgressDialog.showProgressBar(this)
                }
                is Resource.Error -> {
                    ProgressDialog.hideProgressBar()
                    response.message?.let { showToast(it) }
                }
                else -> {
                    ProgressDialog.hideProgressBar()
                    response.message?.let { showToast(it) }
                }
            }
        }

        viewModel.userDetailResponseModel.observe(this) { response ->
            var gson = Gson().toJson(response)
            Log.e("viewModel","${gson}")
            when (response) {
                is Resource.Success -> {
                    ProgressDialog.hideProgressBar()
                    startNewActivity(MainActivity())
                }
                is Resource.Loading -> {
                    ProgressDialog.showProgressBar(this)
                }
                is Resource.Error -> {
                    ProgressDialog.hideProgressBar()
                    response.message?.let { showToast(it) }
                }
                else -> {
                    ProgressDialog.hideProgressBar()
                    response.message?.let { showToast(it) }
                }
            }
        }
    }

    private fun getUserDetail(patronId: Int?){
        viewModel.getUserDetail(patronId)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showHidePassword(){
        if (!isPasswordVisible) {
            binding?.etPassword?.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding?.ivShowHidePassword?.setImageDrawable(resources.getDrawable(R.drawable.ic_password_hide))
            isPasswordVisible = true
        } else {
            binding?.etPassword?.transformationMethod = PasswordTransformationMethod.getInstance()
            binding?.ivShowHidePassword?.setImageDrawable(resources.getDrawable(R.drawable.ic_password_hide))
            isPasswordVisible = false
        }
    }


    override fun provideViewResource(): Int {
       return R.layout.activity_login
    }

    override fun onClick(view: View?) {
        binding?.run {
            when(view?.id){
                btnLogin.id->validate()
                tvRegister.id->startNewActivity(RegistrationPersonalActivity())
                ivShowHidePassword.id->showHidePassword()
                else -> {}
            }

        }
    }

}