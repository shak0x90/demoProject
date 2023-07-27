package com.example.testapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.testapp.databinding.ActivityAdProductBinding
import com.example.testapp.mehedi.RetrofitClient
import com.example.testapp.mehedi.addproduct.AddProductResponseModel
import com.example.testapp.mehedi.utils.RealPathUtil
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

var ctoken = ""
 var userid = 0

class AdProduct : AppCompatActivity() {

    private lateinit var binding: ActivityAdProductBinding
    private lateinit var retrofitInstance: RetrofitInstance

    private var path: String? = ""
    private var path1: String? = ""
    private var path2: String? = ""
    private  var path3: String? = ""
    private  var path4: String? = ""

    private lateinit var file: File
    private lateinit var file1: File
    private lateinit var file2: File
    private lateinit var file3: File
    private lateinit var file4: File

    private lateinit var imagedata:List<MultipartBody.Part>

    private lateinit var image:List<Uri>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAdProductBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        retrofitInstance = RetrofitInstance()

        var token = intent.getStringExtra("token").toString()
         userid = intent.getStringExtra("id")?.toInt()!!
        Toast.makeText(this@AdProduct, "USer iD $userid", Toast.LENGTH_SHORT).show()

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

        binding.BtnSkip.setOnClickListener {
            val intent = Intent(this@AdProduct, ProductsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.BtnSave.setOnClickListener {
            val bearer = "Bearer "
            token = bearer + token
            ctoken = token
            Log.i("token", token)
            val productname = binding.EDproductname.text.toString().trim()
            val producPrice: Double = binding.EDproductPrcie.text.toString().toDouble()


            file = File(path)
            file1 = File(path1)
            file2 = File(path2)
            file3 = File(path3)
            file4 = File(path4)

            val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val requestFile1 = file1.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val requestFile2 = file2.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val requestFile3 = file3.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val requestFile4 = file4.asRequestBody("multipart/form-data".toMediaTypeOrNull())

            val body: MultipartBody.Part =
                MultipartBody.Part.createFormData("image", file.name, requestFile)
            val body1: MultipartBody.Part =
                MultipartBody.Part.createFormData("image", file1.name, requestFile1)
            val body2: MultipartBody.Part =
                MultipartBody.Part.createFormData("image", file2.name, requestFile2)
            val body3: MultipartBody.Part =
                MultipartBody.Part.createFormData("image", file3.name, requestFile3)
            val body4: MultipartBody.Part =
                MultipartBody.Part.createFormData("image", file4.name, requestFile4)


            imagedata = arrayListOf(body,body1,body2,body3,body4)


            //image = arrayListOf(imgUri1S!!,imgUri2S!!,imgUri3S!!,imgUri4S!!,imgUri5S!!)

            // Log.i("TAG", .toString())

            addProduct(imagedata, token, productname, producPrice)
//            val intent = Intent(this@AdProduct, ProductsActivity::class.java)
//            startActivity(intent)
        }
    }

    private fun addProduct(
        body: List<MultipartBody.Part>,
//        body1: MultipartBody.Part,
//        body2: MultipartBody.Part,
//        body3: MultipartBody.Part,
//        body4: MultipartBody.Part,
        token: String,
        productname: String,
        producPrice: Double
    ) {
        RetrofitClient.getService()
            .CreateProduct(token, productname, producPrice, body)
            .enqueue(
                object : Callback<AddProductResponseModel> {
                    override fun onResponse(
                        call: Call<AddProductResponseModel>,
                        response: Response<AddProductResponseModel>
                    ) {
                        Toast.makeText(this@AdProduct, "Sucessfull", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@AdProduct, ProductsActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<AddProductResponseModel>, t: Throwable) {
                        Toast.makeText(this@AdProduct, "Failed", Toast.LENGTH_SHORT).show()
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
                path = RealPathUtil.getRealPath(this@AdProduct, imgUri1)
                Log.i("TAG", "$path")
                binding.LayoutRl1img.isVisible = true
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


                path1 = RealPathUtil.getRealPath(this@AdProduct, imgUri2)
                binding.LayoutRl2img.isVisible = true
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

                path2 = RealPathUtil.getRealPath(this@AdProduct, imgUri3)
                Log.i("TAG", "$path2")
                binding.LayoutRl3img.isVisible = true
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
                path3 = RealPathUtil.getRealPath(this@AdProduct, imgUri4)
                Log.i("TAG", "$path3")
                binding.LayoutRl4img.isVisible = true
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

                path4 = RealPathUtil.getRealPath(this@AdProduct, imgUri5)
                Log.i("TAG", "$path4")
                binding.LayoutRl5img.isVisible = true
            }
        }

//    private fun createImageParts(imguri: Uri): MultipartBody.Part {
//        //val imageParts =MultipartBody.Part
//
//
//        val contentResolver = contentResolver
//        val file = File(getRealPathFromUri(contentResolver, imguri!!))
//        val mediaType = "image/*".toMediaTypeOrNull()
//        val requestFile = file.asRequestBody(mediaType)
//
//
//        return MultipartBody.Part.createFormData("images[]", file.name, requestFile)
//    }


//    private fun getRealPathFromUri(contentResolver: ContentResolver, uri: Uri): String {
//        var filePath = ""
//        val projection = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor = contentResolver.query(uri, projection, null, null, null)
//        cursor?.use {
//            if (it.moveToFirst()) {
//                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//                filePath = it.getString(columnIndex)
//            }
//        }
//        return filePath
//    }


}