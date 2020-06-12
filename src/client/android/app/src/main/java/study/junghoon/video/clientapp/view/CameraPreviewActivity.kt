package study.junghoon.video.clientapp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageButton
import android.widget.VideoView
import androidx.camera.core.VideoCapture
import androidx.lifecycle.LifecycleOwner
import study.junghoon.video.clientapp.R
import java.io.File

@SuppressLint("RestrictedApi, ClickableViewAccessibility")
class CameraPreviewActivity : AppCompatActivity(), LifecycleOwner {
    companion object {
        const val CAMERA_RECORD_VIDEO = 100
    }

    private lateinit var viewFinder: VideoView
    private lateinit var captureButton: ImageButton
    private lateinit var videoCapture: VideoCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_preview)

        viewFinder = findViewById(R.id.view_finder)
        captureButton = findViewById(R.id.capture_button)


        val file = File(externalMediaDirs.first(),
            "${System.currentTimeMillis()}.mp4")

        captureButton.setOnClickListener {
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
            Log.i("video Uri-> ",""+videoUri.toString())
            viewFinder.setVideoURI(videoUri)
        }
    }



}