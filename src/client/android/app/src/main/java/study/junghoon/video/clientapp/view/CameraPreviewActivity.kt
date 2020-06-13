package study.junghoon.video.clientapp.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.synthetic.main.activity_camera_preview.*
import study.junghoon.video.clientapp.R

class CameraPreviewActivity : AppCompatActivity(), LifecycleOwner {
    companion object {
        const val CAMERA_RECORD_VIDEO = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_preview)

        capture_button.setOnClickListener {
            dispatchTakeVideoIntent()
        }
    }

    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also {takeVideoIntent ->
            takeVideoIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takeVideoIntent, CAMERA_RECORD_VIDEO)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == CAMERA_RECORD_VIDEO && resultCode == Activity.RESULT_OK) {
            val videoUri: Uri? = intent.data
            view_finder.setVideoURI(videoUri)
        }
    }
}