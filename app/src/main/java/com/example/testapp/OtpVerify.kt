package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testapp.databinding.OtpBinding
import com.example.testapp.mehedi.RetrofitClient
import com.example.testapp.mehedi.otp.OtpRequsetModel
import com.example.testapp.mehedi.otp.OtpResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val phoneNo = intent.getStringExtra("phone")
            val forgetpass:Boolean = false;

            val opts= data1+data2+data3+data4

            if (phoneNo != null) {
                verifyotp(opts,phoneNo,forgetpass)
            }else{
                Toast.makeText(this, "Check ur phone no is correct", Toast.LENGTH_SHORT).show()

            }
        }


    }

    private fun verifyotp(opts: String,phoneNo:String,forgetpass:Boolean) {

        RetrofitClient.getService().verifyOTP(OtpRequsetModel(forgetpass, opts,phoneNo)).enqueue(
            object :Callback<OtpResponseModel>{
                override fun onResponse(
                    call: Call<OtpResponseModel>,
                    response: Response<OtpResponseModel>
                ) {
                    Toast.makeText(this@OtpVerify, "Successfully OTp  Verified", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@OtpVerify, MainActivity2::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<OtpResponseModel>, t: Throwable) {
                    Toast.makeText(this@OtpVerify, "Failed", Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "error ${t.localizedMessage}")
                }

            }
        )
    }
}