package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.models.SaveUserNameParams
import com.example.cleanarchitecture.domain.repository.UserRepository

class SaveUserNameUseCase(private val userRepository: UserRepository) {

    fun execute(param: SaveUserNameParams): Boolean {
        return userRepository.saveName(saveParam = param)
    }
}