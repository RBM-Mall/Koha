package com.bbb.koha.module.reset_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bbb.koha.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ResetPasswordFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ResetPasswordFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}