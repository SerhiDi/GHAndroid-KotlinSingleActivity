package com.example.traziusbiz.singleactivityapp.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traziusbiz.singleactivityapp.R
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.BUTTON_PRESSED
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.EXTRA_BUTTON_PRESSED
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.EXTRA_SWITCH_CHECKED
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.EXTRA_TEXT_EDIT
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.SWITCH_CHECKED
import com.example.traziusbiz.singleactivityapp.fragments.CONSTANTS.TEXT_EDIT
import kotlinx.android.synthetic.main.green_fragment.*

class GreenFragment : Fragment() {
    private val broadcastReceiver: CommunicationBroadcastReceiver by lazy(this::CommunicationBroadcastReceiver)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.green_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        initReceivers()
    }

    override fun onPause() {
        super.onPause()
        context?.unregisterReceiver(broadcastReceiver)
    }

    private fun initReceivers() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(TEXT_EDIT)
        intentFilter.addAction(SWITCH_CHECKED)
        intentFilter.addAction(BUTTON_PRESSED)
        context?.registerReceiver(broadcastReceiver, intentFilter)

        setTextMessage("")
        setSwitchMessage(false)
        setButtonMessage(false)
    }

    inner class CommunicationBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                TEXT_EDIT -> {
                    val message = intent.getStringExtra(EXTRA_TEXT_EDIT)
                    setTextMessage(message)
                }
                SWITCH_CHECKED -> {
                    val checked = intent.getBooleanExtra(EXTRA_SWITCH_CHECKED, false)
                    setSwitchMessage(checked)
                }
                BUTTON_PRESSED -> {
                    val pressed = intent.getBooleanExtra(EXTRA_BUTTON_PRESSED, false)
                    setButtonMessage(pressed)
                }
            }
        }

    }

    private fun setTextMessage(text: String) {
        text_status.text = text
    }

    private fun setSwitchMessage(status: Boolean) {
        var text = "'Off'"
        if (status) {
            text = "'On'"
        }
        switch_status.text = text
    }

    private fun setButtonMessage(status: Boolean) {
        var text = "'Not pressed'"
        if (status) {
            text = "'Pressed'"
        }
        button_status.text = text
    }

}
