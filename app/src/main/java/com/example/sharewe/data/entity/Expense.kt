package com.example.sharewe.data.entity

data class Expense(
    val id: Int,
    val groupId: Int,
    val payer: User,
    val amount: Double,
    val description: String,
    val participants: List<User>
)
