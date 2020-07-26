package com.fizhu.bikeappconcept.di

import androidx.room.Room
import com.fizhu.bikeappconcept.data.db.Db
import com.fizhu.bikeappconcept.data.db.LocalDataSource
import com.fizhu.bikeappconcept.data.pref.PrefDataSource
import com.fizhu.bikeappconcept.data.repository.AppRepository
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by fizhu on 23,May,2020
 * Hvyz.anbiya@gmail.com
 */

val repositoryModule = module {
    single<Repository> { AppRepository(get(), get()) }
}

val persistenceModule = module {
    single { PrefDataSource() }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), Db::class.java, "bike_db")
            .fallbackToDestructiveMigration().build()
    }
    single { get<Db>().userDao() }
    single { get<Db>().bikeDao() }
}

val dataSourceModule = module {
    single { LocalDataSource(get(), get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val appModule =
    listOf(persistenceModule, databaseModule, dataSourceModule, repositoryModule, viewModelModule)