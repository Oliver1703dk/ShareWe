package com.example.sharewe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sharewe.data.entity.User
import com.example.sharewe.data.repository.ExpenseRepository

class AuthViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _registrationSuccess = MutableLiveData<Boolean>()
    val registrationSuccess: LiveData<Boolean> get() = _registrationSuccess

    fun registerUser(name: String, email: String) {
        val newUser = User(id = repository.getUsers().size + 1, name = name, email = email)
        repository.addUser(newUser)
        _users.value = repository.getUsers()
        _registrationSuccess.value = true
    }

    fun getAllUsers() {
        _users.value = repository.getUsers()
    }
}
