package com.example.testtechniquelydia.data.local.contact

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact (
    @PrimaryKey(autoGenerate = false)
    val uuid: String,
    val firstName: String,
    val lastName: String,
    val title: String,
    val email: String,
    val phone: String,
    val smallPicture: String,
    val mediumPicture: String,
    val largePicture: String,
    val page: Long,
)