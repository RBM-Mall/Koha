package com.bbb.koha.app

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.ImageView
import androidx.databinding.DataBindingUtil


abstract class MVVMBindingActivity<B> : BaseActivity() {
    protected var binding: B? = null

    //protected var viewModel :V? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, provideViewResource())
        //val viewModel: V by  provideViewModel() { ViewModalFactoryClass(application) }
        //viewModel = ViewModelProvider(this, ViewModalFactoryClass(application)).get(provideViewModel())
        initializeView()
    }

    abstract fun initializeView()

    abstract fun provideViewResource(): Int

    fun onBackArrow(backArrow: ImageView) {
        backArrow.setOnClickListener {
            finish()
        }
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


}