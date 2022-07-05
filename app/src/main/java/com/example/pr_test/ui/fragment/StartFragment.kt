package com.example.pr_test.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.pr_test.R
import com.example.pr_test.databinding.FragmentStartBinding
import com.example.pr_test.ui.NetworkStatus
import com.example.pr_test.ui.NetworkStatusTracker
import com.example.pr_test.ui.SharedService
import kotlinx.coroutines.launch

class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {
    private lateinit var mySharedService: SharedService
    private val networkStatus by lazy { NetworkStatusTracker(requireContext()) }

    companion object {
        fun newInstance(): StartFragment {
            return StartFragment()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySharedService = SharedService(requireContext())
        binding.btnAccept.setOnClickListener {
            mySharedService.writeToShare(true)
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.main_container, WebViewFragment.newInstance())
                    .commit()
            }

        }

        lifecycleScope.launch {
            networkStatus.networkStatus.collect {
                if (it is NetworkStatus.Available) {
//                    activity?.supportFragmentManager?.beginTransaction()?.apply {
//                        replace(R.id.main_container, WebViewFragment.newInstance())
//                            .commit()
//                    }
                } else {
//                    activity?.supportFragmentManager?.beginTransaction()?.apply {
//                        replace(R.id.main_container, NoInternetFragment.newInstance())
//                            .commit()
//                    }
                }
            }
        }
        binding.btnDismiss.setOnClickListener {
            activity?.finish()
        }

    }


}