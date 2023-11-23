package com.bbb.koha.module.registration

import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.bbb.koha.R
import com.bbb.koha.app.MVVMBindingActivity
import com.bbb.koha.databinding.ActivityRegistrationLibraryBinding
import com.bbb.koha.module.registration.model.AllLibraryResponseModel
import com.bbb.koha.network.Resource
import com.bbb.koha.network.ViewModelFactoryClass
import com.bbb.koha.utils.ProgressDialog

class RegistrationLibraryActivity : MVVMBindingActivity<ActivityRegistrationLibraryBinding>() {
    private lateinit var viewModel:RegistrationViewModel
    private var libraryList = ArrayList<String>()
    override fun initializeView() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactoryClass(application)
        )[RegistrationViewModel::class.java]
        viewModel.getLibraries()

        setObserver()
    }

    private fun setObserver() {
        viewModel.allLibraryResponseModel.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    ProgressDialog.hideProgressBar()
                    setupSpinnerLibrary(response.data)
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

    private fun setupSpinnerLibrary(list: List<AllLibraryResponseModel>?) {
        libraryList.clear()
        libraryList.add(getString(R.string.select_library))
        list?.forEach {
            it.name?.let { it1 -> libraryList.add(it1) }
        }
        var arrayAdapter = ArrayAdapter(this@RegistrationLibraryActivity,android.R.layout.simple_dropdown_item_1line,libraryList)
        binding?.spinnerLibrary?.adapter = arrayAdapter
    }

    override fun provideViewResource(): Int {
        return R.layout.activity_registration_library
    }

    override fun onClick(p0: View?) {

    }
}