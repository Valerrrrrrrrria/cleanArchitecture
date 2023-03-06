package com.example.cleanarchitecture.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // subscribe on resultLive update
        vm.stateLive.observe(this, Observer {
            binding.dataTextView.text = "${it.firstName} ${it.lastName} ${it.saveResult}"
        })

        // click on SaveData Button
        binding.sendDataButton.setOnClickListener {
            val text = binding.dataEditText.text.toString()
            vm.send(SaveEvent(text = text))
        }

        // click on receiveData Button
        binding.receiveDataButton.setOnClickListener {
            vm.send(LoadEvent())
        }
    }
}