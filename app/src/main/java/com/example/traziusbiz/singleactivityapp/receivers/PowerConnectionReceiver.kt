package com.example.traziusbiz.singleactivityapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PowerConnectionReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            Intent.ACTION_POWER_CONNECTED -> {
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
            }
        }
    }
}