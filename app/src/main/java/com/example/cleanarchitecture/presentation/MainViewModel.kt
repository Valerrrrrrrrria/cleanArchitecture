package com.example.cleanarchitecture.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.domain.usecase.GetUserNameUseCase
import com.example.cleanarchitecture.domain.usecase.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase
) : ViewModel() {

    private var stateLiveMutable = MutableLiveData<MainState>()
    var stateLive: LiveData<MainState> = stateLiveMutable

    init {
        Log.e("INFOINFO", "VM created")
        stateLiveMutable.value = MainState(saveResult = false, firstName = "", lastName = "")
    }

    override fun onCleared() {
        Log.e("INFOINFO", "Activity cleared")
        super.onCleared()
    }

    fun send(event: MainEvent) {

        when (event) {
            is SaveEvent -> save(text = event.text)
            is LoadEvent -> load()
        }
    }

    private fun save(text: String) {
        val params = com.example.cleanarchitecture.domain.models.SaveUserNameParams(name = text)
        val resultData = saveUserNameUseCase.execute(param = params)
        stateLiveMutable.value = MainState(
            saveResult = resultData,
            firstName = stateLiveMutable.value!!.firstName,
            lastName = stateLiveMutable.value!!.lastName
        )
    }

    private fun load() {
        val userName = getUserNameUseCase.execute()
        stateLiveMutable.value = MainState(
            saveResult = stateLiveMutable.value!!.saveResult,
            firstName = userName.firstName,
            lastName = userName.lastName
        )
    }
}