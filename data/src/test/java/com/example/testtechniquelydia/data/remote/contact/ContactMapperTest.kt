package com.example.testtechniquelydia.data.remote.contact

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ContactMapperTest {
    @Test
    fun contact_mapper_test() {

        val contactResponse = ContactResponse(results = listOf(
            RemoteContact(
                gender = "Chrystina",
                name = Name(title = "Mirella", first = "Sally", last = "Katherine"),
                location = Location(
                    street = Street(
                        number = 190L,
                        name = "Shonte"
                    ),
                    city = "Thaddaeus",
                    state = "Alon",
                    country = "Kalvin",
                    postcode = "Terence",
                    coordinates = Coordinates(latitude = "Kassandra", longitude = "Julieann"),
                    timezone = Timezone(offset = "Neisha", description = "Elizebeth")
                ),
                email = "mirella@gmail.com",
                login = Login(
                    uuid = "c4780297-5085-43d7-b8a0-bfeb19e7201a",
                    username = "Lianne",
                    password = "Padraic",
                    salt = "Samanthia",
                    md5 = "Bristol",
                    sha1 = "Timeka",
                    sha256 = "Zebadiah"
                ),
                dateOfBirth = DateOfBirth(date = "Ashlen", age = 4357L),
                registered = Registered(date = "Velia", age = 1001L),
                phone = "01.23.45.67.89",
                cell = "Ignacio",
                id = Id(name = "Jesika", value = "Eugena"),
                picture = Picture(large = "Khristopher", medium = "Heath", thumbnail = "Elesha"),
                nat = "Salina"
            ),
            RemoteContact(
                gender = "Mele",
                name = Name(title = "Rudolph", first = "Delia", last = "Dominic"),
                location = Location(
                    street = Street(
                        number = 551L,
                        name = "Lenise"
                    ),
                    city = "Dejon",
                    state = "Bruno",
                    country = "Klinton",
                    postcode = "Jaquelin",
                    coordinates = Coordinates(latitude = "Lynette", longitude = "Cesar"),
                    timezone = Timezone(offset = "Mireya", description = "Cassidy")
                ),
                email = "Lashana",
                login = Login(
                    uuid = "51d43241-6a9c-4d7b-91d0-3b2d7ffd1b98",
                    username = "Jesica",
                    password = "Hunter",
                    salt = "Delisa",
                    md5 = "Courtland",
                    sha1 = "Fay",
                    sha256 = "Dashaun"
                ),
                dateOfBirth = DateOfBirth(date = "Lamar", age = 3390L),
                registered = Registered(date = "Terrel", age = 7656L),
                phone = "Shabnam",
                cell = "Sierra",
                id = Id(name = "Maximino", value = "Shyra"),
                picture = Picture(large = "Darnell", medium = "Mellissa", thumbnail = "Jarryd"),
                nat = "Nakesha"
            )
        ), info = Info(seed = "Truman", results = 2, page = 1, version = "1.3"))


        val result = ContactMapper.remoteToLocalContact(contactResponse)

        assert(result.size == 2)

        assert(result[0].uuid == "c4780297-5085-43d7-b8a0-bfeb19e7201a")
        assert(result[0].firstName == "Sally")
        assert(result[0].lastName == "Katherine")
        assert(result[0].title == "Mirella")
        assert(result[0].email == "mirella@gmail.com")
        assert(result[0].phone == "01.23.45.67.89")
        assert(result[0].smallPicture == "Elesha")
        assert(result[0].mediumPicture == "Heath")
        assert(result[0].largePicture == "Khristopher")
        assert(result[0].page == 1L)
    }
}