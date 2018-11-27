package com.example.traziusbiz.singleactivityapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimeChangesReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context, intent: Intent) {
        when(intent.action) {
            Intent.ACTION_TIME_TICK -> {
            }
            Intent.ACTION_TIMEZONE_CHANGED -> {
            }
        }
    }
}