package com.example.solienlacdientu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.solienlacdientu.fragment.HomeFragment
import com.example.solienlacdientu.fragment.ThongBaoFragment
import com.example.solienlacdientu.fragment.TuyChonFragment
import com.example.solienlacdientu.ui.Login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var homeFragment: HomeFragment
    private lateinit var thongBaoFragment: ThongBaoFragment
    private lateinit var tuyChonFragment: TuyChonFragment
    private lateinit var activeFragment: Fragment

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
        }

        homeFragment = HomeFragment()
        thongBaoFragment = ThongBaoFragment()
        tuyChonFragment = TuyChonFragment()

        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, homeFragment)
            add(R.id.fragment_container, thongBaoFragment)
            add(R.id.fragment_container, tuyChonFragment)
            hide(thongBaoFragment)
            hide(tuyChonFragment)
            commit()
        }

        activeFragment = homeFragment

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(activeFragment)
                        show(homeFragment)
                        commit()
                    }
                    activeFragment = homeFragment
                    true
                }
                R.id.thongbao -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(activeFragment)
                        show(thongBaoFragment)
                        commit()
                    }
                    activeFragment = thongBaoFragment
                    true
                }
                R.id.tuychon -> {
                    supportFragmentManager.beginTransaction().apply {
                        hide(activeFragment)
                        show(tuyChonFragment)
                        commit()
                    }
                    activeFragment = tuyChonFragment
                    true
                }
                else -> false
            }
        }
    }

}
