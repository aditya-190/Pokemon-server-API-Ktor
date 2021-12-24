package com.bhardwaj.di

import com.bhardwaj.repository.HeroRepository
import com.bhardwaj.repository.HeroRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}