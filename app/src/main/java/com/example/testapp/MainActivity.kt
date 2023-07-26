package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.mehedi.LoginRequestModel
import com.example.testapp.mehedi.LoginResponseModel
import com.example.testapp.mehedi.RetrofitClient
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

        binding.registertxtBtn.setOnClickListener {
            Toast.makeText(this, "Sign up page", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        retrofitInstance = RetrofitInstance()


    binding.logintbtn.setOnClickListener {
        phoneNum = binding.phonnoEditTxtid.text.toString().trim()
        userPass = binding.paswordEditext.text.toString().trim()
        Toast.makeText(this@MainActivity, "clicked", Toast.LENGTH_SHORT).show()
        Toast.makeText(this@MainActivity, "$phoneNum", Toast.LENGTH_SHORT).show()
        Toast.makeText(this@MainActivity, "$userPass", Toast.LENGTH_SHORT).show()


        userLogin(userPass, phoneNum)

    }

//            binding.logintbtn.setOnClickListener {
//
//
//          apiServices = retrofitInstance?.getRetrofitInstance()?.create(RetroServices::class.java)!!
//          val requestBody = LoginRequest(userPass!!, phoneNum!!)
//         val call: Call<UserLogin>? = apiServices?.getUser(requestBody)!!
//
//        call?.enqueue(object : Callback<UserLogin> {
//              override fun onResponse(
//                  call: Call<UserLogin>,
//                    response: Response<UserLogin>
//                ) {                   if (response.isSuccessful) {
//
//                                        rootResponse = response.body()!!
//                       var loginCheck: Boolean = true
//                        if (rootResponse?.success == loginCheck) {
//                           var loginMsg: String? = rootResponse?.message
//                          var userData: Data?=rootResponse?.data
//                          var userToken:String?=userData?.access_token
//                           Toast.makeText(this@MainActivity, loginMsg, Toast.LENGTH_LONG).show()
//                          val intent = Intent(this@MainActivity, MainActivity2::class.java)
//                        intent.putExtra("access_token",userToken)
//                            startActivity(intent)
//                       } else Toast.makeText(this@MainActivity, "Login Failed1", Toast.LENGTH_LONG)
//                            .show()
//
//
//                   } else {
//                      val errorMessage = response.message()
//                        Log.d("EWN2745", "Response: ${response.body()}")
//
//                       Toast.makeText(
//                            this@MainActivity,
//                           "Login Fail : $errorMessage",
//                           Toast.LENGTH_LONG
//                       ).show()
//                  }
//            }
//
//             override fun onFailure(call: Call<UserLogin>, t: Throwable) {
//                 Log.e("EWN2745", "Login rLAPI call failed:", t)
//                   Toast.makeText(this@MainActivity, "Login Unsuccessful", Toast.LENGTH_LONG).show()
//                }
//           })
//       }
//    }



        }


    private fun userLogin(password: String, phoneNumber: String) {
        RetrofitClient.getService()
            .userLogin(LoginRequestModel(phoneNumber, password))
            .enqueue(object : Callback<LoginResponseModel> {
                override fun onResponse(
                    call: Call<LoginResponseModel>,
                    response: Response<LoginResponseModel>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Successfully logged in", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, AdProduct::class.java).putExtra("token",response.body()?.data!!.access_token)
                        startActivity(intent)


                    }
                }

                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "error ${t.localizedMessage}")

                }
            })


    }

}

