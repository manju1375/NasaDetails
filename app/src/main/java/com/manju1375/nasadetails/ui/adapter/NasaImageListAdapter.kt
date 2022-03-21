package com.manju1375.nasadetails.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.manju1375.nasadetails.R
import com.manju1375.nasadetails.model.NasaItemResponse
import javax.inject.Inject


class NasaImageListAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList = mutableListOf<NasaItemResponse>()

    private val requestOptions: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.drawable.loading_spinner)
        .error(R.drawable.ic_launcher_foreground)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    fun setDetails(movies: List<NasaItemResponse>) {
        this.dataList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageItemViewHolder(view) //object of ImagePostViewHolder will return

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val postData = dataList[position]
        val context = holder.itemView.context

        val viewHolder = holder as ImageItemViewHolder
        Glide.with(holder.itemView.context).load(postData.url).apply(requestOptions).into(viewHolder.imageView)
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(context, "Image type post", Toast.LENGTH_SHORT).show()
        }
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

}