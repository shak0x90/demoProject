package com.example.testapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.testapp.databinding.ActivityEditDetailsBinding
import com.example.testapp.mehedi.DeleteResponseClass
import com.example.testapp.mehedi.RetrofitClient
import com.example.testapp.mehedi.addproduct.AddProductResponseModel
import com.example.testapp.mehedi.getproductlist.GetProductResponse
import com.example.testapp.mehedi.getproductlist.Images
import com.example.testapp.mehedi.getproductlist.getProductData
import com.google.android.material.imageview.ShapeableImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class EditDetailsActivity : AppCompatActivity() {
    private var id: Int = 0
    private var name: String = ""
    private var price: Double = 0.0
    private lateinit var image1: ShapeableImageView
    private lateinit var image2: ShapeableImageView
    private lateinit var image3: ShapeableImageView
    private lateinit var image4: ShapeableImageView
    private lateinit var image5: ShapeableImageView



    private lateinit var image: List<Images>
    private lateinit var binding: ActivityEditDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var imaglayou1 = binding.LayoutRl1img
        var imaglayou2 = binding.LayoutRl2img
        var imaglayou3 = binding.LayoutRl3img
        var imaglayou4 = binding.LayoutRl4img
        var imaglayou5 = binding.LayoutRl5img

        image1 = binding.imageprofile1
        image2 = binding.imageprofile2
        image3 = binding.imageprofile3
        image4 = binding.imageprofile4
        image5 = binding.imageprofile5




        var listImage = arrayListOf<ShapeableImageView>(image1, image2, image3, image4, image5)



        var listLayout = arrayListOf(imaglayou1,imaglayou2,imaglayou3,imaglayou4,imaglayou5)


        var getProduct = intent.getParcelableExtra<getProductData>("getData")


        //val getProduct = intent.getParcelableExtra<getProductData>("getData")
        if (getProduct != null) {
            id = getProduct.id
            name = getProduct.name
            price = getProduct.price.toDouble()

            image = getProduct.images!!

            binding.EDproductname.setText(name)
            binding.EDproductPrcie.setText(price.toString())


//
//
//            if (image!=null) {
//
//                val imagesSize = image.size
//              var id: Int = 0

            Log.i("TAGED", getProduct.images.toString())

//                for (id in 0..imagesSize) {
//                    if (image[id].image!=null) {
            if (getProduct.images != null) {
                var imazwsize = getProduct.images!!.size

                for (itemid in 0 until imazwsize) {
                    Glide.with(this).load(getProduct.images!![itemid].image).into(listImage[itemid])
                    listLayout[itemid].isVisible = true

                }
            } else {
                Log.i("TAGED", "error images null ${getProduct.images}")

            }

        }
        binding.BtnSave.setOnClickListener {
            var name = binding.EDproductname.text.toString()
            var price = binding.EDproductPrcie.text.toString().toDouble()

            updateProduct(name, price)
            //
        }

        binding.BtnReset.setOnClickListener {
            deletePrpduct()
        }


    }

    private fun deletePrpduct() {
        RetrofitClient.getService().delteProduct(id, ctoken).enqueue(
            object : Callback<DeleteResponseClass> {
                override fun onResponse(
                    call: Call<DeleteResponseClass>,
                    response: Response<DeleteResponseClass>
                ) {
                    Toast.makeText(
                        this@EditDetailsActivity,
                        "Successfully request Done ",
                        Toast.LENGTH_SHORT
                    ).show()
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
        RetrofitClient.getService().updateProduct(id, ctoken, name, price).enqueue(
            object : Callback<AddProductResponseModel> {
                override fun onResponse(
                    call: Call<AddProductResponseModel>,
                    response: Response<AddProductResponseModel>
                ) {
                    Toast.makeText(
                        this@EditDetailsActivity,
                        "Successfully request sent ",
                        Toast.LENGTH_SHORT
                    ).show()
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