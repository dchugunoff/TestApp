package com.chugunov.testapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import com.chugunov.testapp.databinding.FragmentSecondBinding
import com.chugunov.testapp.presentation.adapters.UsersAdapter
import com.chugunov.testapp.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondFragment: Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding
        get() = _binding ?: throw RuntimeException("FragmentSecondBinding = null")

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        setupResult()
        usersAdapter = UsersAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupResult() {
        viewModel.sum.observe(viewLifecycleOwner) {
            binding.tvResult.text = it.toString()
        }
    }

    private fun loadData() {
        viewModel.viewModelScope.launch {
            viewModel.getUsers()
            binding.usersRv.adapter = usersAdapter
            viewModel.users.observe(viewLifecycleOwner) {
                usersAdapter.submitList(it)
            }
            delay(2000)
            binding.loadProgressBar.visibility = View.GONE
            binding.usersRv.visibility = View.VISIBLE
        }
    }
}