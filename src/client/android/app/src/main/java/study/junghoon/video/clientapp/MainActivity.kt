package study.junghoon.video.clientapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import study.junghoon.video.clientapp.model.ServerResponse
import study.junghoon.video.clientapp.network.RetrofitApi
import study.junghoon.video.clientapp.network.RetrofitBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var retrofitApi: RetrofitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        login_btn_main.setOnClickListener {
            retrofitApi.loginRequest().enqueue(object: Callback<ServerResponse> {
                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<ServerResponse>,
                    response: Response<ServerResponse>
                ) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    private fun init() {
        retrofitApi = RetrofitBuilder.getInstance()
    }
}
