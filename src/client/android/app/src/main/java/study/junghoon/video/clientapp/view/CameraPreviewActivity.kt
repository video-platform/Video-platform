package study.junghoon.video.clientapp.view

import android.graphics.Camera
import android.hardware.Camera.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import study.junghoon.video.clientapp.R
import java.lang.Exception

class CameraPreviewActivity : AppCompatActivity() {
    companion object {
        const val CAMERA_ID = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_preview)
    }
}