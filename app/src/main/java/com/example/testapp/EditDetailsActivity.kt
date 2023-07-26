package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testapp.databinding.ActivityEditDetailsBinding
import com.example.testapp.mehedi.DeleteResponseClass
import com.example.testapp.mehedi.RetrofitClient
import com.example.testapp.mehedi.addproduct.AddProductResponseModel
import com.example.testapp.mehedi.getproductlist.GetProductResponse
import com.example.testapp.mehedi.getproductlist.getProductData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class EditDetailsActivity : AppCompatActivity() {
    private  var id:Int=0
    private var  name:String= ""
    private  var price:Double = 0.0
    private lateinit var binding: ActivityEditDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var getProduct = intent.getParcelableExtra<getProductData>("getData")
        //val getProduct = intent.getParcelableExtra<getProductData>("getData")
        if (getProduct!=null){
             id = getProduct.id
             name = getProduct.name
            price = getProduct.price.toDouble()

            binding.EDproductname.setText(name)
            binding.EDproductPrcie.setText(price.toString())

            Log.i("TAGED",getProduct.toString())

        }
        binding.BtnSave.setOnClickListener {
            var name = binding.EDproductname.text.toString()
            var price = binding.EDproductPrcie.text.toString().toDouble()

            updateProduct(name,price)
           //
        }
        
        binding.BtnReset.setOnClickListener {
            deletePrpduct()
        }



    }

    private fun deletePrpduct() {
        RetrofitClient.getService().delteProduct(id, ctoken).enqueue(
            object : Callback<DeleteResponseClass>{
                override fun onResponse(
                    call: Call<DeleteResponseClass>,
                    response: Response<DeleteResponseClass>
                ) {
                    Toast.makeText(this@EditDetailsActivity, "Successfully request Done ", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@EditDetailsActivity, ProductsActivity::class.java)
                    startActivity(intent)

                    Log.i("TAGR", "ok ${response.body().toString()}")
                }

                override fun onFailure(call: Call<DeleteResponseClass>, t: Throwable) {
                    Toast.makeText(this@EditDetailsActivity, "Failed", Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "error ${t.localizedMessage}")
                }

            }
        )

    }

    private fun updateProduct(name: String, price: Double) {
        RetrofitClient.getService().updateProduct(id, ctoken,name,price).enqueue(
            object : Callback<AddProductResponseModel>{
                override fun onResponse(
                    call: Call<AddProductResponseModel>,
                    response: Response<AddProductResponseModel>
                ) {
                    Toast.makeText(this@EditDetailsActivity, "Successfully request sent ", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@EditDetailsActivity, ProductsActivity::class.java)
                    startActivity(intent)

                    Log.i("TAGR", "ok ${response.body().toString()}")
                }

                override fun onFailure(call: Call<AddProductResponseModel>, t: Throwable) {
                    Toast.makeText(this@EditDetailsActivity, "Failed", Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "error ${t.localizedMessage}")
                }

            }
        )
    }
}