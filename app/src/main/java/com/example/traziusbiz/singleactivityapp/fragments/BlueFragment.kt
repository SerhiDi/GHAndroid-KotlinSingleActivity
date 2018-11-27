package com.example.traziusbiz.singleactivityapp.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.traziusbiz.singleactivityapp.R
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.BUTTON_PRESSED
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.EXTRA_BUTTON_PRESSED
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.EXTRA_SWITCH_CHECKED
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.EXTRA_TEXT_EDIT
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.SWITCH_CHECKED
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.TEXT_EDIT
import kotlinx.android.synthetic.main.blue_fragment.*

class BlueFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blue_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val intent = Intent(TEXT_EDIT)
                intent.putExtra(EXTRA_TEXT_EDIT, edit_text.text.toString())
                activity?.sendBroadcast(intent)
            }

        })

        switch_view.setOnCheckedChangeListener { _, checked ->
            val intent = Intent(SWITCH_CHECKED)
            intent.putExtra(EXTRA_SWITCH_CHECKED, checked)
            activity?.sendBroadcast(intent)
        }

        pressed_button.setOnTouchListener { view, e ->
            val intent = Intent(BUTTON_PRESSED)
            val extra = EXTRA_BUTTON_PRESSED
            when (e.action) {
                MotionEvent.ACTION_DOWN -> {
                    intent.putExtra(extra, true)
                }
                MotionEvent.ACTION_UP -> {
                    intent.putExtra(extra, false)
                }
            }
            activity?.sendBroadcast(intent)
            view?.onTouchEvent(e) ?: true
        }
    }
}