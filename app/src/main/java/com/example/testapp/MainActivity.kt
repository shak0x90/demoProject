package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var phoneNum:String
    private lateinit var userPass :String
    private lateinit var rootResponse:UserLogin
    private lateinit var apiServices:RetroServices
    private lateinit var retrofitInstance:RetrofitInstance
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.borwseAsguestArrow.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
             startActivity(intent)
        }

        retrofitInstance = RetrofitInstance()

        binding.logintbtn.setOnClickListener {
            userPass = binding.phonnoEditTxtid.text.toString().trim()
            phoneNum = binding.paswordEditext.text.toString().trim()

            apiServices = retrofitInstance?.getRetrofitInstance()?.create(RetroServices::class.java)!!
            val requestBody = LoginRequest(userPass!!, phoneNum!!)
            val call: Call<UserLogin>? = apiServices?.getUser(requestBody)!!

            call?.enqueue(object : Callback<UserLogin> {
                override fun onResponse(
                    call: Call<UserLogin>,
                    response: Response<UserLogin>
                ) {
                    if (response.isSuccessful) {
                        rootResponse = response.body()!!
                        var loginCheck: Boolean = true
                        if (rootResponse?.success == loginCheck) {
                            var loginMsg: String? = rootResponse?.message
                            var userData: Data?=rootResponse?.data
                            var userToken:String?=userData?.access_token
                            Toast.makeText(this@MainActivity, loginMsg, Toast.LENGTH_LONG).show()
                            val intent = Intent(this@MainActivity, MainActivity2::class.java)
                            intent.putExtra("access_token",userToken)
                            startActivity(intent)
                        } else Toast.makeText(this@MainActivity, "Login Failed", Toast.LENGTH_LONG)
                            .show()


                    } else {
                        val errorMessage = response.message()
                        Log.d("EWN2745", "Response: ${response.body()}")

                        Toast.makeText(
                            this@MainActivity,
                            "Login Fail : $errorMessage",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                    Log.e("EWN2745", "Login rLAPI call failed:", t)
                    Toast.makeText(this@MainActivity, "Login Unsuccessful", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}