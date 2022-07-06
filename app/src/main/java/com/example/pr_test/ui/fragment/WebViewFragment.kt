package com.example.pr_test.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.example.pr_test.databinding.FragmentWebViewBinding
import com.example.pr_test.localdata.SharedService
import com.example.pr_test.network.MyWebViewClient


class WebViewFragment : BaseFragment<FragmentWebViewBinding>(FragmentWebViewBinding::inflate) {

    companion object {
        fun newInstance(): WebViewFragment {
            return WebViewFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
    }

    private fun initWebView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = MyWebViewClient(SharedService(requireContext()))
        val url = mySharedService.readFromSharedUrl("https://www.reddit.com/")
        binding.webView.loadUrl(url)
        govBack()
    }

    private fun govBack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        isEnabled = false
                        activity?.finish()
                    }
                }
            })
    }


}