package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.testapp.databinding.ActivitySignUpBinding
import com.example.testapp.mehedi.RetrofitClient
import com.example.testapp.mehedi.regitation.RegisterRequestModel
import com.example.testapp.mehedi.regitation.RegisterResponseModel
import com.example.testapp.mehedi.regitation.RegisterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.regisBtnBottomId.setOnClickListener {
            val fName = binding.edittextfname.text.toString()
            val lName = binding.edittextlanmetv .text.toString()
            val password = binding.paswordEditext .text.toString()
            val confirmPass = binding.confirmpaassEditext .text.toString()
            val phoneNumber = binding.phonnoEditTxtid.text.toString()
            val emailOptional = binding.emailEdid .text.toString()
            userRegister(fName,lName,password,phoneNumber,emailOptional)
        }
    }

    private fun userRegister(fName: String, lName: String, password: String, phoneNumber: String, emailOptional: String) {
        RetrofitClient.getService().userRegister(RegisterRequestModel(fName,lName,password,phoneNumber,emailOptional)).enqueue(
            object:Callback<RegisterResponseModel>{
                override fun onResponse(
                    call: Call<RegisterResponseModel>,
                    response: Response<RegisterResponseModel>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@SignUpActivity, "Successfully Sigged up", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignUpActivity, OtpVerify::class.java).putExtra("phone",phoneNumber)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity, "Failed", Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "error ${t.localizedMessage}")
                }

            }

        )

    }


}




