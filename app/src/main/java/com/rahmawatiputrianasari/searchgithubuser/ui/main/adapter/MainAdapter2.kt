package com.rahmawatiputrianasari.searchgithubuser.ui.main.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.rahmawatiputrianasari.searchgithubuser.R
import com.rahmawatiputrianasari.searchgithubuser.app.model.Item
import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke
import kotlinx.android.synthetic.main.item.view.*
import java.net.URL


class MainAdapter2(private val items: List<Item>, private val listener: JokeListener) :
    RecyclerView.Adapter<MainAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.userName.text = items[position].name
        DownLoadImageTask(holder.userImage).execute(items[position].avatarUrl)
//        holder.userImage.text = items[position].site
//        Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            holder.desc.text = Html.fromHtml(items[position].elementPureHtml, Html.FROM_HTML_MODE_LEGACY)
//        } else {
//            holder.desc.text = (Html.fromHtml(items[position].elementPureHtml))
//        }

//        holder.itemView.setOnClickListener { listener.onItemClick(items[position]) }
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
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */logo = BitmapFactory.decodeStream(`is`)
            } catch (e: Exception) { // Catch the download exception
                e.printStackTrace()
            }
            return logo
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.userName!!
        val userImage = itemView.userImage!!
    }

    interface JokeListener {
        fun onItemClick(joke: Joke)
    }
}
