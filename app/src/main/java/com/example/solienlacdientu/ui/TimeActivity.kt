package com.example.solienlacdientu.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import com.example.solienlacdientu.Adapter.TimeListAdapter
import com.example.solienlacdientu.R

class TimeActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            onBackPressed()
        }
        val listView: ListView? = findViewById(R.id.lv_time)
        val data = listOf(
            Triple("1", "6:45", "7:35"),
            Triple("2", "7:40", "8:30"),
            Triple("3", "8:40", "9:30"),
            Triple("4", "9:40", "10:30"),
            Triple("5", "10:35", "11:25"),
            Triple("6", "13:00", "13:50"),
            Triple("7", "13:55", "14:45"),
            Triple("8", "14:55", "15:45"),
            Triple("9", "15:55", "16:45"),
            Triple("10", "16:50", "17:40"),
        )
        listView?.adapter = TimeListAdapter(this,data)
    }
}
