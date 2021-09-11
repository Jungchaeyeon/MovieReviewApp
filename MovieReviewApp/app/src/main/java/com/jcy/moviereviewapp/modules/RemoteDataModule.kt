package com.jcy.moviereviewapp.modules

import com.jcy.moviereviewapp.data.SearchMovieItems
import com.jcy.moviereviewapp.data.SearchMovieItemsImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val remoteDataModule: Module = module{
   single<SearchMovieItems>{ SearchMovieItemsImpl(get())}
}