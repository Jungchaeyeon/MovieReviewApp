package com.jcy.moviereviewapp.modules

import com.jcy.moviereviewapp.data.MovieRepository
import com.jcy.moviereviewapp.data.MovieRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module{
    single<MovieRepository>{ MovieRepositoryImpl(get())}
}