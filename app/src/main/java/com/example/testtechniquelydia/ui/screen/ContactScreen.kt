@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.testtechniquelydia.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.state.ContactUiState
import com.example.presentation.viewmodel.AppViewModel
import com.example.testtechniquelydia.R
import org.koin.androidx.compose.koinViewModel

enum class ContactScreen(@StringRes val title: Int) {
    ContactListScreen(R.string.screen_name_contact),
    ContactDetailScreen(R.string.screen_name_details)
}

@Composable
fun ContactAppBar(
    currentScreen: ContactScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun ContactApp(
    viewModel: AppViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ContactScreen.valueOf(
        backStackEntry?.destination?.route ?: ContactScreen.ContactListScreen.name
    )
    val uiState by viewModel.uiState.collectAsState(ContactUiState())

    Scaffold(
        topBar = {
            ContactAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ContactScreen.ContactListScreen.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ContactScreen.ContactListScreen.name) {
                ContactListScreen() { contact ->
                    viewModel.setContact(contact)
                    navController.navigate(route = ContactScreen.ContactDetailScreen.name)
                }
            }

            composable(route = ContactScreen.ContactDetailScreen.name) {

                ContactDetailScreen(contact = uiState.contact)
            }
        }
    }
}