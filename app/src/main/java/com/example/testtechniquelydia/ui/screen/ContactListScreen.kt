package com.example.testtechniquelydia.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.presentation.viewmodel.ContactListViewModel
import com.example.testtechniquelydia.R
import com.example.testtechniquelydia.data.local.contact.Contact
import com.example.testtechniquelydia.ui.theme.mediumPadding
import com.example.testtechniquelydia.ui.theme.smallPadding
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactListScreen(
    modifier: Modifier = Modifier,
    viewModel: ContactListViewModel = koinViewModel(),
    onClickContact: (Contact) -> Unit = {}
) {

    val lazyPagingItems = viewModel.contactFlow.collectAsLazyPagingItems()


    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(modifier = modifier) {
            items(count = lazyPagingItems.itemCount) { index ->
                val item = lazyPagingItems[index] ?: return@items
                Row(
                    Modifier
                        .clickable { onClickContact.invoke(item) }
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(mediumPadding)
                            .clip(CircleShape),
                        model = item.largePicture,
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.round_portrait_24)
                    )
                    Text(text = item.title)
                    Spacer(modifier = Modifier.width(smallPadding))
                    Text(text = item.firstName)
                    Spacer(modifier = Modifier.width(smallPadding))
                    Text(text = item.lastName)
                }
                HorizontalDivider()
            }
        }
        if (lazyPagingItems.loadState.mediator?.refresh is LoadState.Error) {
            Snackbar(modifier = Modifier.align(Alignment.BottomCenter)) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.error_offline_contacts))
                    Spacer(modifier = Modifier.weight(1F))
                    TextButton(onClick = { lazyPagingItems.refresh() }) {
                        Text(text = "RETRY")
                    }
                }
            }
        }
    }
}
