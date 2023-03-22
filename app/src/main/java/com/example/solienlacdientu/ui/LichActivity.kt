package com.example.solienlacdientu.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.solienlacdientu.Adapter.TaskAdapter
import com.example.solienlacdientu.R
import com.example.solienlacdientu.data.Task
import java.text.SimpleDateFormat
import java.util.*


class LichActivity : AppCompatActivity() {
    private lateinit var calendarView: CalendarView
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskList: MutableList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lich)
        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            onBackPressed()
        }
        // Khởi tạo danh sách công việc tĩnh với ngày 23/3/2023
        val date = Calendar.getInstance().apply {
            set(2023, 2, 23) // Lưu ý tháng bắt đầu từ 0
        }.time
        taskList = mutableListOf(
            Task("Họp nhóm", "Họp nhóm với đồng nghiệp lúc 9h sáng", date),
            Task("Gửi email", "Gửi email cho khách hàng về dự án mới", Date()),
            Task("Đi làm việc", "Đi làm việc tại văn phòng", Date()),
            Task("Tập thể dục", "Tập thể dục tại phòng tập gym", Date())
        )

        // Ánh xạ các view từ layout
        calendarView = findViewById(R.id.calendarView)
        recyclerView = findViewById(R.id.recyclerView)

        // Đặt lắng nghe sự kiện khi ngày được chọn trên CalendarView thay đổi
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }.time
            val filteredList = taskList.filter { getDate(it.date) == getDate(selectedDate) }
            recyclerView.adapter = TaskAdapter(filteredList)
        }
    }
    // Hàm chuyển đổi ngày/tháng/năm thành định dạng dd/MM/yyyy
    private fun getDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }
}



