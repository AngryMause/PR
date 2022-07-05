package com.example.pr_test.ui.fragment

import com.example.pr_test.databinding.FragmentNoInternetBinding

class NoInternetFragment() :
    BaseFragment<FragmentNoInternetBinding>(FragmentNoInternetBinding::inflate) {

    companion object {
        fun newInstance(): NoInternetFragment {
            return NoInternetFragment()
        }
    }
}