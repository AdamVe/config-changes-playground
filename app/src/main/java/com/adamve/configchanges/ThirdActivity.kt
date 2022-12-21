package com.adamve.configchanges

import android.content.ComponentName
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yubico.yubikit.android.YubiKitManager
import com.yubico.yubikit.android.transport.usb.UsbConfiguration

class ThirdActivity : AppCompatActivity() {

    private val viewModel: ThirdActivityVM by viewModels()

    private lateinit var yubikit: YubiKitManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")

        yubikit = YubiKitManager(this)

        setContentView(R.layout.activity_third)

        viewModel.message.observe(this) {
            findViewById<TextView>(R.id.third_activity_info_text).text =
                resources.getString(it)
        }
    }

    override fun onPause() {
        stopUsbDiscovery()
        setUsbIntentFilterEnabled(false)
        log("onPause")
        super.onPause()

    }

    override fun onResume() {
        super.onResume()
        log("onResume")
        setUsbIntentFilterEnabled(true)
        startUsbDiscovery()
    }

    override fun onDestroy() {
        log("onDestroy")
        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        log("onConfigurationChanged")
    }

    private fun setUsbIntentFilterEnabled(enabled: Boolean) {
        val componentName =
            ComponentName(packageName, "com.adamve.configchanges.AliasThirdActivity")
        applicationContext.packageManager.setComponentEnabledSetting(
            componentName,
            if (enabled)
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            else
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
            PackageManager.DONT_KILL_APP
        )
    }

    private fun log(message: String) {
        Log.d("ThirdActivity", "ThirdActivity.$message")
    }

    private fun startUsbDiscovery() {
        val usbConfiguration = UsbConfiguration().handlePermissions(true)
        yubikit.startUsbDiscovery(usbConfiguration) { device ->
            viewModel.setMessageId(R.string.device_connected)
            device.setOnClosed {
                viewModel.setMessageId(R.string.connect_device)
            }
        }
    }

    private fun stopUsbDiscovery() {
        yubikit.stopUsbDiscovery()
    }
}