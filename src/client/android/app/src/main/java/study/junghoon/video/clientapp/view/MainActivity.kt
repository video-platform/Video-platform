package study.junghoon.video.clientapp.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import study.junghoon.video.clientapp.R
import study.junghoon.video.clientapp.model.ServerResponse
import study.junghoon.video.clientapp.network.RetrofitApi
import study.junghoon.video.clientapp.network.RetrofitBuilder

class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    companion object {
        const val PERMISSION_REQUEST_CAMERA = 100
        const val PERMISSION_REQUEST_WRITE_STORAGE = 200
        const val PERMISSION_REQUEST_READ_STORAGE = 300
    }

    private lateinit var retrofitApi: RetrofitApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        login_btn_main.setOnClickListener {
            retrofitApi.loginRequest().enqueue(object : Callback<ServerResponse> {
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

        camera_btn_main.setOnClickListener {
            showCameraPreView()
        }
    }

    private fun showCameraPreView() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //권한 허용되어있음
            startCamera()
        } else {
            requestCameraPermission()
        }
    }

    private fun startCamera() {
        val intent = Intent(this, CameraPreviewActivity::class.java)
        startActivity(intent)
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Snackbar.make(main_layout, "권한필요", Snackbar.LENGTH_INDEFINITE)
                .setAction("확인") {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.CAMERA),
                        PERMISSION_REQUEST_CAMERA
                    )
                }.show()
        } else {
            Snackbar.make(main_layout, "권한이 없으면 사용할 수 없습니다.", Snackbar.LENGTH_SHORT).show()

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CAMERA
            )
        }
    }

    private fun init() {
        retrofitApi = RetrofitBuilder.getInstance()
    }
}
