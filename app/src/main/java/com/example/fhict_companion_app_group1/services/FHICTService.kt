package com.example.fhict_companion_app_group1.services

import android.graphics.BitmapFactory
import com.example.fhict_companion_app_group1.models.Course
import com.example.fhict_companion_app_group1.models.News
import com.example.fhict_companion_app_group1.models.People
import com.example.fhict_companion_app_group1.models.Token
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
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

        try {
            val inputStream = httpsURLConnection.getInputStream()
            val scanner = Scanner(inputStream)
            val jsonString = scanner.useDelimiter("\\Z").next()
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("data")

            for (i in 0 until jsonArray.length()) {
                val innerJsonObject: String = jsonArray.get(i).toString()
                courses.add(Gson().fromJson(innerJsonObject, Course::class.java))
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return getMockCourses()
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

        for (i in 0 until jsonArray.length()) {
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

    // Mockup data for courses if service is down or not found
    private fun getMockCourses(): ArrayList<Course> {
        val courses = ArrayList<Course>()
        courses.add(
            Course(
                "ANDR1",
                "2021-09-19T14:30:16.450Z",
                "2021-09-19T16:00:16.450Z",
                "r10_2.41_t",
                "BOOP02",
            )
        )
        courses.add(
            Course(
                "TCI",
                "2021-09-19T14:30:16.450Z",
                "2021-09-19T16:00:16.450Z",
                "r10_2.41_t",
                "BOOP02",
            )
        )
        courses.add(
            Course(
                "SOT",
                "2021-09-19T14:30:16.450Z",
                "2021-09-19T16:00:16.450Z",
                "r10_2.41_t",
                "BOOP02",
            )
        )
        courses.add(
            Course(
                "PROEP",
                "2021-09-19T14:30:16.450Z",
                "2021-09-19T16:00:16.450Z",
                "r10_2.41_t",
                "BOOP02",
            )
        )
        courses.add(
            Course(
                "WEBPRO",
                "2021-09-19T14:30:16.450Z",
                "2021-09-19T16:00:16.450Z",
                "r10_2.41_t",
                "BOOP02",
            )
        )
        return courses
    }
}