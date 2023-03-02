package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.domain.models.SaveUserNameParams
import com.example.cleanarchitecture.domain.models.UserName

interface UserRepository {
    fun saveName(saveParam: SaveUserNameParams): Boolean
    fun getName(): UserName
}