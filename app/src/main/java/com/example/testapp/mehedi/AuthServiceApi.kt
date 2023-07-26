package com.example.testapp.mehedi

import com.example.testapp.mehedi.addproduct.AddProductResponseModel
import com.example.testapp.mehedi.getServiceList.GetServiceModel
import com.example.testapp.mehedi.getproductlist.GetProductResponse
import com.example.testapp.mehedi.otp.OtpRequsetModel
import com.example.testapp.mehedi.otp.OtpResponseModel
import com.example.testapp.mehedi.regitation.RegisterRequestModel
import com.example.testapp.mehedi.regitation.RegisterResponseModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface AuthServiceApi {
    @POST("/api/v1/auth/login")
    fun userLogin(@Body loginRequestModel: LoginRequestModel): Call<LoginResponseModel>

    @POST("/api/v1/auth/user-register")
    fun userRegister(@Body registerRequestModel: RegisterRequestModel): Call<RegisterResponseModel>

    @POST("/api/v1/auth/verify-otp")
    fun verifyOTP(@Body otpRequestModel: OtpRequsetModel): Call<OtpResponseModel>

    @GET("/api/v1/service/admin-provided-services")
    fun getServiceList(): Call<GetServiceModel>

    //@Headers( "Content-Type:multipart/form-data,boundary=<calculated when request is sent>")
    @Multipart
    @POST("/api/v1/product/add")
    fun CreateProduct(
        @Header("Authorization") token: String,
        @Part("name") name: String,
        @Part("price") price: Double,
        @Part image:List<MultipartBody.Part>,

//        @Part ("image")image1:MultipartBody.Part,
//        @Part ("image")image2:MultipartBody.Part,
//        @Part ("image")image3:MultipartBody.Part,
//        @Part ("image")image4:MultipartBody.Part,
    ): Call<AddProductResponseModel>

    @FormUrlEncoded
    @PATCH("/api/v1/product/{id}")
    fun updateProduct(
        @Path("id") id: Int,
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("price") price: Double
    ): Call<AddProductResponseModel>

    @DELETE("/api/v1/product/{id}")
    fun delteProduct(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<DeleteResponseClass>

    @GET("/api/v1/product/list/{id}")
    fun getProductList(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Call<GetProductResponse>

}
