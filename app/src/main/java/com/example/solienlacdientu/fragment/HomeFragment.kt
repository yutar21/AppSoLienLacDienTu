package com.example.solienlacdientu.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.solienlacdientu.R
import com.example.solienlacdientu.data.user
import com.example.solienlacdientu.ui.LichActivity
import com.example.solienlacdientu.ui.ProfileActivity
import com.example.solienlacdientu.ui.TimeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator

class HomeFragment : Fragment() {

    lateinit var btn_time : Button
    lateinit var btn_profile :LinearLayout
    lateinit var name_tv :TextView
    lateinit var username_tv:TextView
    lateinit var btn_QR :Button
    lateinit var btn_lich:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view  = inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
        btn_lich = view.findViewById(R.id.btn_lich)
        btn_QR = view.findViewById(R.id.btn_QR)
        username_tv = view.findViewById(R.id.username_tv)
        name_tv = view.findViewById(R.id.name_tv)
        btn_time = view.findViewById(R.id.btn_time)
        btn_time.setOnClickListener {
            val intent = Intent(activity, TimeActivity::class.java)
            startActivity(intent)
        }
        btn_lich.setOnClickListener{
            val intent = Intent(activity, LichActivity::class.java)
            startActivity(intent)
        }
        btn_profile = view.findViewById(R.id.btn_profile)
        btn_profile.setOnClickListener {
            val intent = Intent(activity, ProfileActivity::class.java)
            startActivity(intent)
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
        btn_QR.setOnClickListener {
            val integrator = IntentIntegrator.forSupportFragment(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Quét mã QR")
            integrator.setBeepEnabled(false)
            integrator.setOrientationLocked(false)
            integrator.initiateScan()
        }

        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                // Xử lý dữ liệu mã QR tại đây
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    companion object {

        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}

