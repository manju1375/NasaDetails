package com.manju1375.nasadetails.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_content.view.*

class TextItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val profileName = itemView.title
    val postDescription = itemView.postDescription
}