package com.example.pr_test.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.appsflyer.AppsFlyerLib
import com.example.pr_test.databinding.ActivityMainBinding
import com.example.pr_test.localdata.SharedService
import com.example.pr_test.network.NetworkStatus
import com.example.pr_test.network.NetworkStatusTracker
import com.example.pr_test.ui.App
import com.example.pr_test.ui.fragment.NoInternetFragment
import com.example.pr_test.ui.fragment.StartFragment
import com.example.pr_test.ui.fragment.WebViewFragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.onesignal.OneSignal
import kotlinx.coroutines.launch
import java.util.*


private const val ONESIGNAL_APP_ID = "26645856-1c70-4a7d-bf76-aa8d7825b0f1"

class MainActivity : AppCompatActivity() {
    private val networkStatus by lazy { NetworkStatusTracker(this) }
    private val shared by lazy { SharedService(this) }


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initOneSignal()
//        checkInternetConnection()
        checkIsFirstSignIn()
    }

    private fun initOneSignal() {
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }

    private fun checkIsFirstSignIn() {
        val check = shared.readFromSharedIsSign(false)
        if (check) {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.mainContainer.id,
                    WebViewFragment.newInstance())
            }.commit()

        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.mainContainer.id, StartFragment.newInstance())
            }.commit()
        }
    }

    private fun checkInternetConnection() {
        lifecycleScope.launch {
            networkStatus.networkStatus.collect {
                if (it is NetworkStatus.Available) {
                    checkIsFirstSignIn()
                } else {
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.mainContainer.id, NoInternetFragment.newInstance())
                            .commit()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null!!
    }
}


