package com.example.sharewe.data.repository

import com.example.sharewe.data.entity.Expense
import com.example.sharewe.data.entity.Group
import com.example.sharewe.data.entity.User

class ExpenseRepository {

    // In-memory storage for users, groups, and expenses
    private val users = mutableListOf<User>()
    private val groups = mutableListOf<Group>()
    private val expenses = mutableListOf<Expense>()

    // Manage Users
    fun addUser(user: User) {
        users.add(user)
    }

    fun getUsers(): List<User> {
        return users
    }

    // Manage Groups
    fun addGroup(group: Group) {
        groups.add(group)
    }

    fun getGroups(): List<Group> {
        return groups
    }

    fun getGroupById(groupId: Int): Group? {
        return groups.find { it.id == groupId }
    }

    // Manage Expenses
    fun addExpense(expense: Expense) {
        expenses.add(expense)
    }

    fun getExpensesByGroupId(groupId: Int): List<Expense> {
        return expenses.filter { it.groupId == groupId }
    }

    fun getTotalAmountOwed(groupId: Int, userId: Int): Double {
        // Find all expenses for the group
        val groupExpenses = expenses.filter { it.groupId == groupId }

        // Calculate how much the user owes
        var totalOwed = 0.0
        for (expense in groupExpenses) {
            if (expense.participants.any { it.id == userId }) {
                val share = expense.amount / expense.participants.size
                totalOwed += share
            }
        }
        return totalOwed
    }
}
