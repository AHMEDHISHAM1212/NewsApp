package com.example.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.api.newsResponse.NewsItem
import com.example.newsapp.databinding.ItemNewsLayoutBinding

class NewsAdapter(private var newsList: List<NewsItem?>? = null)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemNewsLayoutBinding.inflate(
            LayoutInflater.from(parent.context) ,
            parent ,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList!![position]
        holder.bind(news)

        if (onRootClickListener!=null) {
            holder.itemViewBinding.root
                .setOnClickListener{
                    onRootClickListener!!.onItemClick(position,news)
                }
        }
    }

    override fun getItemCount(): Int = newsList?.size?:0

    fun bindViews(articles: List<NewsItem?>?) {
        this.newsList= articles
        notifyDataSetChanged()
    }
     var onRootClickListener:OnItemClickListener?= null

    fun interface OnItemClickListener  {
        fun onItemClick(position: Int, news: NewsItem?)
    }

    class ViewHolder (val itemViewBinding: ItemNewsLayoutBinding) : RecyclerView.ViewHolder(itemViewBinding.root){
        fun bind(news: NewsItem?){
            itemViewBinding.news = news
            itemViewBinding.executePendingBindings()
        }
    }

}