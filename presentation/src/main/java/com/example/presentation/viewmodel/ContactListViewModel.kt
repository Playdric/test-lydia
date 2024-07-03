package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testtechniquelydia.data.local.contact.Contact
import com.example.testtechniquelydia.data.local.contact.ContactDao
import com.example.testtechniquelydia.data.remote.contact.ContactRemoteMediator
import com.example.testtechniquelydia.data.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val contactRepository: ContactRepository,
) : ViewModel() {

    private val _contactFlow: MutableStateFlow<PagingData<Contact>> = MutableStateFlow(PagingData.empty())
    val contactFlow: Flow<PagingData<Contact>> = _contactFlow

    init {
        viewModelScope.launch {
            getContacts()
        }
    }

    private suspend fun getContacts() {
        contactRepository
            .getContacts()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collectLatest {
                _contactFlow.emit(it)
            }
    }
}