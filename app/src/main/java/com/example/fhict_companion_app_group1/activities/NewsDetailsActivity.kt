package com.example.fhict_companion_app_group1.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.example.fhict_companion_app_group1.NEWS_DETAIL_INTENT_KEY
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.models.News
import com.google.gson.Gson

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        news = Gson().fromJson(intent.getStringExtra(NEWS_DETAIL_INTENT_KEY), News::class.java)
        val newsDescription = news.content

        findViewById<TextView>(R.id.textViewDetailNewsDescription).text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              Html.fromHtml(newsDescription, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(newsDescription)
        }

        findViewById<TextView>(R.id.textViewDetailNewsTitle).text = news.title
        findViewById<TextView>(R.id.textViewDetailNewsAuthor).text = news.author
        findViewById<TextView>(R.id.textViewDetailNewsPublishedDate).text = news.getPublishedDate()
        findViewById<ImageView>(R.id.imageViewDetailNews).setImageBitmap(news.getBitmap())
    }
}