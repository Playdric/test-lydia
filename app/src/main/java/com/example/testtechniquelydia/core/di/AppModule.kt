package com.example.testtechniquelydia.core.di

import android.content.Context
import androidx.room.Room
import com.example.presentation.viewmodel.AppViewModel
import com.example.presentation.viewmodel.ContactListViewModel
import com.example.testtechniquelydia.data.local.ContactDatabase
import com.example.testtechniquelydia.data.local.contact.ContactDao
import com.example.testtechniquelydia.data.remote.contact.ContactRemoteMediator
import com.example.testtechniquelydia.data.remote.contact.ContactService
import com.example.testtechniquelydia.data.repository.ContactRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { ContactListViewModel(get()) }
    viewModel { AppViewModel() }
}

val coreModule = module {
    single<Retrofit> { provideRetrofit() }
}

val dataModule = module {
    single<ContactDatabase> { provideDatabase(get()) }

    single<ContactService> { provideService(get()) }

    single<ContactDao> { provideDao(get()) }

    single<ContactRepository> {
        ContactRepository(
            get(),
            get()
        )
    }

    single<ContactRemoteMediator> {
        ContactRemoteMediator(
            get(),
            get()
        )
    }
}

private inline fun <reified T> provideService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

private fun provideDatabase(ctx: Context): ContactDatabase =
    Room.databaseBuilder(
        ctx,
        ContactDatabase::class.java,
        "ContactDatabase"
    ).fallbackToDestructiveMigration().build()


private fun provideDao(db: ContactDatabase) = db.contactDao()


private fun provideHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        builder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    }
    return builder.build()
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://randomuser.me/")
        .client(provideHttpClient())
        .build()
}