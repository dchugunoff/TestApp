package com.chugunov.testapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chugunov.testapp.databinding.FragmentMainBinding
import com.chugunov.testapp.presentation.utils.FragmentState
import com.chugunov.testapp.presentation.viewmodels.MainViewModel

class MainFragment : Fragment() {

    //Приватное свойство для привязки фрагмента
    private var _binding: FragmentMainBinding? = null

    //Публичное свойство для доступа к привязке фрагмента
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    //Создание экземпляра viewModel с использованием activityViewModels() делегата свойств
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

        //Слушатель клика на кнопку
        binding.mainNextViewButton.setOnClickListener {
            calculateSum()
            viewModel.setCurrentFragmentState(FragmentState.SecondFragmentState)
        }
    }

    /**
     * Зануление _binding выполняется для предотвращения утечки памяти.
     * Когда фрагмент уничтожается - он все еще может удерживать ссылку на привязку binding, которая
     * удерживает ссылки на view в раздутом макете. Это может привести к утечки памяти, т.к ссылка
     * на представления будет сохраняться и не будет освобождаться даже после уничтожения фрагмента.
     */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Передача числе из EditText в функцию для рассчета суммы во viewModel
    private fun calculateSum() {
        val firstNumber = binding.firstNumber.text.toString().toIntOrNull()
        val secondNumber = binding.secondNumber.text.toString().toIntOrNull()
        viewModel.calculateSum(firstNumber ?: 0, secondNumber ?: 0)
    }
}