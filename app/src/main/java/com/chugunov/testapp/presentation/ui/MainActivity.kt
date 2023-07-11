package com.chugunov.testapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.chugunov.testapp.R
import com.chugunov.testapp.databinding.ActivityMainBinding
import com.chugunov.testapp.presentation.utils.FragmentState
import com.chugunov.testapp.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel.currentFragmentState.observe(this, Observer { fragmentState ->
            when (fragmentState) {
                is FragmentState.MainFragmentState -> showMainFragment()
                is FragmentState.SecondFragmentState -> showSecondFragment()
            }
        })
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewModel.currentFragmentState.value == FragmentState.SecondFragmentState) {
                    viewModel.setCurrentFragmentState(FragmentState.MainFragmentState)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun showSecondFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SecondFragment())
            .addToBackStack(null)
            .commit()
    }


}