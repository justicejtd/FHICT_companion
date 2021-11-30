package com.example.fhict_companion_app_group1.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.fhict_companion_app_group1.NEWS_DETAIL_INTENT_KEY
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.activities.MainActivity
import com.example.fhict_companion_app_group1.activities.NewsDetailsActivity
import com.example.fhict_companion_app_group1.adapters.NewsAdapter
import com.example.fhict_companion_app_group1.models.News
import com.example.fhict_companion_app_group1.services.FHICTService
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NewsFragment : Fragment() {
    private lateinit var fhictService: FHICTService
    private lateinit var newsFragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fhictService = (activity as MainActivity).getFhictService()
        // Inflate the layout for this fragment
        newsFragmentView  = inflater.inflate(R.layout.fragment_news, container, false)
        setNews()
        return newsFragmentView
    }

    private fun setNews() {
        doAsync {
            val news = fhictService.getNews()
            uiThread {
                val newsAdapter = NewsAdapter(context, news)
                val listViewsNewsFeed: ListView = newsFragmentView.findViewById(R.id.listViewNewsFeed)

                listViewsNewsFeed.adapter = newsAdapter
                listViewsNewsFeed.setOnItemClickListener { _, _, position, _ ->
                    val intent = Intent(context, NewsDetailsActivity::class.java)
                    val jsonString = Gson().toJson(news[position], News::class.java)

                    intent.putExtra(NEWS_DETAIL_INTENT_KEY, jsonString)
                    startActivity(intent)
                }
            }
        }
    }
}