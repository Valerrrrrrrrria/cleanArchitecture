package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.models.SaveUserNameParams
import com.example.cleanarchitecture.domain.models.UserName
import com.example.cleanarchitecture.domain.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class SaveUserNameUseCaseTest {

    private val userRepository = mock<UserRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun shouldSaveData() {

        val testUserName = UserName(firstName = "test first name", lastName = "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val expected = true
        val testParams = SaveUserNameParams(name = "test name")
        Mockito.`when`(userRepository.saveName(saveParam = testParams)).thenReturn(expected)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)

        val actual = useCase.execute(testParams)

        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.times(1)).saveName(saveParam = testParams)
    }
}