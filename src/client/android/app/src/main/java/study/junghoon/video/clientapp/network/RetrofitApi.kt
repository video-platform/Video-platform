package study.junghoon.video.clientapp.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import study.junghoon.video.clientapp.model.ServerResponse

interface RetrofitApi {
    @GET("login")
    fun loginRequest(): Call<ServerResponse>

    @Multipart
    @POST("uploadFile")
    fun uploadVideo(@Part file: MultipartBody.Part, @Part("desc") desc: RequestBody, @Field("ID") id: String): Call<ServerResponse>
}