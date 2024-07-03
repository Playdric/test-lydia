package com.example.testtechniquelydia.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.testtechniquelydia.R
import com.example.testtechniquelydia.data.local.contact.Contact
import com.example.testtechniquelydia.ui.theme.largePadding
import com.example.testtechniquelydia.ui.theme.mediumPadding

@Composable
fun ContactDetailScreen(contact: Contact?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding)
    ) {
        if (contact == null) {
            return
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            AsyncImage(
                modifier = Modifier
                    .width(200.dp)
                    .clip(CircleShape),
                model = contact.largePicture,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(mediumPadding))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = contact.title, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.width(mediumPadding))
            Text(text = contact.firstName, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.width(mediumPadding))
            Text(text = contact.lastName, style = MaterialTheme.typography.headlineLarge)
        }
        Spacer(modifier = Modifier.height(largePadding))
        ContactInfoRow(text = contact.phone, icon = R.drawable.round_local_phone_24)
        ContactInfoRow(text = contact.email, icon = R.drawable.baseline_alternate_email_24)
    }
}

@Composable
fun ContactInfoRow(text: String, @DrawableRes icon: Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(mediumPadding), verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = icon), contentDescription = null)
        Spacer(modifier = Modifier.width(mediumPadding))
        Text(text = text, style = MaterialTheme.typography.headlineSmall)
    }
}