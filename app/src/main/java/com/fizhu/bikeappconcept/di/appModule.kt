package com.fizhu.bikeappconcept.di

import androidx.room.Room
import com.fizhu.bikeappconcept.data.db.Db
import com.fizhu.bikeappconcept.data.db.LocalDataSource
import com.fizhu.bikeappconcept.data.repository.AppRepository
import com.fizhu.bikeappconcept.data.repository.Repository
import org.koin.dsl.module

/**
 * Created by fizhu on 23,May,2020
 * Hvyz.anbiya@gmail.com
 */

val repositoryModule = module {
    single<Repository> { AppRepository(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), Db::class.java, "bike_db")
            .fallbackToDestructiveMigration().build()
    }
    single { get<Db>().userDao() }
}

val dataSourceModule = module {
    single { LocalDataSource(get()) }
}

val viewModelModule = module {
}

val appModule = listOf(databaseModule, dataSourceModule, repositoryModule, viewModelModule)