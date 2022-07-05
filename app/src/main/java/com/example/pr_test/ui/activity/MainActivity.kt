package com.example.pr_test.ui.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pr_test.databinding.ActivityMainBinding
import com.example.pr_test.ui.SharedService
import com.example.pr_test.ui.fragment.StartFragment
import com.example.pr_test.ui.fragment.WebViewFragment

class MainActivity : AppCompatActivity() {
    private lateinit var shared: SharedService
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        check()
    }

    fun check() {
        shared = SharedService(this)
        val check = shared.readFromShared()
        if (!check) {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.mainContainer.id, WebViewFragment.newInstance())
            }.commit()

        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.mainContainer.id, StartFragment.newInstance())
            }.commit()
        }
    }

    override fun onDestroy() {
        binding = null!!
        super.onDestroy()

    }
}