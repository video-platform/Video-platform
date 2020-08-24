package study.junghoon.video.clientapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "http://www.naver.com"

    private lateinit var retrofitImpl: RetrofitApi

    init {
        buildRetrofit()
    }

    fun getInstance(): RetrofitApi {
        return retrofitImpl
    }

    private fun buildRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitImpl = retrofit.create(RetrofitApi::class.java)
    }

}