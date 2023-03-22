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
            Triple("4", "6:45", "7:35"),
            Triple("5", "6:45", "7:35"),
            Triple("6", "6:45", "7:35"),
            Triple("7", "6:45", "7:35"),
            Triple("8", "6:45", "7:35"),
            Triple("9", "6:45", "7:35"),
            Triple("10", "6:45", "7:35"),
            // Thêm các phần tử khác tương tự
        )
        listView?.adapter = TimeListAdapter(this,data)
    }
}
