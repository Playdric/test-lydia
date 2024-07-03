package com.example.testtechniquelydia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtechniquelydia.data.local.contact.Contact
import com.example.testtechniquelydia.data.local.contact.ContactDao

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}