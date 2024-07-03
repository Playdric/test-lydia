package com.example.testtechniquelydia.data.remote.contact

import com.example.testtechniquelydia.data.local.contact.Contact

object ContactMapper {
    fun remoteToLocalContact(contactResponse: ContactResponse): List<Contact> {
        return contactResponse.results.map { remoteContact ->
            Contact(
                uuid = remoteContact.login.uuid,
                firstName = remoteContact.name.first,
                lastName = remoteContact.name.last,
                title = remoteContact.name.title,
                email = remoteContact.email,
                smallPicture = remoteContact.picture.thumbnail,
                mediumPicture = remoteContact.picture.medium,
                largePicture = remoteContact.picture.large,
                page = contactResponse.info.page,
                phone = remoteContact.phone
            )
        }
    }
}