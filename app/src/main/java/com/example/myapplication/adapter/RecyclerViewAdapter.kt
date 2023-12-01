package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.RecycleviewItemRowBinding
import com.example.myapplication.model.App

class RecyclerViewAdapter() :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var appList = ArrayList<App>()
    var filteredList = ArrayList<App>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedData(appList: ArrayList<App>) {
        this.appList = appList
        this.filteredList = appList
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: RecycleviewItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: App) {
            binding.textView.text = data.app_name;
            LoadImagesIntoTheImagesView(binding.root.context,data.app_icon,binding.imageView)
        }



        // to load image into imageview using glide url
        fun LoadImagesIntoTheImagesView(mContext: Context, glideUrl: String?, imageView: ImageView?) {
            try {
                (mContext as Activity).runOnUiThread {
                    Glide.with(mContext)
                        .load(glideUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.baseline_downloading_24)
                        .error(R.drawable.baseline_error_24)
                        .into(imageView!!)

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        val binding = RecycleviewItemRowBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(filteredList[position])

    }


    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun filter(query: String) {
        filteredList = appList .filter { it.app_name.startsWith(query, ignoreCase = true) } as ArrayList<App>
        notifyDataSetChanged()
    }

}