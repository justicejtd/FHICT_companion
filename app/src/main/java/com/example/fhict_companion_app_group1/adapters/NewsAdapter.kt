package com.example.fhict_companion_app_group1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.fhict_companion_app_group1.models.News
import com.example.fhict_companion_app_group1.R


class NewsAdapter(context: Context?, private val news: ArrayList<News>) : BaseAdapter() {
    private val layoutInflater =
        context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return news.size
    }

    override fun getItem(position: Int): Any {
        return news[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View =
            convertView ?: layoutInflater.inflate(R.layout.list_item_news, parent, false)

        rowView.findViewById<ImageView>(R.id.imageViewNews)
            .setImageBitmap(news[position].getBitmap())
        rowView.findViewById<TextView>(R.id.textViewNewsRowPosition).text =
            position.toString().plus(".")
        rowView.findViewById<TextView>(R.id.textViewNewsAuthor).text = news[position].author
        rowView.findViewById<TextView>(R.id.textViewNewsTitle).text = news[position].title
        return rowView
    }
}
