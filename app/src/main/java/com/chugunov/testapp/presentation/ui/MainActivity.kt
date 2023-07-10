package com.chugunov.testapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.chugunov.testapp.R
import com.chugunov.testapp.databinding.ActivityMainBinding
import com.chugunov.testapp.presentation.viewmodels.FragmentState
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
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()
    }

    private fun showSecondFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SecondFragment())
            .commit()
    }
}