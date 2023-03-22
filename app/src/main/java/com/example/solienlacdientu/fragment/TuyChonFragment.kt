package com.example.solienlacdientu.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.solienlacdientu.R
import com.example.solienlacdientu.ui.Login.LoginActivity

class TuyChonFragment : Fragment() {
    lateinit var logoutbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view  = inflater.inflate(R.layout.fragment_tuy_chon, container, false)
        logoutbtn = view.findViewById(R.id.logoutbtn)
        logoutbtn.setOnClickListener {
            var intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TuyChonFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}