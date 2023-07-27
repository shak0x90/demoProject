package com.example.testapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.testapp.databinding.ActivityEditDetailsBinding
import com.example.testapp.mehedi.DeleteResponseClass
import com.example.testapp.mehedi.RetrofitClient
import com.example.testapp.mehedi.addproduct.AddProductResponseModel
import com.example.testapp.mehedi.getproductlist.Images
import com.example.testapp.mehedi.getproductlist.getProductData
import com.example.testapp.mehedi.utils.RealPathUtil
import com.google.android.material.imageview.ShapeableImageView
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class EditDetailsActivity : AppCompatActivity() {

    private var path: String? = null
    private var path1: String? = null
    private var path2: String? = null
    private var path3: String? = null
    private var path4: String? = null
    private var id: Int = 0
    private var name: String = ""
    private var price: Double = 0.0
    private lateinit var image1: ShapeableImageView
    private lateinit var image2: ShapeableImageView
    private lateinit var image3: ShapeableImageView
    private lateinit var image4: ShapeableImageView
    private lateinit var image5: ShapeableImageView

    //private lateinit var file: ArrayList<File>

    //private lateinit var requatbody: ArrayList<RequestBody>



    //private lateinit var imageUri:List<String>
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

        var btllayout1 = binding.LyoutRl1
        var btllayout2 = binding.LyoutRl2
        var btllayout3 = binding.LyoutRl3
        var btllayout4 = binding.LyoutRl4
        var btllayout5 = binding.LyoutRl5

        var listLayoutBtn = arrayListOf(btllayout1, btllayout2, btllayout3, btllayout4, btllayout5)

        binding.Btnimage1.setOnClickListener {
            btllayout1.isVisible = true
            imaglayou1.isVisible = false
        }
        binding.Btnimage2.setOnClickListener {
            btllayout2.isVisible = true
            imaglayou2.isVisible = false
        }
        binding.Btnimage3.setOnClickListener {
            btllayout3.isVisible = true
            imaglayou3.isVisible = false

        }
        binding.Btnimage4.setOnClickListener {
            btllayout4.isVisible = true
            imaglayou4.isVisible = false
        }
        binding.Btnimage5.setOnClickListener {
            btllayout5.isVisible = true
            imaglayou5.isVisible = false
        }
        binding.Btnuploadimg1.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeImage.launch(pickImg)
        }
        binding.Btnuploadimg2.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            changeImage2.launch(pickImg)
        }

        binding.Btnuploadimg3.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            changeImage3.launch(pickImg)
        }

        binding.Btnuploadimg4.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            changeImage4.launch(pickImg)
        }

        binding.Btnuploadimg5.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            changeImage5.launch(pickImg)
        }

        image1 = binding.imageprofile1
        image2 = binding.imageprofile2
        image3 = binding.imageprofile3
        image4 = binding.imageprofile4
        image5 = binding.imageprofile5


        var listImage = arrayListOf<ShapeableImageView>(image1, image2, image3, image4, image5)


        var listLayout = arrayListOf(imaglayou1, imaglayou2, imaglayou3, imaglayou4, imaglayou5)


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
            var name = binding.EDproductname.text.toString().trim()
            var price = binding.EDproductPrcie.text.toString().toDouble()

            var imageUri = arrayListOf(path, path1, path2, path3, path4)
            var file = arrayListOf<File>()
            var requatbody = arrayListOf<RequestBody>()
            var imagedata = arrayListOf <MultipartBody.Part>()

            for (i in 0 until imageUri.size) {
                if (imageUri[i] != null)
                   file.add(File(imageUri[i]))
//                   requatbody.add(file[i].asRequestBody("multipart/form-data".toMediaTypeOrNull()))
//                   imagedata.add(MultipartBody.Part.createFormData("image", file[i].name, requatbody[i]))
            }

//            for (i in 0 until imageUri.size) {
//                if (imageUri[i] != null)
//                    file.add(File(imageUri[i]))
//            }


            for (i in 0 until file.size) {
                requatbody.add(file[i].asRequestBody("multipart/form-data".toMediaTypeOrNull()))
            }

//

            for (i in 0 until requatbody.size) {

                imagedata.add(MultipartBody.Part.createFormData("image", file[i].name, requatbody[i]))
            }

            updateProduct(name, price,imagedata)
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

    private fun updateProduct(name: String, price: Double, imagedata: ArrayList<MultipartBody.Part>) {
        RetrofitClient.getService().updateProduct(id, ctoken, name, price,imagedata).enqueue(
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

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri1 = data?.data


                // Log.i("imagepath","$imgUri1")
                // val file = File(RealPathUtils.getRealPathFromURI_API19(this, imgUri1))


                binding.imageprofile1.setImageURI(imgUri1)


                //bitmap1 = MediaStore.Images.Media.getBitmap(contentResolver,imgUri1)
                path = RealPathUtil.getRealPath(this@EditDetailsActivity, imgUri1)
                Log.i("TAG", "$path")
                binding.LayoutRl1img.isVisible = true
                binding.LyoutRl1.isVisible = false
            }
        }

    private val changeImage2 =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri2 = data?.data

                binding.imageprofile2.setImageURI(imgUri2)


                path1 = RealPathUtil.getRealPath(this@EditDetailsActivity, imgUri2)
                binding.LayoutRl2img.isVisible = true
                binding.LyoutRl2.isVisible = false
            }
        }

    private val changeImage3 =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri3 = data?.data

                binding.imageprofile3.setImageURI(imgUri3)

                path2 = RealPathUtil.getRealPath(this@EditDetailsActivity, imgUri3)
                Log.i("TAG", "$path2")
                binding.LayoutRl3img.isVisible = true
                binding.LyoutRl3.isVisible = false
            }
        }

    private val changeImage4 =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri4 = data?.data

                binding.imageprofile4.setImageURI(imgUri4)
                path3 = RealPathUtil.getRealPath(this@EditDetailsActivity, imgUri4)
                Log.i("TAG", "$path3")
                binding.LayoutRl4img.isVisible = true
                binding.LyoutRl4.isVisible = false
            }
        }

    private val changeImage5 =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri5 = data?.data

                binding.imageprofile5.setImageURI(imgUri5)

                path4 = RealPathUtil.getRealPath(this@EditDetailsActivity, imgUri5)
                Log.i("TAG", "$path4")
                binding.LayoutRl5img.isVisible = true
                binding.LyoutRl5.isVisible = false
            }
        }

}