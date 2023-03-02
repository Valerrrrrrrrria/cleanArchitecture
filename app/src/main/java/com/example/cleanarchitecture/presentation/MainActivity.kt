package com.example.cleanarchitecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.data.repository.UserRepositoryImpl
import com.example.cleanarchitecture.data.storage.SharedPrefUserStorage
import com.example.cleanarchitecture.data.storage.UserStorage
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import com.example.cleanarchitecture.domain.models.SaveUserNameParams
import com.example.cleanarchitecture.domain.usecase.GetUserNameUseCase
import com.example.cleanarchitecture.domain.usecase.SaveUserNameUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = applicationContext))
    }

    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE)  {
        GetUserNameUseCase(
            userRepository = userRepository
        )
    }

    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE)  {
        SaveUserNameUseCase(
            userRepository = userRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // click on SaveData Button
        binding.sendDataButton.setOnClickListener {
            val text = binding.dataEditText.text.toString()
            val params = com.example.cleanarchitecture.domain.models.SaveUserNameParams(name = text)
            val result = saveUserNameUseCase.execute(param = params)

            binding.dataTextView.text = "Save result = $result"
        }

        // click on receiveData Button
        binding.receiveDataButton.setOnClickListener {
            val userName = getUserNameUseCase.execute()
            binding.dataTextView.text = "${userName.firstName} ${userName.lastName}"
        }
    }
}