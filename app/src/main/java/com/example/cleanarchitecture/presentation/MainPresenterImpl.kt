package com.example.cleanarchitecture.presentation

import android.util.Log
import com.example.cleanarchitecture.domain.usecase.GetUserNameUseCase
import com.example.cleanarchitecture.domain.usecase.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainPresenterImpl @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val view: MainView
) : MainPresenter {

    init {
        Log.e("INFOINFO", "VM created")
    }

    override fun save(text: String) {
        val params = com.example.cleanarchitecture.domain.models.SaveUserNameParams(name = text)
        val resultData = saveUserNameUseCase.execute(param = params)
        view.showResult("Save result = $resultData")
    }

    override fun load() {
        val userName = getUserNameUseCase.execute()
        view.showResult("${userName.firstName} ${userName.lastName}")
    }
}