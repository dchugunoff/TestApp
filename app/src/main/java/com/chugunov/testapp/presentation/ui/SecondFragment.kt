package com.chugunov.testapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chugunov.testapp.databinding.FragmentSecondBinding
import com.chugunov.testapp.presentation.adapters.UsersAdapter
import com.chugunov.testapp.presentation.utils.LoadingState
import com.chugunov.testapp.presentation.viewmodels.MainViewModel

class SecondFragment : Fragment() {

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
        if (savedInstanceState == null) {
            viewModel.loadData()
        }
        Log.d("ViewModel", "${viewModel.loadingState}")
        binding.usersRv.adapter = usersAdapter
        viewModel.users.observe(viewLifecycleOwner) {
            usersAdapter.submitList(it)
        }
        binding.closeFragment.setOnClickListener {
            findNavController().popBackStack()
        }


        //Наблюдение за состоянием загрузки данных
        viewModel.loadingState.observe(viewLifecycleOwner) { loadingState ->
            when (loadingState) {
                LoadingState.Loading -> {
                    binding.loadProgressBar.visibility = View.VISIBLE
                    binding.usersRv.visibility = View.GONE
                }

                LoadingState.Loaded -> {
                    binding.loadProgressBar.visibility = View.GONE
                    binding.usersRv.visibility = View.VISIBLE
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Установка значения суммы
    private fun setupResult() {
        viewModel.sum.observe(viewLifecycleOwner) {
            binding.tvResult.text = it.toString()
        }
    }
}