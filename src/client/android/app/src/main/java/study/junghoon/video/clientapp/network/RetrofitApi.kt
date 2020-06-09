package study.junghoon.video.clientapp.network

import retrofit2.Call
import retrofit2.http.GET
import study.junghoon.video.clientapp.model.ServerResponse

interface RetrofitApi {
    @GET("login")
    fun loginRequest(): Call<ServerResponse>
}