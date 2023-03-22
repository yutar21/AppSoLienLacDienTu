package com.example.solienlacdientu.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.solienlacdientu.R
import com.example.solienlacdientu.data.user
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileActivity: AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val backButton = findViewById<ImageButton>(R.id.btn_back)
        val name_tv = findViewById<TextView>(R.id.name_tv)
        val username_tv = findViewById<TextView>(R.id.username_tv)
        backButton.setOnClickListener {
            onBackPressed()
        }
        // Lấy đối tượng FirebaseUser hiện tại
        val currentUser = FirebaseAuth.getInstance().currentUser
        // Tạo tham chiếu đến nút "users" trong cơ sở dữ liệu Firebase
        val usersRef = FirebaseDatabase.getInstance().getReference("user")
        // Lấy giá trị "name" của người dùng đang đăng nhập
        currentUser?.uid?.let { uid ->
            usersRef.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(user::class.java)
                    val name = user?.name.toString()
                    val username = user?.username.toString()

                    // Gán giá trị vào TextView
                    name_tv.setText(name)
                    username_tv.setText(username)

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Xử lý lỗi nếu có
                }
            })
        }
    }
}