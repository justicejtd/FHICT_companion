package com.example.fhict_companion_app_group1.services

import android.graphics.BitmapFactory
import com.example.fhict_companion_app_group1.models.Course
import com.example.fhict_companion_app_group1.models.News
import com.example.fhict_companion_app_group1.models.People
import com.example.fhict_companion_app_group1.models.Token
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class FHICTService(private val token: Token) {
    private val baseUrl = "https://api.fhict.nl"

    fun getCourses(): ArrayList<Course> {
        val courses = ArrayList<Course>()
        val endpoint = URL(baseUrl.plus("/schedule/me"))
        val httpsURLConnection = endpoint.openConnection()

        httpsURLConnection.setRequestProperty("Accept", "application/json")
        httpsURLConnection.setRequestProperty(
            "Authorization",
            "Bearer ".plus(token.getTokenId())
        )
        httpsURLConnection.connect()

        val inputStream = httpsURLConnection.getInputStream()
        val scanner = Scanner(inputStream)
        val jsonString = scanner.useDelimiter("\\Z").next()
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("data")

        for (i in 0 until jsonArray.length()) {
            val innerJsonObject: String = jsonArray.get(i).toString()
            courses.add(Gson().fromJson(innerJsonObject, Course::class.java))
        }
        return courses
    }

    fun getNews(): ArrayList<News> {
        val news = ArrayList<News>()
        val endpoint = URL(baseUrl.plus("/newsfeeds/BronNieuws"))
        val httpsURLConnection = endpoint.openConnection()

        httpsURLConnection.setRequestProperty("Accept", "application/json")
        httpsURLConnection.setRequestProperty(
            "Authorization",
            "Bearer ".plus(token.getTokenId())
        )
        httpsURLConnection.connect()

        val inputStream = httpsURLConnection.getInputStream()
        val scanner = Scanner(inputStream)
        val jsonString = scanner.useDelimiter("\\Z").next()
        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("items")

        for (i in 0 until jsonArray.length()) {
            val innerJsonObject: String = jsonArray.get(i).toString()
            val course = Gson().fromJson(innerJsonObject, News::class.java)
            val imageInputStream = URL(course.image).openStream()
            val bitmap = BitmapFactory.decodeStream(imageInputStream)

            course.setBitmap(bitmap)
            news.add(course)
        }
        return news
    }

    fun getTeachers(): ArrayList<People> {
        val people = ArrayList<People>()
        val endpoint = URL(baseUrl.plus("/people"))
        val httpsURLConnection = endpoint.openConnection()

        httpsURLConnection.setRequestProperty("Accept", "application/json")
        httpsURLConnection.setRequestProperty(
            "Authorization",
            "Bearer ".plus(token.getTokenId())
        )
        httpsURLConnection.connect()

        val inputStream = httpsURLConnection.getInputStream()
        val scanner = Scanner(inputStream)
        val jsonString = scanner.useDelimiter("\\Z").next()

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until 10) {
            val innerJsonObject: String = jsonArray.get(i).toString()
            val info = Gson().fromJson(innerJsonObject, People::class.java)
            people.add(info)
        }
        return people
    }

    fun getMyInfo(): People {
        val endpoint = URL(baseUrl.plus("/people/me"))
        val httpsURLConnection = endpoint.openConnection()

        httpsURLConnection.setRequestProperty("Accept", "application/json")
        httpsURLConnection.setRequestProperty(
            "Authorization",
            "Bearer ".plus(token.getTokenId())
        )
        httpsURLConnection.connect()

        val inputStream = httpsURLConnection.getInputStream()
        val scanner = Scanner(inputStream)
        val jsonString = scanner.useDelimiter("\\Z").next()
        val jsonObject = JSONObject(jsonString)
        val jsonObjectToString: String = jsonObject.toString()

        return Gson().fromJson(jsonObjectToString, People::class.java)
    }

}