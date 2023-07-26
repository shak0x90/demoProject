package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.adapters.AddProductAdapter
import com.example.testapp.adapters.ServiceCardAdapter
import com.example.testapp.databinding.ActivityAdProductBinding
import com.example.testapp.databinding.ActivityProductsBinding
import com.example.testapp.mehedi.LoginResponseModel
import com.example.testapp.mehedi.RetrofitClient
import com.example.testapp.mehedi.addproduct.AddProData
import com.example.testapp.mehedi.getServiceList.Data
import com.example.testapp.mehedi.getproductlist.GetProductResponse
import com.example.testapp.mehedi.getproductlist.getProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProductsBinding
    private lateinit var apapter:AddProductAdapter
    private lateinit var  datalis:List<getProductData>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProductsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)


        setContentView(binding.root)
        getProductList()
        binding.BtnAddproduct.setOnClickListener {
            val intent = Intent(this@ProductsActivity, AdProduct::class.java)
            startActivity(intent)
            finish()
        }



    }

    private fun getProductList() {
        RetrofitClient.getService().getProductList(127,ctoken).enqueue(object :
            Callback<GetProductResponse> {
            override fun onResponse(
                call: Call<GetProductResponse>,
                response: Response<GetProductResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ProductsActivity, "Successfully loaded", Toast.LENGTH_SHORT).show()
                    binding.BtnAddproduct.setOnClickListener {
                        val intent = Intent(this@ProductsActivity, AdProduct::class.java)
                        startActivity(intent)
                        finish()
                    }
                    //datalis = arrayListOf<getProductData>(response.body()!!.data)
                    datalis = response.body()!!.data

                    Log.e("TAG", "error $datalis")

                    apapter = AddProductAdapter(datalis,this@ProductsActivity)

                    binding.reclyerVid .layoutManager = LinearLayoutManager(this@ProductsActivity)
                    binding.reclyerVid.adapter = apapter
                    apapter.onItemclick = {
                        val intent = Intent(this@ProductsActivity,EditDetailsActivity::class.java).putExtra("getData",it)
                        startActivity(intent)
                        finish()
                    }



                }
            }

            override fun onFailure(call: Call<GetProductResponse>, t: Throwable) {
                Toast.makeText(this@ProductsActivity, "Failed", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "error ${t.localizedMessage}")

            }
        })
    }
}