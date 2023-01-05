package com.adamve.configchanges

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.yubico.yubikit.android.YubiKitManager
import com.yubico.yubikit.android.transport.usb.UsbConfiguration

class ThirdActivity : ReportingActivity(R.layout.activity_third) {

    private val viewModel: ThirdActivityVM by viewModels()

    private lateinit var yubikit: YubiKitManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        yubikit = YubiKitManager(this)

        viewModel.message.observe(this) {
            findViewById<TextView>(R.id.third_activity_info_text).text =
                resources.getString(it)
        }
    }

    override fun onPause() {
        stopUsbDiscovery()
        setUsbIntentFilterEnabled(false)
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        setUsbIntentFilterEnabled(true)
        startUsbDiscovery()
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

    private fun startUsbDiscovery() {
        log("starting UsbDiscovery")
        val usbConfiguration = UsbConfiguration().handlePermissions(true)
        yubikit.startUsbDiscovery(usbConfiguration) { device ->
            viewModel.setMessageId(R.string.device_connected)
            device.setOnClosed {
                viewModel.setMessageId(R.string.connect_device)
            }
        }
    }

    private fun stopUsbDiscovery() {
        log("stopping UsbDiscovery")
        yubikit.stopUsbDiscovery()
    }
}