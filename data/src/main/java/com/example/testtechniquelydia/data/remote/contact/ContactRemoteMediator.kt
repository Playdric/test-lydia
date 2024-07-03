package com.example.testtechniquelydia.data.remote.contact

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.testtechniquelydia.data.local.ContactDatabase
import com.example.testtechniquelydia.data.local.contact.Contact
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ContactRemoteMediator(
    private val database: ContactDatabase,
    private val contactService: ContactService
) : RemoteMediator<Int, Contact>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Contact>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.page + 1
                }
            }
            val response = contactService.getContact(results = 20, page = page)

            database.withTransaction {
                database.contactDao().insertAll(ContactMapper.remoteToLocalContact(response))
            }

            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}