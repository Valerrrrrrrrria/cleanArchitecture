package com.example.cleanarchitecture.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        // subscribe on resultLive update
        vm.resultLive.observe(this, Observer {
            binding.dataTextView.text = it
        })

        // click on SaveData Button
        binding.sendDataButton.setOnClickListener {
            val text = binding.dataEditText.text.toString()
            vm.save(text)
        }

        // click on receiveData Button
        binding.receiveDataButton.setOnClickListener {
            vm.load()
        }
    }
}