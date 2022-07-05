package com.example.pr_test.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pr_test.databinding.ActivityMainBinding
import com.example.pr_test.ui.fragment.StartFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(binding.mainContainer.id, StartFragment.newInstance())
        }.commit()

    }

    override fun onDestroy() {
        binding = null!!
        super.onDestroy()

    }
}