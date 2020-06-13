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
import study.junghoon.video.clientapp.util.PermissionChecker

class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var retrofitApi: RetrofitApi
    private lateinit var mPermissionChecker: PermissionChecker

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
            if (mPermissionChecker.getPermissionState(Manifest.permission.CAMERA)) {
                startCamera()
            } else {
                mPermissionChecker.requestPermission(Manifest.permission.CAMERA, 100)
            }
        }

        gallery_btn_main.setOnClickListener {
            if (mPermissionChecker.getPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                openGallery()
            } else {
                mPermissionChecker.requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 300)
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
    }

    private fun startCamera() {
        val intent = Intent(this, CameraPreviewActivity::class.java)
        startActivity(intent)
    }

    private fun init() {
        mPermissionChecker = PermissionChecker(this, main_layout)
        retrofitApi = RetrofitBuilder.getInstance()
    }
}
