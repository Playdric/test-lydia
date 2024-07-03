package com.example.testtechniquelydia.data.local.contact

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contacts: List<Contact>)

    @Query("SELECT * FROM contacts")
    fun pagingSource(): PagingSource<Int, Contact>

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): List<Contact>
}