package study.junghoon.video.clientapp.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import study.junghoon.video.clientapp.R

class PermissionChecker(activity: Activity, view: FrameLayout) {

    companion object {
        const val PERMISSION_REQUEST_CAMERA = 100
        const val PERMISSION_REQUEST_WRITE_STORAGE = 200
        const val PERMISSION_REQUEST_READ_STORAGE = 300
    }

    private var mActivity = activity
    private var mLayoutMain = view

    fun getPermissionState(permissionKind: String): Boolean {
        if (ActivityCompat.checkSelfPermission(
                mActivity.applicationContext,
                permissionKind
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    fun requestPermission(requirePermission: String, requestCode: Int) {
        val mRequestCode = when (requestCode) {
            100 -> PERMISSION_REQUEST_CAMERA
            300 -> PERMISSION_REQUEST_READ_STORAGE
            else -> 0
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                mActivity,
                Manifest.permission.CAMERA
            )
        ) {
            Snackbar.make(mLayoutMain, mActivity.getString(R.string.requirePermissionMsg), Snackbar.LENGTH_INDEFINITE)
                .setAction(mActivity.getString(R.string.okMessage)) {
                    ActivityCompat.requestPermissions(
                        mActivity,
                        arrayOf(requirePermission),
                        mRequestCode
                    )
                }.show()
        } else {
            Snackbar.make(mLayoutMain, mActivity.getString(R.string.needPermissionMsg), Snackbar.LENGTH_SHORT).show()

            ActivityCompat.requestPermissions(
                mActivity, arrayOf(requirePermission),
                mRequestCode
            )
        }
    }
}