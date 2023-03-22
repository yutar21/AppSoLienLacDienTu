package com.example.solienlacdientu.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.solienlacdientu.R

class TimeListAdapter(context: Context, data: List<Triple<String, String, String>>) : ArrayAdapter<Triple<String, String, String>>(context, R.layout.item_listview_time, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_listview_time, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(R.id.tv_tiet).text = item?.first
        view.findViewById<TextView>(R.id.tv_gio).text = item?.second
        view.findViewById<TextView>(R.id.tv_gioend).text = item?.third
        return view
    }
}
