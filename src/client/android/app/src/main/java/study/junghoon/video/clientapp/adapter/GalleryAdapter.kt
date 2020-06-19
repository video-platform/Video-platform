package study.junghoon.video.clientapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import study.junghoon.video.clientapp.R
import study.junghoon.video.clientapp.util.MediaStoreVideo
import study.junghoon.video.clientapp.util.TimeConverter

class GalleryAdapter :
    ListAdapter<MediaStoreVideo, ImageViewHolder>(MediaStoreVideo.DiffCallback) {

    interface VideoClickListener {
        fun selectedVideo(video: MediaStoreVideo)
    }

    fun setVideoClickListener(videoClickListener: VideoClickListener) {
        this.mVideoClickListener = videoClickListener
    }

    private lateinit var mVideoClickListener: VideoClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.gallery_item_layout, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val mediaStoreImage = getItem(position)
        Log.i("video "," "+ mediaStoreImage.contentUri)

        Glide.with(holder.imageView)
            .load(mediaStoreImage.contentUri)
            .thumbnail(0.33f)
            .centerCrop()
            .into(holder.imageView)

        holder.videoDuration.text = TimeConverter.millisecondToTime(mediaStoreImage.videoDuration)

        holder.imageView.setOnClickListener {
            mVideoClickListener.selectedVideo(mediaStoreImage)
        }
    }

}

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.image)
    val videoDuration: TextView = view.findViewById(R.id.video_duration_txtView)
}