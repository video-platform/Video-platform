package study.junghoon.video.clientapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.selects.select
import study.junghoon.video.clientapp.R
import study.junghoon.video.clientapp.util.MediaStoreVideo
import study.junghoon.video.clientapp.util.TimeConverter

class GalleryAdapter :
    ListAdapter<MediaStoreVideo, ImageViewHolder>(MediaStoreVideo.DiffCallback) {
    private var selectedItemPos = -1
    private var lastItemSelectedPos = -1

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
        Log.i("video ", " " + mediaStoreImage.contentUri)

        if (position == selectedItemPos) {
            holder.galleryMainLayout.setBackgroundResource(R.color.gallery_selected_color)
        } else {
            holder.galleryMainLayout.setBackgroundResource(R.color.color_white)
        }

        Glide.with(holder.imageView)
            .load(mediaStoreImage.contentUri)
            .thumbnail(0.33f)
            .centerCrop()
            .into(holder.imageView)

        holder.videoDuration.text = TimeConverter.millisecondToTime(mediaStoreImage.videoDuration)

        holder.imageView.setOnClickListener {
            selectedItemPos = position
            if (lastItemSelectedPos == -1)
                lastItemSelectedPos = selectedItemPos
            else {
                notifyItemChanged(lastItemSelectedPos)
                lastItemSelectedPos = selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
        }
    }
}

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.image)
    val videoDuration: TextView = view.findViewById(R.id.video_duration_txtView)
    val galleryMainLayout: LinearLayout = view.findViewById(R.id.gallery_main_layout)
}