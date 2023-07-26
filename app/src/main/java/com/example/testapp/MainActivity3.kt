package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testapp.adapters.ServiceCardAdapter
import com.example.testapp.databinding.ActivityMain3Binding
import com.example.testapp.mehedi.RetrofitClient
import com.example.testapp.mehedi.getServiceList.Data
import com.example.testapp.mehedi.getServiceList.GetServiceModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var  datalis:List<Data>
    private lateinit var adapter:ServiceCardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain3Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        getServices()
    }

    private fun getServices() {
       RetrofitClient.getService().getServiceList().enqueue(
           object :Callback<GetServiceModel>{
               override fun onResponse(
                   call: Call<GetServiceModel>,
                   response: Response<GetServiceModel>
               ) {
                   if (response.isSuccessful){
                       Toast.makeText(this@MainActivity3, "Successfully OTp  Verified", Toast.LENGTH_SHORT).show()
                       Log.e("TAG2", "Success ${response.body().toString()}")
                       datalis = response.body()!!.data

                       adapter = ServiceCardAdapter(datalis,this@MainActivity3)

                       binding.RecylerIdService.layoutManager = GridLayoutManager(this@MainActivity3,2)
                       binding.RecylerIdService.adapter = adapter

                   }
               }

               override fun onFailure(call: Call<GetServiceModel>, t: Throwable) {
                   Toast.makeText(this@MainActivity3, "Failed", Toast.LENGTH_SHORT).show()
                   Log.e("TAG", "error ${t.localizedMessage}")
               }

           }
       )


    }
}