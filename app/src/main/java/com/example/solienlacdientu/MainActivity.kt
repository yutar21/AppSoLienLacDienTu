package com.example.solienlacdientu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.solienlacdientu.fragment.HomeFragment
import com.example.solienlacdientu.fragment.ThongBaoFragment
import com.example.solienlacdientu.fragment.TuyChonFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var homeFragment: HomeFragment
    private lateinit var thongBaoFragment: ThongBaoFragment
    private lateinit var tuyChonFragment: TuyChonFragment
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
