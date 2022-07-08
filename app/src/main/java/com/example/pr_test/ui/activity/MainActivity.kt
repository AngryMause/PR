package com.example.pr_test.ui.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pr_test.databinding.ActivityMainBinding
import com.example.pr_test.localdata.SharedService
import com.example.pr_test.network.NetworkStatus
import com.example.pr_test.network.NetworkStatusTracker
import com.example.pr_test.ui.fragment.NoInternetFragment
import com.example.pr_test.ui.fragment.StartFragment
import com.example.pr_test.ui.fragment.WebViewFragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.onesignal.OneSignal
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


private const val ONESIGNAL_APP_ID = "26645856-1c70-4a7d-bf76-aa8d7825b0f1"
private const val EMAIL = "email"

class MainActivity : AppCompatActivity() {

    private val networkStatus by lazy { NetworkStatusTracker(this) }
    private val shared by lazy { SharedService(this) }
    private lateinit var callbackManager: CallbackManager


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initOneSignal()
        checkInternetConnection()
    }

    private fun initfacebookLoginButton() {
        callbackManager = CallbackManager.Factory.create();
        binding.fbLoginBtn.setPermissions(Arrays.asList(EMAIL));

        binding.fbLoginBtn.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                }

                override fun onError(error: FacebookException) {
                }

                override fun onSuccess(result: LoginResult) {
                }
            })
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


