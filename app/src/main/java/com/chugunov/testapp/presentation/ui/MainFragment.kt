package com.chugunov.testapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chugunov.testapp.databinding.FragmentMainBinding
import com.chugunov.testapp.presentation.viewmodels.FragmentState
import com.chugunov.testapp.presentation.viewmodels.MainViewModel

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainNextViewButton.setOnClickListener {
            calculateSum()
            viewModel.setCurrentFragmentState(FragmentState.SecondFragmentState)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun calculateSum() {
        val firstNumber = binding.firstNumber.text.toString().toIntOrNull()
        val secondNumber = binding.secondNumber.text.toString().toIntOrNull()
        viewModel.calculateSum(firstNumber ?: 0, secondNumber ?: 0)
    }
}