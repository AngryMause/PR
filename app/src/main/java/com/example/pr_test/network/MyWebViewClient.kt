package com.example.pr_test.network

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.pr_test.localdata.SharedService

class MyWebViewClient(private var myShare: SharedService) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        myShare.writeURLToShared(request?.url.toString())
        return true
    }
}