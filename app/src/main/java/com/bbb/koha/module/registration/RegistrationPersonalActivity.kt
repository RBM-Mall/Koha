package com.bbb.koha.module.registration

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.bbb.koha.R
import com.bbb.koha.app.MVVMBindingActivity
import com.bbb.koha.common.Constant
import com.bbb.koha.databinding.ActivityRegistrationPersonalBinding
import java.text.SimpleDateFormat
import java.util.*


class RegistrationPersonalActivity : MVVMBindingActivity<ActivityRegistrationPersonalBinding>() {
    val myCalendar = Calendar.getInstance()
    private lateinit var date :DatePickerDialog.OnDateSetListener
    override fun initializeView() {
        binding?.btnRegister?.setOnClickListener(this)
        binding?.etDOB?.setOnClickListener(this)

        binding?.rgGender?.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," On checked change :"+
                        " ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            })

        date = OnDateSetListener { _, year, month, day ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)
                updateLabel()
            }
    }

    private fun updateLabel() {
        val myFormat = "dd-MM-yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        binding?.etDOB?.setText(dateFormat.format(myCalendar.time))
    }

    override fun provideViewResource(): Int {
        return R.layout.activity_registration_personal
    }

    private fun validate(){
        binding?.run {
            if(TextUtils.isEmpty(etFirstName.text.toString())){
                Toast.makeText(this@RegistrationPersonalActivity,"Please enter first name", Toast.LENGTH_LONG).show()
            }else if(TextUtils.isEmpty(etLastName.text.toString())){
                Toast.makeText(this@RegistrationPersonalActivity,"Please enter last name", Toast.LENGTH_LONG).show()
            }else if(TextUtils.isEmpty(etDOB.text.toString())){
                Toast.makeText(this@RegistrationPersonalActivity,"Please enter date of birth", Toast.LENGTH_LONG).show()
            }else if(TextUtils.isEmpty(etAddress.text.toString())){
                Toast.makeText(this@RegistrationPersonalActivity,"Please enter address", Toast.LENGTH_LONG).show()
            }else {
                var intent = Intent(this@RegistrationPersonalActivity,RegistrationLibraryActivity::class.java)
                intent.putExtra(Constant.FIRST_NAME,etFirstName.text.toString())
                intent.putExtra(Constant.LAST_NAME,etLastName.text.toString())
                intent.putExtra(Constant.DOB,etDOB.text.toString())
                intent.putExtra(Constant.ADDRESS,etAddress.text.toString())
                startActivity(intent)
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            binding?.btnRegister?.id -> validate()
            binding?.etDOB?.id -> {
                DatePickerDialog(
                    this@RegistrationPersonalActivity,
                    date,
                    myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]
                ).show()
            }
        }
    }
}