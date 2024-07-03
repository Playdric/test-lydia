package com.example.testtechniquelydia.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testtechniquelydia.data.local.ContactDatabase
import com.example.testtechniquelydia.data.local.contact.Contact
import com.example.testtechniquelydia.data.local.contact.ContactDao
import okio.IOException
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var contactDao: ContactDao
    private lateinit var db: ContactDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, ContactDatabase::class.java
        ).build()
        contactDao = db.contactDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val contacts = listOf(
            Contact(
                uuid = "df3e4510-54ea-40b9-b563-a2f64f44b819",
                firstName = "Tameika",
                lastName = "Amada",
                title = "Trudy",
                email = "Anette@gmail.com",
                phone = "01.23.45.67.89",
                smallPicture = "Aundrea",
                mediumPicture = "Alonso",
                largePicture = "Peaches",
                page = 1L
            ), Contact(
                uuid = "c85639f7-f2fc-43cb-a82c-fdaca68bc0f4",
                firstName = "Chaim",
                lastName = "Trudi",
                title = "Alexsandra",
                email = "Montel@gmail.com",
                phone = "01.23.45.67.89",
                smallPicture = "Gordon",
                mediumPicture = "Romel",
                largePicture = "Emily",
                page = 1L
            )
        )
        contactDao.insertAll(contacts)

        val results = contactDao.getAllContacts()

        assert(results.size == 2)
        assert(results[0].uuid == "df3e4510-54ea-40b9-b563-a2f64f44b819")
        assert(results[0].firstName == "Tameika")
        assert(results[0].lastName == "Amada")
        assert(results[0].title == "Trudy")
        assert(results[0].email == "Anette@gmail.com")
        assert(results[0].phone == "01.23.45.67.89")
        assert(results[0].smallPicture == "Aundrea")
        assert(results[0].mediumPicture == "Alonso")
        assert(results[0].largePicture == "Peaches")
        assert(results[0].page == 1L)


    }

}