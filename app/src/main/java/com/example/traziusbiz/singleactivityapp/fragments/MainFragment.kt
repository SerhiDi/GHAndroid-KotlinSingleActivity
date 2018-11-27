package com.example.traziusbiz.singleactivityapp.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.traziusbiz.singleactivityapp.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        system_info.setOnClickListener {
            openFragment(SystemInfoFragment())
        }
        communication.setOnClickListener {
            openFragment(CommunicationFragment())
        }
    }

    private fun openFragment(fragment: Fragment) {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

}