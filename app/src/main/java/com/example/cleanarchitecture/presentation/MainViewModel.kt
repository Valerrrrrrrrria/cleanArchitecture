package com.example.cleanarchitecture.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.domain.usecase.GetUserNameUseCase
import com.example.cleanarchitecture.domain.usecase.SaveUserNameUseCase

class MainViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase
) : ViewModel() {

    private var resultLiveMutable = MutableLiveData<String>()
    var resultLive: LiveData<String> = resultLiveMutable

    init {
        Log.e("INFOINFO", "VM created")
    }

    override fun onCleared() {
        Log.e("INFOINFO", "Activity cleared")
        super.onCleared()
    }

    fun save(text: String) {
        val params = com.example.cleanarchitecture.domain.models.SaveUserNameParams(name = text)
        val resultData = saveUserNameUseCase.execute(param = params)
        resultLiveMutable.value = "Save result = $resultData"
    }

    fun load() {
        val userName = getUserNameUseCase.execute()
        resultLiveMutable.value = "${userName.firstName} ${userName.lastName}"
    }
}