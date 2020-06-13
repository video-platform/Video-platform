package study.junghoon.video.clientapp.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_upload -> {
            if (mPermissionChecker.getPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                openGallery()
            } else {
                mPermissionChecker.requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 300)
            }
            true
        }

        R.id.action_camera -> {
            if (mPermissionChecker.getPermissionState(Manifest.permission.CAMERA)) {
                startCamera()
            } else {
                mPermissionChecker.requestPermission(Manifest.permission.CAMERA, 100)
            }
            true
        }

        R.id.action_login -> {
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
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
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
        setSupportActionBar(my_toolbar)
        my_toolbar.title = getString(R.string.toolbarTitle)
        my_toolbar.setTitleTextColor(resources.getColor(R.color.color_white))
        mPermissionChecker = PermissionChecker(this, main_layout)
        retrofitApi = RetrofitBuilder.getInstance()
    }
}
