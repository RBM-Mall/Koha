package com.bbb.koha.module.forgot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bbb.koha.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ForgotFragment : BottomSheetDialogFragment() {


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
        return inflater.inflate(R.layout.fragment_forgot, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ForgotFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}