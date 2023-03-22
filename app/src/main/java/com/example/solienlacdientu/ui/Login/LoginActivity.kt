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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        var username = findViewById<EditText>(R.id.username)
        var password = findViewById<EditText>(R.id.password)
        var loginButton = findViewById<Button>(R.id.loginButton)
        var signupText = findViewById<TextView>(R.id.signupText)
        signupText.setOnClickListener {
            var intent = Intent(this, registerActivity::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener {
            var ktusername = username.text.toString()
            var ktpassword = password.text.toString()
            if (ktusername.isEmpty()||ktpassword.isEmpty()){
                if (ktusername.isEmpty()){
                    username.error = "bạn chưa nhập tài khoản "
                }
                if (ktpassword.isEmpty()){
                    password.error = "bạn chưa nhập mật khẩu "
                }
                Toast.makeText(this,"Bạn nhập thiếu thông tin",Toast.LENGTH_LONG).show()
            } else{
                auth.signInWithEmailAndPassword("$ktusername@gmail.com",ktpassword).addOnCompleteListener{
                    if (it.isSuccessful){
                        var intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Tài khoản hoặc mật khẩu không chính xác!",Toast.LENGTH_LONG).show()
                    }

                }
            }
        }
    }
}