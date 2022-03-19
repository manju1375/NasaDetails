package com.manju1375.nasadetails.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.manju1375.nasadetails.model.NasaItemResponse
import javax.inject.Inject
import com.bumptech.glide.load.engine.DiskCacheStrategy


import com.bumptech.glide.request.RequestOptions
import com.manju1375.nasadetails.R


class NasaImageListAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val POST_TYPE_TEXT = 1
    private val POST_TYPE_IMAGE = 2

    var dataList = mutableListOf<NasaItemResponse>()

    val requestOptions: RequestOptions = RequestOptions()
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
        val view: View
        return if (viewType == POST_TYPE_TEXT) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
            TextItemViewHolder(view) //object of TextPostViewHolder will return
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            ImageItemViewHolder(view) //object of ImagePostViewHolder will return
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val postData = dataList[position]
        val context = holder.itemView.context

        if (holder.itemViewType == POST_TYPE_TEXT) {
            val viewHolder = holder as TextItemViewHolder

            viewHolder.profileName.text = postData.title
            viewHolder.postDescription.text = postData.explanation

            viewHolder.itemView.setOnClickListener {
                Toast.makeText(context, "Text type post", Toast.LENGTH_SHORT).show()
            }

        } else {
            val viewHolder = holder as ImageItemViewHolder

            viewHolder.profileName.text = postData.title
            viewHolder.postDescription.text = postData.explanation

            Glide.with(holder.itemView.context).load(postData.url).apply(requestOptions).into(viewHolder.imageView)

            viewHolder.itemView.setOnClickListener {
                Toast.makeText(context, "Image type post", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].url?.isEmpty() == true) POST_TYPE_TEXT else POST_TYPE_IMAGE
    }
}