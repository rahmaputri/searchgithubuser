package com.rahmawatiputrianasari.searchgithubuser.ui.main.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.rahmawatiputrianasari.searchgithubuser.R
import com.rahmawatiputrianasari.searchgithubuser.app.model.Item
import kotlinx.android.synthetic.main.item.view.*
import java.net.URL


class MainAdapter(private var items: ArrayList<Item>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val ITEM = 0
    private val LOADING = 1
    private val context: Context? = null

    private var isLoadingAdded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)

        return ViewHolder(view)
    }


    fun getItems(): ArrayList<Item> {
        return items
    }

    fun setItems(item: ArrayList<Item>) {
        items = item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM -> {
                holder.userName.text = items[position].name
                DownLoadImageTask(holder.userImage).execute(items[position].avatarUrl)
            }
            LOADING -> {
            }
        }

    }

    private class DownLoadImageTask(imageView: ImageView) :
        AsyncTask<String?, Void?, Bitmap?>() {
        var imageView: ImageView

        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }

        init {
            this.imageView = imageView
        }

        override fun doInBackground(vararg params: String?): Bitmap? {
            val urlOfImage = params[0]
            var logo: Bitmap? = null
            try {
                val `is` = URL(urlOfImage).openStream()
                logo = BitmapFactory.decodeStream(`is`)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return logo
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    fun add(mc: Item?) {
        items.add(mc!!)
        val handler = Handler()

        val r = Runnable { notifyItemInserted(items.size - 1) }

        handler.post(r)

    }

    fun addAll(mcList: List<Item?>) {
        for (mc in mcList) {
            add(mc)
        }
    }

    fun remove(item: Item?) {
        val position: Int = items.indexOf(item)
        if (position > -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }


    fun addLoadingFooter() {
        isLoadingAdded = true
        add(Item())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position: Int = items.size - 1
        val item = getItem(position)
        if (item != null) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItem(position: Int): Item? {
        return items.get(position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.userName!!
        val userImage = itemView.userImage!!
    }

}
