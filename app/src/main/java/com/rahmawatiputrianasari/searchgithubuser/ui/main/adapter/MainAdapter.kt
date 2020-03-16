package com.rahmawatiputrianasari.searchgithubuser.ui.main.adapter

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmawatiputrianasari.searchgithubuser.R
import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke
import kotlinx.android.synthetic.main.activity_detail.view.*

class MainAdapter(private val jokes: List<Joke>, private val listener: JokeListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_joke, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = jokes[position].desc
        holder.site.text = jokes[position].site
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.desc.text =
                Html.fromHtml(jokes[position].elementPureHtml, Html.FROM_HTML_MODE_LEGACY)
        } else {
            holder.desc.text = (Html.fromHtml(jokes[position].elementPureHtml))
        }

        holder.itemView.setOnClickListener { listener.onItemClick(jokes[position]) }
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.name!!
        val desc = itemView.desc!!
        val site = itemView.site!!
    }

    interface JokeListener {
        fun onItemClick(joke: Joke)
    }
}
