package com.chugunov.testapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chugunov.testapp.databinding.ActivityMainBinding
import com.chugunov.testapp.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {


    //Экземпляр привязки для активити
    private lateinit var binding: ActivityMainBinding

    //Создание экземпляра ViewModel с использованием viewModels() делегата свойств.
    //елегат свойства by viewModels() брабатывает создание и сохраненине экземпляра ViewModel для активити.
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Настройка привязки с использованием раздувания макета
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }


}