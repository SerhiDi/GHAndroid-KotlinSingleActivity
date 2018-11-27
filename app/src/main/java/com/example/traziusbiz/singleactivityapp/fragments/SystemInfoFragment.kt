package com.example.traziusbiz.singleactivityapp.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traziusbiz.singleactivityapp.R
import kotlinx.android.synthetic.main.system_info_fragment.*
import java.text.SimpleDateFormat
import java.util.*
import android.os.BatteryManager
import com.example.traziusbiz.singleactivityapp.MainActivity

class SystemInfoFragment : Fragment() {

    private val broadcastReceiver: SystemInfoBroadcastReceiver by lazy(this::SystemInfoBroadcastReceiver)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.system_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = "System Info"
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
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG)
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED)
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        context?.registerReceiver(broadcastReceiver, intentFilter)

        setInternetConnectionMessage()
        setChargingMessage()
        setHeadphoneMessage()
        setTimeMessage()
    }

    inner class SystemInfoBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                ConnectivityManager.CONNECTIVITY_ACTION -> {
                    setInternetConnectionMessage()
                }
                Intent.ACTION_POWER_CONNECTED -> {
                    setChargingMessage()
                }
                Intent.ACTION_POWER_DISCONNECTED -> {
                    setChargingMessage()
                }
                Intent.ACTION_HEADSET_PLUG -> {
                    setHeadphoneMessage()
                }
                Intent.ACTION_TIME_TICK -> {
                    setTimeMessage()
                }
                Intent.ACTION_TIME_CHANGED -> {
                    setTimeMessage()
                }
                Intent.ACTION_TIMEZONE_CHANGED -> {
                    setTimeMessage()
                }
            }
        }
    }

    fun setInternetConnectionMessage() {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        var text = "Disconnected"
        if (activeNetwork!!.isConnected) {
            text = "Connected"
        }
        internet_connection.text = text
    }

    fun setHeadphoneMessage() {
        val audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val plugged = audioManager.isBluetoothScoOn || audioManager.isWiredHeadsetOn
        var text = "Headphones: "
        if (plugged) {
            text += "plugged"
        } else {
            text += "unplugged"
        }
        headphones_plugged.text = text
    }

    fun setChargingMessage() {
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val intent = context!!.registerReceiver(null, intentFilter)
        val status = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        var text: String
        val isCharging =
            status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
        if (isCharging) {
            text = "Charging : Yes."
            val chargePlug = intent?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
            val usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
            if (usbCharge) {
                text += "\nUSB Charging"
            }
            val acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
            if (acCharge) {
                text += "\nAC Charging"
            }
            val wirelessCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_WIRELESS
            if (wirelessCharge) {
                text += "\nWireless Charging"
            }
        } else {
            text = "Charging : No."
        }
        device_charging.text = text
    }

    fun setTimeMessage() {
        val timeZone = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        val date = SimpleDateFormat("dd-MM-yyyy HH:mm").format(Date())
        current_date.text = "Date: ${date}\nTimezone: ${timeZone}"
    }

}