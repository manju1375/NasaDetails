package com.manju1375.nasadetails.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.manju1375.nasadetails.R
import com.manju1375.nasadetails.model.NasaItemResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

// Custom pager adapter not using fragments
@Module
@InstallIn(FragmentComponent::class)
class NasaDetailsPagerAdapter @Inject constructor(@ActivityContext context: Context) : PagerAdapter() {

    private lateinit var pages:List<NasaItemResponse>
    fun setNasaDetails(list: List<NasaItemResponse>){
        pages = list
    }
    var mLayoutInflater: LayoutInflater
    val requestOptions: RequestOptions

    override fun getCount() = pages.size

    // Returns true if a particular object (page) is from a particular page
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    // This method should create the page for the given position passed to it as an argument. 
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // Inflate the layout for the page
        val itemView: View = mLayoutInflater.inflate(R.layout.item_image, container, false)
        // Find and populate data into the page (i.e set the image)
        val imageView: ImageView = itemView.findViewById(R.id.imageView) as ImageView
        // ...
        // Add the page to the container
        container.addView(itemView)
        Glide.with(itemView.context).load(pages[position].url).apply(requestOptions).into(imageView)
        // Return the page
        return itemView
    }

    // Removes the page from the container for the given position.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    init {
        mLayoutInflater = LayoutInflater.from(context)
        requestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.loading_spinner)
            .error(R.drawable.ic_launcher_foreground)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
    }
}