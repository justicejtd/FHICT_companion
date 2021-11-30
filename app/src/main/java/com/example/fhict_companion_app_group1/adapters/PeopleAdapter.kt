package com.example.fhict_companion_app_group1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.models.People
import de.hdodenhof.circleimageview.CircleImageView

//class PeopleAdapter  ( context:Context,var resources:Int, var items:List<People>):ArrayAdapter<People>(context, resources, items)
//{
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
//        val view:View = layoutInflater.inflate(resources, null)
//
//        val imageView:ImageView = view.findViewById(R.id.image)
//        val nameTextView:TextView = view.findViewById(R.id.people_name)
//        val emailTextView:TextView = view.findViewById(R.id.people_email)
//        val phoneTextView:TextView = view.findViewById(R.id.people_telephone)
//
//        var mItem: People = items[position]
//        imageView.setImageDrawable(context.resources.getDrawable(mItem.image))
//
//        nameTextView.text = mItem.displayName
//        emailTextView.text = mItem.mail
//        phoneTextView.text = mItem.telephone.toString()
//
//        return view
//    }
//}
class PeopleAdapter (context: Context?, private val people:ArrayList<People>):BaseAdapter()
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
        view.findViewById<TextView>(R.id.people_email).text = "Email: " + people[position].mail
        view.findViewById<TextView>(R.id.people_office).text = people[position].office

        return view
    }

}