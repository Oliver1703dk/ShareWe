package com.example.sharewe.data.entity

data class Group(
    val id: Int,
    val name: String,
    val description: String,
    val members: List<User>
)
