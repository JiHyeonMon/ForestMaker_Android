package com.example.forestmaker.ui.reserve.Planting

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forestmaker.R
import com.example.forestmaker.data.SelectTreeData

class SelectTreeAdapter(private val context: Context, private val onClickListener: SelectTreeViewHolder.OnClickListener): RecyclerView.Adapter<SelectTreeViewHolder>(){

    var data = ArrayList<SelectTreeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTreeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tree_item, parent, false)
        return SelectTreeViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: SelectTreeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

@SuppressLint("Range")
class SelectTreeViewHolder(itemview: View, onClickListener: OnClickListener) : RecyclerView.ViewHolder(itemview) {

    val image = itemview.findViewById<ImageView>(R.id.item_tree_img)
    val name = itemview.findViewById<TextView>(R.id.item_tree_name)
    val price = itemview.findViewById<TextView>(R.id.item_tree_price)
    val check = itemview.findViewById<ImageView>(R.id.item_tree_checked)

    fun bind(selectTreeData: SelectTreeData) {

        Glide.with(itemView).load(selectTreeData.image).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(13)
            )).into(image)

        name.text = selectTreeData.name
        price.text = selectTreeData.price
    }

    init {
        itemview.setOnClickListener {
            onClickListener.onClickTree(adapterPosition)
//            image.setColorFilter(Color.parseColor("#999999"), PorterDuff.Mode.MULTIPLY)

            if (image.alpha == 0.3F) {
                image.alpha = 1F
                check.visibility = View.GONE
            } else {
                image.alpha = 0.3F
                check.visibility = View.VISIBLE
            }

        }
    }

    interface OnClickListener {
        fun onClickTree(position: Int)
    }
}