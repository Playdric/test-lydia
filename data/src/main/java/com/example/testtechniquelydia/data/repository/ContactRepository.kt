package com.example.testtechniquelydia.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testtechniquelydia.data.local.contact.Contact
import com.example.testtechniquelydia.data.local.contact.ContactDao
import com.example.testtechniquelydia.data.remote.contact.ContactRemoteMediator
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class ContactRepository(
    private val contactRemoteMediator: ContactRemoteMediator,
    private val contactDao: ContactDao,
) {

    fun getContacts(): Flow<PagingData<Contact>> = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = contactRemoteMediator
    ) {
        contactDao.pagingSource()
    }.flow

}


