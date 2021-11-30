package com.example.fhict_companion_app_group1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.models.People

class PeopleAdapter (val context: Context?, private val people:ArrayList<People>):BaseAdapter()
{
    private val layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return people.size
    }

    override fun getItem(position: Int): Any {
        return people[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: layoutInflater.inflate(R.layout.list_people_details, parent, false)

        view.findViewById<ImageView>(R.id.image).setImageBitmap(people[position].getBitmap())
        view.findViewById<TextView>(R.id.people_name).text = people[position].displayName
        view.findViewById<TextView>(R.id.people_email).text = context?.getString(R.string.email).plus(people[position].mail)
        view.findViewById<TextView>(R.id.people_office).text = people[position].office

        return view
    }

}