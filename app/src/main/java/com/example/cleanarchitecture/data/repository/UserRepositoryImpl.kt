package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.storage.models.User
import com.example.cleanarchitecture.data.storage.UserStorage
import com.example.cleanarchitecture.domain.models.SaveUserNameParams
import com.example.cleanarchitecture.domain.models.UserName
import com.example.cleanarchitecture.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParams): Boolean {
        return userStorage.save(mapToStorage(saveParam))
    }

    override fun getName(): UserName {
        val user = userStorage.get()
        return mapToDomain(user)
    }

    private fun mapToDomain(user: User): UserName {
        return UserName(firstName = user.firstName, lastName = user.lastName)
    }

    private fun mapToStorage(saveParam: SaveUserNameParams): User {
        return User(firstName = saveParam.name, lastName = "")
    }
}