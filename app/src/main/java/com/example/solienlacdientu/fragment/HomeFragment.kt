package com.example.solienlacdientu.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.solienlacdientu.R
import com.example.solienlacdientu.ui.TimeActivity

class HomeFragment : Fragment() {

    lateinit var btn_time : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view  = inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment

        btn_time = view.findViewById(R.id.btn_time)
        btn_time.setOnClickListener {
            val intent = Intent(activity, TimeActivity::class.java)
            startActivity(intent)
        }
        return view
    }


    companion object {

        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}