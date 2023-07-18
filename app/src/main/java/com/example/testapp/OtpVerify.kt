package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.databinding.OtpBinding

class OtpVerify : AppCompatActivity() {
    private lateinit var binding: OtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = OtpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.otpverifyContunueId.setOnClickListener {
            var data1 = binding.EditTextOtp1.text.toString()
            var data2 = binding.otpEditText2 .text.toString()
            var data3 = binding.otpEditText3 .text.toString()
            var data4 = binding.otpEditText4 .text.toString()

            val opts= data1+data2+data3+data4

            verifyotp(opts)
        }


    }

    private fun verifyotp(opts: String) {

    }
}