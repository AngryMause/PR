package com.example.pr_test.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pr_test.R
import com.example.pr_test.databinding.FragmentStartBinding

class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {
    companion object {
        fun newInstance(): StartFragment {
            return StartFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnAccept?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.main_container, WebViewFragment.newInstance())
                addToBackStack(null)
                    .commit()
            }
        }
        binding?.btnDismiss?.setOnClickListener {
            activity?.finish()
        }

    }






}