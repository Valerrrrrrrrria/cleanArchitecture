package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.models.SaveUserNameParams

class SaveUserNameUseCase {

    fun execute(param: SaveUserNameParams) : Boolean {
        return param.name.isNotEmpty()
    }
}