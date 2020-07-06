package com.fizhu.bikeappconcept.di

import org.koin.dsl.module

/**
 * Created by fizhu on 23,May,2020
 * Hvyz.anbiya@gmail.com
 */

//val networkModule = module {

val repositoryModule = module {
//    single<Repository> { AppRepository(get()) }
}

val viewModelModule = module {
}

val appModule = listOf(repositoryModule, viewModelModule)