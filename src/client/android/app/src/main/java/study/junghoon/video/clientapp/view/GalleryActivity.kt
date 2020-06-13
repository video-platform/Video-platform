package study.junghoon.video.clientapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import study.junghoon.video.clientapp.R
import study.junghoon.video.clientapp.adapter.GalleryAdapter
import study.junghoon.video.clientapp.util.MediaStoreVideo
import java.text.SimpleDateFormat

class GalleryActivity : AppCompatActivity() {

    private val videos = MutableLiveData<List<MediaStoreVideo>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val galleryAdapter = GalleryAdapter()
        gallery_recyclerView.also { view ->
            view.layoutManager = GridLayoutManager(this, 3)
            view.adapter = galleryAdapter
        }

        videos.observe(this, Observer { videos ->
            galleryAdapter.submitList(videos)
        })

        showImages()
    }

    private fun showImages() {
        GlobalScope.launch {
            val videoList = queryImages()
            videos.postValue(videoList)
        }
    }

    private fun queryImages(): List<MediaStoreVideo> {
        val videos = mutableListOf<MediaStoreVideo>()

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            null
//            MediaStore.Video.Media.DATE_TAKEN
        )
//        val selection = "${MediaStore.Video.Media.DATE_TAKEN} >= ?"
        val selectionArgs = arrayOf(
            dateToTimestamp(day = 1, month = 1, year = 1970).toString()
        )

//        val sortOrder = "${MediaStore.Video.Media.DATE_TAKEN} DESC"
        val cursor = contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null, // selection
            null, //selectionArgs
//            sortOrder
        null
        )
        cursor?.use {
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
//            val dateTakenColumn =
//                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)
            val displayNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
//                val dateTaken = Date(cursor.getLong(dateTakenColumn))
                val displayName = cursor.getString(displayNameColumn)
                val contentUri = Uri.withAppendedPath(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id.toString()
                )
                val video = MediaStoreVideo(id, displayName, null, contentUri)
                videos += video
                Log.e(
                    "video ",
                    "id: $id, display_name: $displayName, date_taken: content_uri: $contentUri"
                )
            }
        }
        return videos
    }

    @SuppressLint("SimpleDateFormat")
    private fun dateToTimestamp(day: Int, month: Int, year: Int): Long =
        SimpleDateFormat("dd.MM.yyyy").let { formatter ->
            formatter.parse("$day.$month.$year")?.time ?: 0
        }
}