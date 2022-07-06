package com.example.pr_test.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.pr_test.R
import com.example.pr_test.databinding.FragmentStartBinding
import com.example.pr_test.network.NetworkStatus
import com.example.pr_test.network.NetworkStatusTracker
import kotlinx.coroutines.launch

class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {
    private val networkStatus by lazy { NetworkStatusTracker(requireContext()) }

    companion object {
        fun newInstance(): StartFragment {
            return StartFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAccept.setOnClickListener {
            checkConnection()
        }
        binding.btnDismiss.setOnClickListener {
            activity?.finish()
        }
    }

    private fun checkConnection() {
        lifecycleScope.launch {
            networkStatus.networkStatus.collect {
                if (it is NetworkStatus.Available) {
                    mySharedService.writeToShareIsSignIn(true)
                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.main_container,
                            WebViewFragment.newInstance())
                            .commit()
                    }
                } else {
                    activity?.supportFragmentManager?.beginTransaction()?.apply {
                        replace(R.id.main_container, NoInternetFragment.newInstance())
                            .commit()
                    }
                }
            }
        }
    }


}