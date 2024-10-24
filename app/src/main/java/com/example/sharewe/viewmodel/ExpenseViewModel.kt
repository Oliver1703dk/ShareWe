package com.example.sharewe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sharewe.data.entity.Expense
import com.example.sharewe.data.entity.User
import com.example.sharewe.data.repository.ExpenseRepository

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> get() = _expenses

    private val _expenseCreationSuccess = MutableLiveData<Boolean>()
    val expenseCreationSuccess: LiveData<Boolean> get() = _expenseCreationSuccess

    fun addExpense(groupId: Int, payer: User, amount: Double, description: String, participants: List<User>) {
        val newExpense = Expense(
            id = repository.getExpensesByGroupId(groupId).size + 1,
            groupId = groupId,
            payer = payer,
            amount = amount,
            description = description,
            participants = participants
        )
        repository.addExpense(newExpense)
        _expenses.value = repository.getExpensesByGroupId(groupId)
        _expenseCreationSuccess.value = true
    }

    fun getExpensesByGroup(groupId: Int) {
        _expenses.value = repository.getExpensesByGroupId(groupId)
    }

    fun getTotalAmountOwed(groupId: Int, userId: Int): Double {
        return repository.getTotalAmountOwed(groupId, userId)
    }
}
