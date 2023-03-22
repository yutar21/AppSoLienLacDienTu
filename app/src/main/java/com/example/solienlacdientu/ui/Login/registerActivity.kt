package com.example.solienlacdientu.ui.Login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.solienlacdientu.MainActivity
import com.example.solienlacdientu.R
import com.example.solienlacdientu.data.user
import com.example.solienlacdientu.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import java.security.AuthProvider

class registerActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val username = findViewById<EditText>(R.id.username)
        val fullname = findViewById<EditText>(R.id.fullname)
        val password = findViewById<EditText>(R.id.password)
        val rpassword = findViewById<EditText>(R.id.rpassword)
        var sigupButton = findViewById<Button>(R.id.sigupButton)

        var signupText = findViewById<TextView>(R.id.signupText)
        signupText.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        sigupButton.setOnClickListener {
            val name = username.text.toString()
            val fname = fullname.text.toString()
            val ktpassword = password.text.toString()
            val ktrpassword = rpassword.text.toString()
            if (name.isEmpty() || fname.isEmpty() || ktpassword.isEmpty() || ktrpassword.isEmpty()) {
                if (name.isEmpty()) {
                    username.error = "Bạn chưa nhập tên tài khoản"
                }
                if (fname.isEmpty()) {
                    fullname.error = "Bạn chưa nhập tên"
                }
                if (ktpassword.isEmpty()) {
                    password.error = "Bạn chưa nhập mật khẩu"
                }
                if (ktrpassword.isEmpty()) {
                    rpassword.error = "Bạn chưa nhập lại mật khẩu"
                }
                Toast.makeText(this, "Bạn nhập thiếu thông tin", Toast.LENGTH_LONG).show()
            } else if (ktpassword != ktrpassword) {
                rpassword.error = "Mật khẩu không trùng khớp"
            } else {
                auth.createUserWithEmailAndPassword("$name@gmail.com", ktpassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val databaseRef =
                                database.reference.child("user").child(auth.currentUser!!.uid)
                            val users: user = user(name, fname, auth.currentUser!!.uid)
                            databaseRef.setValue(users).addOnCompleteListener { databaseTask ->
                                if (databaseTask.isSuccessful) {
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Lỗi đăng ký: ${databaseTask.exception}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        } else {
                            Toast.makeText(
                                this,
                                "Lỗi đăng ký: ${task.exception}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }
}