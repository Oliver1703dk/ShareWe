package com.example.sharewe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sharewe.data.entity.Group
import com.example.sharewe.data.entity.User
import com.example.sharewe.data.repository.ExpenseRepository

class GroupViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _groups = MutableLiveData<List<Group>>()
    val groups: LiveData<List<Group>> get() = _groups

    private val _groupCreationSuccess = MutableLiveData<Boolean>()
    val groupCreationSuccess: LiveData<Boolean> get() = _groupCreationSuccess

    fun createGroup(name: String, description: String, members: List<User>) {
        val newGroup = Group(id = repository.getGroups().size + 1, name = name, description = description, members = members)
        repository.addGroup(newGroup)
        _groups.value = repository.getGroups()
        _groupCreationSuccess.value = true
    }

    fun getAllGroups() {
        _groups.value = repository.getGroups()
    }

    fun getGroupById(groupId: Int): Group? {
        return repository.getGroupById(groupId)
    }
}
