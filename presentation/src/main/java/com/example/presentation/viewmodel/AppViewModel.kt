package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.presentation.state.ContactUiState
import com.example.testtechniquelydia.data.local.contact.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<ContactUiState> = MutableStateFlow(ContactUiState())
    val uiState: Flow<ContactUiState> = _uiState

    fun setContact(contact: Contact) {
        _uiState.update { currentState ->
            currentState.copy(contact = contact)
        }
    }

}